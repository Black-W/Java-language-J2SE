package lab8_2;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import javax.swing.JPanel;

/**
 * 画板控制类，实现对图形的绘制控制
 * 
 * @author Wang
 *
 */
public class DrawController extends JPanel {
	private static final long serialVersionUID = 1L;
	private Graphics g; // 画笔
	private Graphics2D g2d; // 画笔
	private static ArrayList<shape> shapes = new ArrayList<shape>(); // 保存已经画的图形
	private int x1, y1; // 起点坐标
	private int x2, y2; // 终点坐标
	private int type = 0; // 画图的种类,0代表直线，1代表矩形，2代表椭圆
	private Boolean filled = true; // 是否使用填充，默认使用
	private Boolean gridient = true; // 是否使用渐变色，默认使用
	private Boolean dashed = true; // 是否使用虚线，默认使用
	private Color color1 = Color.RED; // 颜色1
	private Color color2 = Color.YELLOW; // 颜色2
	private double lineWidth = 10; // 线宽
	private double dashLength = 15; // 虚线长度
	private Boolean undo = false; // 是否在撤销

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		this.g = getGraphics();
		this.g2d = (Graphics2D) getGraphics();
	}

	public void draw() {
		// 先将图片画在图片缓冲区中，在一起画到画板中，避免闪烁
		BufferedImage bf = new BufferedImage(this.getWidth(), this.getHeight(), BufferedImage.TYPE_INT_BGR); // 缓冲图片
		g2d = (Graphics2D) bf.createGraphics(); // 缓冲图片的graphics2D对象
		g2d.setColor(Color.WHITE);// 设置笔刷白色
		g2d.fillRect(0, 0, this.getWidth(), this.getHeight());// 填充整个屏幕

		// 保存当前图形，用于恢复画板
		shape temp = new shape(type, x1, y1, x2, y2, filled, gridient, dashed, color1, color2, lineWidth, dashLength);
		// 绘制已经保存了的图形
		for (shape nowShape : shapes) {
			// 修改重绘的画板参数
			setArgument(nowShape);
			// 设置画笔
			setPait();

			if (nowShape.getType() == 0)
				g2d.draw(new Line2D.Double(x1, y1, x2, y2));
			else if (nowShape.getType() == 1) {
				if (filled == true)
					g2d.fill(new Rectangle2D.Double(Math.min(x1, x2), Math.min(y1, y2), Math.abs(x1 - x2),
							Math.abs(y1 - y2)));
				else
					g2d.draw(new Rectangle2D.Double(Math.min(x1, x2), Math.min(y1, y2), Math.abs(x1 - x2),
							Math.abs(y1 - y2)));
			} else if (nowShape.getType() == 2) {
				if (filled == true)
					g2d.fill(new Ellipse2D.Double(Math.min(x1, x2), Math.min(y1, y2), Math.abs(x1 - x2),
							Math.abs(y1 - y2)));
				else
					g2d.draw(new Ellipse2D.Double(Math.min(x1, x2), Math.min(y1, y2), Math.abs(x1 - x2),
							Math.abs(y1 - y2)));
			}
		}
		// 恢复画板参数
		setArgument(temp);
		
		// 绘制正在拖拽的图形
		// 设置画笔
		setPait();
		if (!undo) {
			if (type == 0) { // 画直线
				g2d.draw(new Line2D.Double(x1, y1, x2, y2));
			} else if (type == 1) // 画矩形
				if (filled == true)
					g2d.fill(new Rectangle2D.Double(Math.min(x1, x2), Math.min(y1, y2), Math.abs(x1 - x2),
							Math.abs(y1 - y2)));
				else
					g2d.draw(new Rectangle2D.Double(Math.min(x1, x2), Math.min(y1, y2), Math.abs(x1 - x2),
							Math.abs(y1 - y2)));
			else if (type == 2) { // 画椭圆
				if (filled == true)
					g2d.fill(new Ellipse2D.Double(Math.min(x1, x2), Math.min(y1, y2), Math.abs(x1 - x2),
							Math.abs(y1 - y2)));
				else
					g2d.draw(new Ellipse2D.Double(Math.min(x1, x2), Math.min(y1, y2), Math.abs(x1 - x2),
							Math.abs(y1 - y2)));
			}
		}
		// 将缓冲区的图片画到画板中
		g.drawImage(bf, 0, 0, this);
	}

	// 设置起点坐标
	public void setBeginPoint(int x, int y) {
		this.x1 = x;
		this.y1 = y;
	}

	// 设置终点坐标
	public void setFinalPoint(int x, int y) {
		this.x2 = x;
		this.y2 = y;
	}

	// 设置是否填充
	public void setFilled(Boolean filled) {
		this.filled = filled;
	}

	// 设置是否渐变
	public void setGridient(Boolean gridient) {
		this.gridient = gridient;
	}

	// 设置是否虚线
	public void setDashed(Boolean dashed) {
		this.dashed = dashed;
	}

	// 设置颜色1
	public void setColor1(Color color1) {
		this.color1 = color1;
	}

	// 设置颜色2
	public void setColor2(Color color2) {
		this.color2 = color2;
	}

	// 设置线宽
	public void setLineWidth(double lineWidth) {
		this.lineWidth = lineWidth;
	}

	// 设置虚线长
	public void setDashLength(double dashLength) {
		this.dashLength = dashLength;
	}

	// 设置画图类型
	public void setType(int type) {
		this.type = type;
	}

	// 设置画笔
	public void setPait() {
		// 虚线设置
		if (dashed == true) {
			float[] dashes = { (float) dashLength }; // specify dash pattern
			g2d.setStroke(
					new BasicStroke((float) lineWidth, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND, 10, dashes, 0));
		} else
			g2d.setStroke(new BasicStroke((float) lineWidth));
		// 渐变色设置
		if (gridient == true)
			g2d.setPaint(new GradientPaint(5, 30, color1, 35, 100, color2, true));
		else
			g2d.setPaint(color1);
	}

	// 根据当前图形改变画板参数
	public void setArgument(shape nowShape) {
		x1 = nowShape.getPoint(0);
		y1 = nowShape.getPoint(1);
		x2 = nowShape.getPoint(2);
		y2 = nowShape.getPoint(3);
		type = nowShape.getType();
		filled = nowShape.getFilled();
		gridient = nowShape.getGridient();
		dashed = nowShape.getDashed();
		color1 = nowShape.getColor1();
		color2 = nowShape.getColor2();
		lineWidth = nowShape.getLineWidth();
		dashLength = nowShape.getDashLength();

	}

	// 保存当前图像
	public void saveShape() {
		shape a = new shape(type, x1, y1, x2, y2, filled, gridient, dashed, color1, color2, lineWidth, dashLength);
		shapes.add(a);
	}

	// 清空所有已经绘制的图形
	public void removaAll() {
		super.paintComponent(g);
		shapes.removeAll(shapes);
	}

	// 撤销
	public void undo() {
		shapes.remove(shapes.size() - 1);
		undo = true;
		draw();
		undo = false;
	}
}
