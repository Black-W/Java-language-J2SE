package lab3_1;

import java.util.Random;
import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JPanel;

public class DrawPanel extends JPanel {
	// draws 10 random filled shapes in random positions
	public void paintComponent(Graphics g) {
		super.paintComponent(g);

		int width = getWidth();
		int height = getHeight();
		Random random = new Random();

		for (int i = 0; i < 10; i++) {
			// 设置本次绘制的图形填充颜色
			Color myColor = new Color(random.nextInt(256), random.nextInt(256), random.nextInt(256));
			g.setColor(myColor);

			// 产生随机数0或者1，若是0则绘制矩形，否则绘制椭圆
			if (random.nextInt(2) == 0) {
				g.fillRect(random.nextInt(width), random.nextInt(height), random.nextInt(width / 2),
						random.nextInt(height / 2));
			} else {
				g.fillOval(random.nextInt(width), random.nextInt(height), random.nextInt(width / 2),
						random.nextInt(height / 2));
			}
		}
	}
}
