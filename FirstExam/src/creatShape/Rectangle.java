package creatShape;

/** 
 * @author WangZhenwei
 * @date 2018/04/11
 */

//Rectangle类继承自Shape

public class Rectangle extends Shape {

	// 矩形宽和高
	private double width;
	private double height;

	// 构造函数
	public Rectangle(double width, double height) {
		this.width = width;
		this.height = height;
	}

	// 实现父类的方法，获得名字，计算面积，周长
	@Override
	public String name() {
		return "Rectangle";
	}
	
	@Override
	public double area() {
		return width * height;
	}

	@Override
	public double perimeter() {
		return 2 * (width + height);
	}
}
