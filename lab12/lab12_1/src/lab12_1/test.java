package lab12_1;

import javax.swing.JFrame;

public class test {
	public static void main(String[] args) {
		
		JFrame frame=new JFrame("JSliderTest");
		JSliderPanel sp=new JSliderPanel();
		frame.add(sp);
		frame.setVisible(true);
		frame.setSize(400, 120);
	}
}
