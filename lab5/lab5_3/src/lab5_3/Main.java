package lab5_3;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
	static Scanner input = new Scanner(System.in);
	
	public static void main(String[] args) {
		// 输入动物总数
		int num = inputNumOfAnimals();

		// 输入num个动物名称
		ArrayList<String> animalKind=inputNameOfAnimals(num);
		
		// 创建对象，输出种类和声音
		for (String s : animalKind) {
			try {
				Animal animal = (Animal) Class.forName("lab5_3." + s).newInstance();
				System.out.print("kind:" + animal.animalKind() + ",");
				animal.talk();
			} catch (Exception e) {
				System.out.println(e);
			}
		}
		
		input.close();
	}//end main

	public static int inputNumOfAnimals() {
		System.out.println("Please input the number of animal species:");
		int num = 0;
		while (true) {
			try {
				num = input.nextInt();
			} catch (InputMismatchException e) {
				System.out.println("Wrong type.Int number needed.");
				System.out.println("Please input the number of animal species:");
				// 上次输错的那个数据还在缓冲流中。定义一个字符串变量把它读走
				String clear = input.nextLine();
				continue;
			}
			break;
		}
		return num;
	}// end inputNumOfAnimals

	public static ArrayList<String> inputNameOfAnimals(int num) {
		ArrayList<String> animalKind = new ArrayList<String>();
		System.out.println("Please input " + num + " animal's specie names,and input \"end\" to end input.");
		
		while (true) {
			int sum = 0;
			boolean nameFalse = false;
			boolean numberFalse = false;
			
			while (true) {
				String s=new String();
				try {
				 s = input.next();
				 sum++; // 统计输入的字符数量
				}catch(Exception e) {
					System.out.println(e);
				}
				if (!s.equals("Dog") && !s.equals("Pig") && !s.equals("Chicken")) {
					if (s.equals("end")) { // 名称输入正确，判断数量是否正确
						if (sum != num + 1)
							numberFalse = true;
						break;
					} else { // 名称输入错误
						nameFalse = true;
						break;
					}
				} else
					animalKind.add(s); // 输入正确名称放到数组中
			}
			
			if (numberFalse) {
				System.out.println("Wrong number of String.");
				System.out.println("Please input " + num + " animal's specie names,and input \"end\" to end input.");
				animalKind.clear(); // 清空数组
				// 上次输错的那个数据还在缓冲流中。定义一个字符串变量把它读走
				String clear = input.nextLine();
			} else if (nameFalse) {
				System.out.println("Creat object error.");
				System.out.println("Please input " + num + " animal's specie names,and input \"end\" to end input.");
				animalKind.clear();
				String clear = input.nextLine();
			} else
				break;
		}
		return animalKind;
	}//end inputNameOfAnimals

}
