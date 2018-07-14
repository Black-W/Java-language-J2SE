package lab11_1;

import java.awt.List;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Scanner;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class CreateStudentsBook {

	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);

		ArrayList<Student> students = new ArrayList<Student>();
		while (true) {
			try {
				System.out.println("请依次输入：学号、姓名、班级、已修学分，中间用空格隔开(输入Q/q退出输入)");
				String inputStr = input.nextLine();
				// 输入Q/q退出
				if (inputStr.equals("Q") || inputStr.equals("q")) {
					break;
				} else {
					String[] tokens = inputStr.split(" ");
					students.add(new Student(tokens[0], tokens[1], tokens[2], tokens[3]));
				}
			} catch (Exception e) {
				System.err.println("输入有误,请重新输入");
			}
		}

		System.out.println("按照输入顺序输出所有学生信息:");
		System.out.println("学号\t姓名\t班级\t已修学分");
		students.stream().forEach(System.out::println);

		System.out.println("按照先按班级再按学号排序的顺序输出所有学生信息:");
		System.out.println("学号\t姓名\t班级\t已修学分");
		students.stream().sorted(Comparator.comparing(Student::get_class).thenComparing(Student::getStudentNumber))
				.forEach(System.out::println);

		System.out.print("输出全部学生所修学分的平均分:");
		System.out.println(students.stream().mapToDouble(student -> Double.parseDouble(student.getCredits())).average()
				.getAsDouble());

		System.out.println("分班级分组输出所有学生:");
		students.stream().collect(Collectors.groupingBy(Student::get_class)).forEach((_class, studentsInClass) -> {
			System.out.println("班级:" + _class);
			System.out.println("学号\t姓名\t班级\t已修学分");
			studentsInClass.stream().forEach(System.out::println);
		});
	}

}
