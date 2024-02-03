import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;


public class Run {
    public static void runJavaCode(String javaCode) {
        // Write the Java code to a temporary file
        Path tempJavaFile = Paths.get("Main.java");
        try {
            Files.write(tempJavaFile, javaCode.getBytes());
        } catch (IOException ex) {
            ex.printStackTrace();
            return;
        }

        // Compile the Java file
        Process compileProcess;
        try {
            compileProcess = new ProcessBuilder("javac", "Main.java").start();
            compileProcess.waitFor();

            // Check if compilation was successful
            if (compileProcess.exitValue() != 0) {
                Scanner errorScanner = new Scanner(compileProcess.getErrorStream());
                while (errorScanner.hasNextLine()) {
                    System.err.println(errorScanner.nextLine());
                }
                errorScanner.close();
                return;
            }
        } catch (IOException | InterruptedException ex) {
            ex.printStackTrace();
            return;
        }

        // Execute the compiled Java program
        try {
            Process executionProcess = new ProcessBuilder("java", "Main").start();
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(executionProcess.getOutputStream()));
            BufferedReader reader = new BufferedReader(new InputStreamReader(executionProcess.getInputStream()));
            BufferedReader errorReader = new BufferedReader(new InputStreamReader(executionProcess.getErrorStream()));

            // Start a separate thread to read from console and write to process input stream
            Thread consoleReaderThread = new Thread(() -> {
                try {
                    BufferedReader consoleReader = new BufferedReader(new InputStreamReader(System.in));
                    String line;
                    while ((line = consoleReader.readLine()) != null) {
                        writer.write(line);
                        writer.newLine();
                        writer.flush();
                    }
                    // Close writer when console input ends
                    writer.close();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            });
            consoleReaderThread.start();

            // Read from process output and error streams and print to console
            String outputLine;
            while ((outputLine = reader.readLine()) != null) {
                System.out.println(outputLine);
            }
            String errorLine;
            while ((errorLine = errorReader.readLine()) != null) {
                System.err.println(errorLine);
            }

            // Wait for the console reader thread to finish
            consoleReaderThread.join();

            // Close resources
            reader.close();
            errorReader.close();
        } catch (IOException | InterruptedException ex) {
            ex.printStackTrace();
        }
    }
}
