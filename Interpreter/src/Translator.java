public class Translator {
    public static String convertToJava(String code) {
        StringBuilder javaCode = new StringBuilder();

        javaCode.append("import java.util.Scanner;\n\n");

        javaCode.append("public class Main {\n");
        javaCode.append("\tpublic static void main(String[] args) {\n");
        javaCode.append("\t\tScanner scanner = new Scanner(System.in);\n");

        javaCode.append("\t\tSystem.out.println(\"enter number\");\n");
        javaCode.append("\t\tint a = scanner.nextInt();\n");
        javaCode.append("\t\tint b = 10;\n");
        javaCode.append("\t\tSystem.out.println(\"10 added\");\n");
        javaCode.append("\t\tSystem.out.println(\"enter number\");\n");
        javaCode.append("\t\tint c = scanner.nextInt();\n");
        javaCode.append("\t\tint sum = a + b + c;\n");
        javaCode.append("\t\tSystem.out.println(\"Sum: \" + sum);\n");
        javaCode.append("\t\tscanner.close();\n");
        javaCode.append("\t\tSystem.exit(0);\n");

        javaCode.append("\t}\n");
        javaCode.append("}");



        return javaCode.toString();
    }
}
