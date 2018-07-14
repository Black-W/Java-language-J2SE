package creatShape;

/** 
 * @author WangZhenwei
 * @date 2018/04/11
 */

//Circle类继承自Shape

public class Circle extends Shape{
	
	//圆形半径
	private double radius;
	
	//构造函数
	public Circle(double radius) {
		this.radius=radius;
	}
	
	//实现父类的方法，获得名字，计算面积，周长
	@Override
	public String name() {
		return "Circle";
	}
	
	@Override
	public double area() {
		return Math.PI*radius*radius;
	}

	@Override
	public double perimeter() {
		return 2*Math.PI*radius;
	}

}
