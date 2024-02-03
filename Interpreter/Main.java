import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		System.out.println("enter number");
		int a = scanner.nextInt();
		int b = 10;
		System.out.println("10 added");
		System.out.println("enter number");
		int c = scanner.nextInt();
		int sum = a + b + c;
		System.out.println("Sum: " + sum);
		scanner.close();
		System.exit(0);
	}
}