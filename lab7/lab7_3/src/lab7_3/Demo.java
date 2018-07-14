package lab7_3;

import javax.swing.UIManager;

public class Demo {
	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
		} catch (Exception e) {
			e.printStackTrace();
		}
		KeyboardGame newGame = new KeyboardGame();
		newGame.setDefaultCloseOperation(KeyboardGame.EXIT_ON_CLOSE);
		newGame.setLocation(800, 450);
		newGame.setSize(480, 210);
		newGame.setResizable(false);
		newGame.setVisible(true);
	}
}
