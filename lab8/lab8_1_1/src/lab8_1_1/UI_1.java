package lab8_1_1;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.UIManager;

public class UI_1 extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JCheckBox checkbox1;
	private JCheckBox checkbox2;
	private JTextField textfield1;
	private JTextField textfield2;
	private JButton button1;
	private JButton button2;
	private JButton button3;
	private JPanel panel1;
	private JPanel panel2;
	private JPanel panel3;

	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
		} catch (Exception e) {
			e.printStackTrace();
		}
		UI_1 test = new UI_1();
		test.setVisible(true);
		test.setLocation(500, 500);
		test.setSize(300, 150);
		test.setResizable(false);
	}

	public UI_1() {
		super("Align");
		setLayout(new BorderLayout());
		panel1 = new JPanel();
		panel2 = new JPanel();
		panel3 = new JPanel();
		//添加左边的组件
		checkbox1 = new JCheckBox("Snap to Crid");
		checkbox2 = new JCheckBox("Show Crid");
		checkbox1.setFont(new Font("微软雅黑", Font.PLAIN, 15));
		checkbox2.setFont(new Font("微软雅黑", Font.PLAIN, 15));
		{
			Box box = Box.createVerticalBox(); // create box
			box.add(Box.createVerticalStrut(20));
			box.add(checkbox1);
			box.add(checkbox2);
			panel1.add(box);
		}
		//添加中间部分组件
		textfield1 = new JTextField("8");
		textfield2 = new JTextField("8");
		textfield1.setFont(new Font("微软雅黑", Font.PLAIN, 10));
		textfield2.setFont(new Font("微软雅黑", Font.PLAIN, 10));
		textfield1.setPreferredSize(new Dimension(30, 10));
		textfield2.setPreferredSize(new Dimension(30, 10));
		{
			Box box = Box.createVerticalBox(); // create box
			Box box1 = Box.createHorizontalBox(); // create box1
			Box box2 = Box.createHorizontalBox(); // create box2
			JLabel aJLabel=new JLabel("X:");
			aJLabel.setFont(new Font("微软雅黑", Font.PLAIN, 15));
			JLabel bJLabel=new JLabel("Y:");
			bJLabel.setFont(new Font("微软雅黑", Font.PLAIN, 15));
			box1.add(aJLabel);
			box1.add(Box.createHorizontalStrut(10));
			box1.add(textfield1);
			box2.add(bJLabel);
			box2.add(Box.createHorizontalStrut(10));
			box2.add(textfield2);
			box.add(Box.createVerticalStrut(15));
			box.add(box1);
			box.add(Box.createVerticalStrut(20));
			box.add(box2);
			panel2.add(box);
		}
		//添加左边控件
		button1 = new JButton("Ok");
		button2 = new JButton("Cancel");
		button3 = new JButton("Help");
		button1.setFont(new Font("微软雅黑", Font.PLAIN, 15));
		button2.setFont(new Font("微软雅黑", Font.PLAIN, 15));
		button3.setFont(new Font("微软雅黑", Font.PLAIN, 15));
		{
			Box box = Box.createVerticalBox(); // create box
			box.add(button1);
			box.add(Box.createVerticalStrut(5));
			box.add(button2);
			box.add(Box.createVerticalStrut(5));
			box.add(button3);
			panel3.add(box);
		}
		add(panel1, BorderLayout.WEST);
		add(panel2, BorderLayout.CENTER);
		add(panel3, BorderLayout.EAST);
	}
}
