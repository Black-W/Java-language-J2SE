package lab3_1;

import javax.swing.JFrame;

public class DrawPanel_main {

	public static void main(String[] args) {
		DrawPanel panel= new DrawPanel();
		
		JFrame application=new JFrame("随机绘制10个天填充图形");
		
		application.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		application.add(panel);
		application.setSize(500,500);
		application.setVisible(true);

	}

}
