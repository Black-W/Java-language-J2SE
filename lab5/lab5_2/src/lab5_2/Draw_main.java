package lab5_2;

import java.util.Random;
import java.util.Scanner;

import javax.swing.JFrame;
import lab5_2.DrawPanel;

public class Draw_main {

	public static void main(String[] args) {

		// 创建窗体
		JFrame application = new JFrame("绘图程序");
		application.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		application.setSize(300, 300);
		application.setVisible(true);

		// 创建画板
		DrawPanel panel = new DrawPanel();
		application.add(panel);

		// 开始绘制
		Scanner input = new Scanner(System.in);
		
		for (int i = 0; i < 20; i++) {
			System.out.print("绘制第"+(i+1)+"个图形——");
			Random random = new Random();
			int choice = random.nextInt(3);
			switch (choice) {
			case 0:	//绘制直线
				System.out.println("直线，请输入直线两端点的坐标（画板横纵坐标范围是0~300），数字用空格隔开：");
				// 输入参数
				int x1 = input.nextInt();
				int y1 = input.nextInt();
				int x2 = input.nextInt();
				int y2 = input.nextInt();
				panel.paintLine(x1,y1,x2,y2);
				break;
			case 1:	//绘制矩形
				System.out.println("矩形，请输入矩形左上角坐标，以及宽和高（画板横纵坐标范围是0~300），数字用空格隔开：");
				// 输入参数
				int x = input.nextInt();
				int y = input.nextInt();
				int width = input.nextInt();
				int height = input.nextInt();
				panel.paintRec(x,y,width,height);
				break;
			case 2:	//绘制椭圆
				System.out.println("椭圆，请输入椭圆左上角坐标，以及宽和高（画板横纵坐标范围是0~300），数字用空格隔开：");
				// 输入参数
				int x3 = input.nextInt();
				int y3 = input.nextInt();
				int width3 = input.nextInt();
				int height3 = input.nextInt();
				panel.paintOval(x3,y3,width3,height3);
				break;
			}
		}
		System.out.println("绘制完毕");
		input.close();
	}

}
