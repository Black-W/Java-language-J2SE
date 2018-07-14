package lab8_2;

import java.awt.Color;

/**
 * 图形类保存了已经画的图形的信息
 * 
 * @author Wang
 *
 */

public class shape {
	private int type; // 图形类别
	private Boolean filled; // 是否使用填充，默认使用
	private Boolean gridient; // 使用渐变色
	private Boolean dashed; // 使用虚线
	private Color color1; // 颜色1
	private Color color2; // 颜色2
	private double lineWidth; // 线宽
	private double dashLength; // 虚线长度
	private int point[] = new int[4]; // 参数

	public shape(int type, int x1, int y1, int x2, int y2, Boolean filled, Boolean gridient, Boolean dashed,
			Color color1, Color color2, double lineWidth, double dashLength) {
		this.type = type;
		this.filled = filled;
		this.gridient = gridient;
		this.dashed = dashed;
		this.color1 = color1;
		this.color2 = color2;
		this.lineWidth = lineWidth;
		this.dashLength = dashLength;
		point[0] = x1;
		point[1] = y1;
		point[2] = x2;
		point[3] = y2;
	}

	public int getType() {
		return type;
	}

	public int getPoint(int i) {
		return point[i];
	}

	public Boolean getFilled() {
		return filled;
	}

	public Boolean getGridient() {
		return gridient;
	}

	public Boolean getDashed() {
		return dashed;
	}

	public Color getColor1() {
		return color1;
	}

	public Color getColor2() {
		return color2;
	}

	public double getLineWidth() {
		return lineWidth;
	}

	public double getDashLength() {
		return dashLength;
	}
}
