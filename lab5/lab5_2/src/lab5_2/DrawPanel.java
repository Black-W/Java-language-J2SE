package lab5_2;

import java.util.Random;
import java.util.Scanner;
import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JPanel;

public class DrawPanel extends JPanel {
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
	}

	public void paintLine(int x1,int y1,int x2,int y2) {
		Graphics g=this.getGraphics();
		// 设置本次绘制的图形填充颜色
		Random random = new Random();
		Color myColor = new Color(random.nextInt(256), random.nextInt(256), random.nextInt(256));
		g.setColor(myColor);
		// 检验参数
		int width = getWidth();
		int height = getHeight();
		if (x1 >= 0 && y1 >= 0 && x2 >= 0 && y2 >= 0 && x1 <= width && y1 <= height && x2 <= width && y2 <= height)
			g.drawLine(x1, y1, x2, y2);
		else
			throw new IllegalArgumentException("直线参数错误！");
	}

	public void paintRec(int x,int y,int width,int height) {
		Graphics g=this.getGraphics();
		// 设置本次绘制的图形填充颜色
		Random random = new Random();
		Color myColor = new Color(random.nextInt(256), random.nextInt(256), random.nextInt(256));
		g.setColor(myColor);
		// 检验参数
		int frameWidth = getWidth();
		int frameHeight = getHeight();
		if (x >= 0 && y >= 0 && width >= 0 && height >= 0 && x + width <= frameWidth && y + height <= frameHeight)
			g.drawRect(x, y, width, height);
		else
			throw new IllegalArgumentException("矩形参数错误！");
	}

	public void paintOval(int x,int y,int width,int height) {
		Graphics g=this.getGraphics();
		// 设置本次绘制的图形填充颜色
		Random random = new Random();
		Color myColor = new Color(random.nextInt(256), random.nextInt(256), random.nextInt(256));
		g.setColor(myColor);
		// 检验参数
		int frameWidth = getWidth();
		int frameHeight = getHeight();
		if (x >= 0 && y >= 0 && width >= 0 && height >= 0 && x + width + 1 <= frameWidth
				&& y + height + 1 <= frameHeight)
			g.drawOval(x, y, width, height);
		else
			throw new IllegalArgumentException("椭圆参数错误！");
	}

}
