package lab5_1;

import java.text.NumberFormat;
import java.util.Scanner;

import javax.xml.stream.events.EndDocument;

public class Rational extends Number implements Comparable<Rational> {
	private long numerator; // 分子
	private long denominator; // 分母

	// 两个重载构造函数
	public Rational() {
		numerator = 0;
		denominator = 1;
	}// end Rational

	public Rational(long numerator, long denominator) {
		if (denominator == 0) {
			System.out.println("Sorry!the Denominator of a Rational can't be zero!");
			System.exit(-1);
		}
		if (denominator < 0) { // 若分母是负数，分子分母同时乘以-1，使分母永远为正，方便后面的计算
			denominator *= -1;
			numerator *= -1;
		}
		long gcd = gcd(numerator, denominator);
		this.numerator = numerator / gcd;
		this.denominator = denominator / gcd;
	}// end Rational

	// 返回两个数的最大公约数
	public long gcd(long num1, long num2) {
		num1 = (num1 >= 0 ? num1 : -num1);
		num2 = (num2 > 0 ? num2 : -num2); // 先确保num1和num2都大于0
		long remainder = 0;
		try {
			remainder = num1 % num2;
		} catch (ArithmeticException e) {
			System.err.println(e);
		}
		while (remainder != 0) {
			num1 = num2;
			num2 = remainder;
			remainder = num1 % num2;
		}
		return num2;
	}// end gcd

	// 将有理数变成最简形式
	public Rational toReducedForm(Rational n) {
		long gcd = gcd(n.numerator, n.denominator);
		n.setDenominator(n.denominator / gcd);
		n.setNumerator(n.numerator / gcd);
		return n;
	}// end toReducedForm

	// 返回该有理数与另一个有理数的和
	public Rational add(Rational secondRational) {
		long addDenominator = this.denominator * secondRational.denominator;
		long addNumerator = this.numerator * secondRational.denominator + secondRational.numerator * this.denominator;
		Rational result = new Rational(addNumerator, addDenominator);
		return result;
	}// end add

	// 返回该有理数与另一个有理数的差
	public Rational subtract(Rational secondRational) {
		long subDenominator = this.denominator * secondRational.denominator;
		long subNumerator = this.numerator * secondRational.denominator - secondRational.numerator * this.denominator;
		Rational result = new Rational(subNumerator, subDenominator);
		return result;
	}//end subtract

	// 返回该有理数与另一个有理数的积
	public Rational multiply(Rational secondRational) {
		long subDenominator = this.denominator * secondRational.denominator;
		long subNumerator = this.numerator * secondRational.numerator;
		Rational result = new Rational(subNumerator, subDenominator);
		return result;
	}//end multipy

	// 返回该有理数与另一个有理数的商
	public Rational divide(Rational secondRational) {
		if(secondRational.numerator==0) {
			System.out.println("the Divisor can't be zero!");
			System.exit(-1);
		}
		//变成乘以第二个数的倒数
		long divDenominator = this.denominator * secondRational.numerator;
		long divNumerator = this.numerator * secondRational.denominator;
		Rational result = new Rational(divNumerator, divDenominator);
		return result;
	}//end divide

	// 返回形如“分子/分母”的字符串
	@Override
	public String toString() {
		String s;
		if (denominator == 1)
			s = String.valueOf(numerator);
		else
			s = String.valueOf(numerator) + '/' + String.valueOf(denominator);
		return s;
	}//end toString
	
	//按用户指定的格式按浮点型输出
	public void printInFloatingpointFormat() {
		System.out.println("请输入要精确到小数点右边的位数:");
		Scanner input=new Scanner(System.in);
		int digits=input.nextInt();
		//设置格式化输出
		NumberFormat format=NumberFormat.getNumberInstance() ;
		format.setMaximumFractionDigits(digits);
		
		System.out.println(format.format((double)numerator/denominator));
		input.close();
	}//end printInFloatingpointFormat
	
	// get set 分子和分母
	public long getNumerator() {
		return numerator;
	}

	public long getDenominator() {
		return denominator;
	}

	public void setNumerator(long numerator) {
		this.numerator = numerator;
	}

	public void setDenominator(long denominator) {
		if (denominator == 0) {
			System.err.println("Sorry!the Denominator of a rational can't be zero!");
			System.exit(-1);
		}
		this.denominator = denominator;
	}
	//end get/set

	// 下面是对Number类的抽象方法的重写
	@Override
	public int compareTo(Rational secondRational) {
		Rational result=this.subtract(secondRational);
		if(result.numerator<0)return -1;
		else if(result.numerator==0)return 0;
		else return 1;
	}

	@Override
	public double doubleValue() {
		return (double)numerator/denominator;
	}

	@Override
	public float floatValue() {
		return (float)numerator/denominator;
	}

	@Override
	public int intValue() {
		return (int)(numerator/denominator);
	}

	@Override
	public long longValue() {
		return numerator/denominator;
	}

}
