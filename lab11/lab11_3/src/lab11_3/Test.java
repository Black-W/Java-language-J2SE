package lab11_3;

import javax.lang.model.element.Parameterizable;

public class Test {

	public static void main(String[] args) {
		Pair<Integer, Double> test=new Pair<Integer,Double>();
		
		test.setFirst(100);
		
		test.setSecond(99.99);
		
		System.out.println(test.getFirst());
		
		System.out.println(test.getSecond());
	}

}
