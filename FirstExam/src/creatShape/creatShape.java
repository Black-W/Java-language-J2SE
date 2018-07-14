package creatShape;

import creatShape.Circle;
import creatShape.Rectangle;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;

/**
 * @author WangZhenwei
 * @date 2018/04/11
 */

// 输出并实现命令列表
public class creatShape {
	// 创建Scanner对象，用于输入
	static Scanner input = new Scanner(System.in);

	// 创建Random对象，用于产生随机颜色
	static Random random = new Random();

	// 用ArrayList来存放创建的图形
	static ArrayList<Shape> shapes = new ArrayList<Shape>();

	public static void main(String[] args) {

		boolean isEnd = false;

		// 用一个while循环来实现多次输入命令，直到输入5为止
		while (!isEnd) {
			showList();
			int choice = inputInt(); // 变量choice表示用户输入的选择

			switch (choice) {
			case 1:
				addShape();
				break;
			case 2:
				outShapesPerimeter();
				break;
			case 3:
				outShapesArea();
				break;
			case 4:
				outShapesColor();
				break;
			case 5:
				System.out.println("程序安全退出");
				isEnd = true;
				break;
			}
		}
	}

	// 输出命令列表
	static void showList() {
		System.out.println("请输入命令编号:");
		System.out.println("1:创建图形");
		System.out.println("2:输出所有图形的周长");
		System.out.println("3:输出所有图形的面积");
		System.out.println("4:输出所有图形的颜色");
		System.out.println("5:结束程序");

	}

	// 选择1，创建图形
	static void addShape() {

		boolean isEnd = false;
		while (!isEnd) {
			System.out.println("请输入要创建的图形的名称：");
			String name = input.nextLine();
			switch (name) {
			case "Circle":
				System.out.println("请输入圆形的半径");
				double radius = inputDouble();
				Shape newCircle = new Circle(radius);
				newCircle.setColor(random.nextInt(256), random.nextInt(256), random.nextInt(256));
				shapes.add(newCircle);
				isEnd = true;
				break;
			case "Rectangle":
				System.out.println("请输入矩形的宽和高，中间用空格隔开");
				double width =inputDouble();
				double height = inputDouble();
				Shape newRec = new Rectangle(width, height);
				newRec.setColor(random.nextInt(256), random.nextInt(256), random.nextInt(256));
				shapes.add(newRec);
				isEnd = true;
				break;
			default:
				System.out.println("请输入正确的图形名称!");
			}
		}

	}

	// 选择2，输出所有图形的周长
	static void outShapesPerimeter() {
		System.out.println("编号\t图形名称\t\t周长");
		for (int i = 0; i < shapes.size(); i++) {
			System.out.printf("%d\t%s  \t%.2f\n", i + 1, shapes.get(i).name(), shapes.get(i).perimeter());
		}
	}

	// 选择3，输出所有图形的面积
	static void outShapesArea() {
		System.out.println("编号\t图形名称\t\t面积");
		for (int i = 0; i < shapes.size(); i++) {
			System.out.printf("%d\t%s  \t%.2f\n", i + 1, shapes.get(i).name(), shapes.get(i).area());
		}
	}

	// 选择4，输出所有图形的颜色
	static void outShapesColor() {
		System.out.println("编号\t图形名称\t\t颜色");
		for (int i = 0; i < shapes.size(); i++) {
			System.out.printf("%d\t%s  \t(%d,%d,%d)\n", i + 1, shapes.get(i).name(), shapes.get(i).getRed(),
					shapes.get(i).getGreen(), shapes.get(i).getBlue());
		}
	}

	// 输入一个1到5之间的整数
	static int inputInt() {
		int temp = 0;
		while (true) {
			try {
				temp = input.nextInt();
				if (temp < 1 || temp > 5) {
					System.out.println("输入无效，请重新输入数字1到5");
					continue;
				}
			} catch (InputMismatchException e) {
				System.out.println("请输入数字！");
				// 上次输错的那个数据还在缓冲流中。定义一个字符串变量把它读走
				String clear = input.nextLine();
				continue;
			}
			// 清除缓冲区中的回车
			String clear = input.nextLine();

			break;
		}

		return temp;
	}

	// 输入一个大于0的浮点数
	static double inputDouble() {
		double temp = 0;
		while (true) {
			try {
				temp = input.nextDouble();
				if (temp < 0) {
					System.out.println("输入无效，请输入大于0的数字");
					continue;
				}
			} catch (InputMismatchException e) {
				System.out.println("请输入数字！");
				// 上次输错的那个数据还在缓冲流中。定义一个字符串变量把它读走
				String clear = input.nextLine();
				continue;
			}
			break;
		}

		return temp;
	}
}