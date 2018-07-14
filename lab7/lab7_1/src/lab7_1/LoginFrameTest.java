package lab7_1;

import javax.swing.JFrame;
import javax.swing.UIManager;

public class LoginFrameTest {

	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
		} catch (Exception e) {
			e.printStackTrace();
		}
		LoginFrame frame = new LoginFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocation(800, 500);
		frame.setSize(400, 160);
		frame.setVisible(true);
	}

}
