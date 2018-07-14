package lab11_2;

public class isEqualTo {

	public static void main(String[] args) {
		Double double1=10.1;
		Double double2=10.1;
		System.out.println(isEqualTo(double1,double2));
		
		Integer int1=10;
		Integer int2=20;
		System.out.println(isEqualTo(int1,int2));
		
		Object obj1=new Object();
		Object obj2=obj1;
		System.out.println(isEqualTo(obj1,obj2));
	}
	
	public static <T> boolean isEqualTo(T value1,T value2) {
		return value1.equals(value2);
	}

}
