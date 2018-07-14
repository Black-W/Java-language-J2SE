package lab6_1;

import java.util.Scanner;

public class ExceptionTest {

	public static void main(String[] args) {
		Student student=new Student();
		Scanner input = new Scanner(System.in);
		String name;
		String address;
		boolean needContinue = true;
		
		System.out.println("请输入姓名");
		while (needContinue) {
			try {
				name = input.nextLine();
				student.setName(name);
				needContinue = false;
			} catch (IllegalNameException e) {
				System.err.println(e);
				System.out.println("请重新输入姓名");
			}
		}
		
		needContinue=true;
		System.out.println("请输入地址");
		while(needContinue) {
			try {
				address=input.nextLine();
				student.setAddress(address);
				needContinue=false;
			}catch (IllegalAddressException e) {
				System.err.println(e);
				System.out.println("请重新输入地址");
			}
		}
		
		System.out.println("信息输入成功！");
		input.close();
	}

}
