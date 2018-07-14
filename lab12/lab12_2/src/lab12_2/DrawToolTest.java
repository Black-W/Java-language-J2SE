package lab12_2;

import javax.swing.JFrame;

public class DrawToolTest {

	public static void main(String[] args) {
		DesktopFrame drawTool=new DesktopFrame();
		drawTool.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		drawTool.setSize(1000,1000);
		drawTool.setLocationRelativeTo(null);
		drawTool.setVisible(true);
	}
}
