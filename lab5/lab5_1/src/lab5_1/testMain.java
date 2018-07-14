package lab5_1;

public class testMain {

	public static void main(String[] args) {
		Rational a=new Rational(1, 4);
		Rational b=new Rational(1, 3);
		
		System.out.println("a="+a.toString());
		System.out.println("b="+b.toString());
		
		//输出加减乘除的结果
		System.out.println("a+b="+a.add(b).toString());
		System.out.println("a-b="+a.subtract(b));
		System.out.println("a*b="+a.multiply(b));
		System.out.println("a/b="+a.divide(b));
		
		//输出Comparable接口的compareTo方法结果
		System.out.println("a compareTo b is "+a.compareTo(b));
		
		//用户指定输出格式
		b.printInFloatingpointFormat();
	}

}
