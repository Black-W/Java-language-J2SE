package Test;

import java.awt.Graphics;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import ColorMap.*;

public class Test extends JPanel {
	private static final long serialVersionUID = 1L;

	public static void main(String[] args) {
		JFrame frame = new JFrame();
		Test test = new Test();
		frame.add(test);
		frame.setVisible(true);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(300, 300);
		String input;
		do {
			input = JOptionPane.showInputDialog("请输入颜色（大写）");
			test.draw(input);
		} while (input != null);

	}

	public void draw(String input) {
		if(input==null)return;
		Graphics g = getGraphics();
		ColorMap colorMap = new ColorMap();
		if (colorMap.contains(input)) {
			g.setColor(colorMap.getColor(input));
			g.fillRect(100, 100, 100, 100);
		} else
			JOptionPane.showMessageDialog(null, "不存在该颜色");
	}
}
