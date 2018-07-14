package lab4_4;

public class main {

	public static void main(String[] args) {
		Time2 myTime2=new Time2(12,30,30);
		System.out.println(myTime2.toUniversalString());
		myTime2.tick();
		System.out.println(myTime2.toUniversalString());
		myTime2.incrementMinute();
		System.out.println(myTime2.toUniversalString());
		myTime2.incrementHour();
		System.out.println(myTime2.toUniversalString());
		myTime2.setTime(23, 59, 59);
		System.out.println(myTime2.toString());
		System.out.println(myTime2.toUniversalString());
		myTime2.tick();
		System.out.println(myTime2.toString());
		System.out.println(myTime2.toUniversalString());
	}

}
