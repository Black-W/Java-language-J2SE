package lab13_3;

import java.security.SecureRandom;

public class ArrayTask implements Runnable {
	private static final SecureRandom generator = new SecureRandom();
	private final Array array;

	public ArrayTask(Array a) {
		array = a;
	}

	@Override
	public void run() {
		// 向数组中加入一个1~6的随机数，返回添加的操作描述字符串
		while (array.add(generator.nextInt(6) + 1)) {
		}
		;
	}

}
