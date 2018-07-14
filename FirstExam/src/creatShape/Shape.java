package creatShape;

/**
 * @author WangZhenwei
 * @date 2018/04/11
 */

// 定义一个抽象类 Shape

public abstract class Shape {
	// 图形颜色
	private int color[] = new int[3];

	// 定义抽象方法 name(),返回图形名称
	public abstract String name();

	// 定义抽象方法 area(),返回图形面积
	public abstract double area();

	// 定义抽象方法 perimeter(),返回图形周长
	public abstract double perimeter();

	// 定义方法 setColor(),设置随机颜色
	public void setColor(int r, int g, int b) {
		color[0] = r;
		color[1] = g;
		color[2] = b;
	}

	// 定义获取颜色的方法，返回颜色的RGB值
	public int getRed() {
		return color[0];
	}

	public int getGreen() {
		return color[1];
	}

	public int getBlue() {
		return color[2];
	}
}
