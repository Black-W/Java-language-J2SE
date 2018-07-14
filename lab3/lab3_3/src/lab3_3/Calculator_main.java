package lab3_3;

import java.util.Scanner;
public class Calculator_main {

	public static void main(String[] args) {
		Scanner input=new Scanner(System.in);
		while(true) {
		System.out.println("请输入a的值：");
		double a=input.nextDouble();
		System.out.println("请输入b的值：");
		double b=input.nextDouble();
		System.out.println("请输入运算符（+、-、*、/、%）：");
		String s=input.next();
		char operator=s.charAt(0);
		switch(operator) {
		case '+':
			System.out.println(a+s+b+'='+(a+b));
			break;
		case '-':
			System.out.println(a+s+b+'='+(a-b));
			break;
		case '*':
			System.out.println(a+s+b+'='+(a*b));
			break;
		case '/':
			System.out.println(a+s+b+'='+(a/b));
			break;
		case '%':
			System.out.println(a+s+b+'='+(a%b));
			break;
		}
		}
	}

}
