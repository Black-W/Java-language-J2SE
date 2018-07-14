package lab13_3;

import java.util.Arrays;
import javax.swing.JTextArea;

public class Array {
	private final int[] array;
	private int writeIndex = 0;
	private final JTextArea textArea;

	// 数组构造函数
	public Array(int size, JTextArea t) {
		array = new int[size];
		textArea = t;
	}

	public synchronized boolean add(int value) {
		int position = writeIndex;
		if (position == array.length)
			return false;
		array[position] = value;
		++writeIndex;
		textArea.append(
				String.format("%s wrote %2d to element %d\n", Thread.currentThread().getName(), value, position));

		return true;
	}

	@Override
	public String toString() {
		return Arrays.toString(array);
	}

}
