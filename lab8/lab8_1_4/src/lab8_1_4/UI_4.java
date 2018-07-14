package lab8_1_4;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import javax.swing.Box;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.UIManager;

public class UI_4 extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JCheckBox checkbox1;
	private JCheckBox checkbox2;
	private JCheckBox checkbox3;
	private JCheckBox checkbox4;
	private JRadioButton radiobutton1;
	private JRadioButton radiobutton2;
	private JRadioButton radiobutton3;
	private JTextField textfield1;
	private JTextField textfield2;
	private JTextField textfield3;
	private JButton button1;
	private JButton button2;
	private JButton button3;
	private JButton button4;
	private JLabel label1;
	private JLabel label2;
	private JComboBox<String> comboBox;
	private String names[] = { "High", "Low", "Center" };
	private JPanel panel1;
	private JPanel panel2;
	private JPanel panel3;
	private JPanel panel4;
	private Font font = new Font("微软雅黑", Font.PLAIN, 15);

	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
		} catch (Exception e) {
			e.printStackTrace();
		}
		UI_4 test = new UI_4();
		test.setVisible(true);
		test.setLocation(500, 500);
		test.setSize(580, 220);
		test.setResizable(false);
	}

	public UI_4() {
		super("Printer");
		setLayout(new BorderLayout());
		panel1 = new JPanel();
		panel2 = new JPanel(new BorderLayout());
		panel3 = new JPanel();
		panel4 = new JPanel();
		panel2.add(panel3, BorderLayout.NORTH);
		panel2.add(panel4, BorderLayout.SOUTH);
		add(panel1, BorderLayout.EAST);
		add(panel2, BorderLayout.CENTER);

		// 设置右边的按钮
		button1 = new JButton("Ok");
		button2 = new JButton("Cancel");
		button3 = new JButton("Setup");
		button4 = new JButton("Help");
		button1.setFont(font);
		button2.setFont(font);
		button3.setFont(font);
		button4.setFont(font);
		{
			Box box = Box.createVerticalBox();
			box.add(Box.createVerticalStrut(5));
			box.add(button1);
			box.add(Box.createVerticalStrut(10));
			box.add(button2);
			box.add(Box.createVerticalStrut(10));
			box.add(button3);
			box.add(Box.createVerticalStrut(10));
			box.add(button4);
			box.add(Box.createVerticalStrut(10));
			panel1.add(box);
		}

		// 设置上面的标签
		label1 = new JLabel("Printer:MyPrinter");
		label1.setFont(font);
		panel3.add(label1, FlowLayout.LEFT);

		// 设置中间的文本区和复选框单选框
		textfield1 = new JTextField();
		textfield2 = new JTextField();
		textfield3 = new JTextField();
		checkbox1 = new JCheckBox("Image");
		checkbox2 = new JCheckBox("Text");
		checkbox3 = new JCheckBox("Code");
		checkbox1.setFont(font);
		checkbox2.setFont(font);
		checkbox3.setFont(font);
		radiobutton1 = new JRadioButton("Selection");
		radiobutton2 = new JRadioButton("ALL");
		radiobutton2.setSelected(true);
		radiobutton3 = new JRadioButton("Applet");
		radiobutton1.setFont(font);
		radiobutton2.setFont(font);
		radiobutton3.setFont(font);
		ButtonGroup group = new ButtonGroup();
		group.add(radiobutton1);
		group.add(radiobutton2);
		group.add(radiobutton3);
		{
			Box box = Box.createHorizontalBox();
			Box box1 = Box.createVerticalBox();
			Box box2 = Box.createVerticalBox();
			box1.add(checkbox1);
			box1.add(checkbox2);
			box1.add(checkbox3);
			box2.add(radiobutton1);
			box2.add(radiobutton2);
			box2.add(radiobutton3);
			box.add(Box.createHorizontalStrut(10));
			box.add(textfield1);
			box.add(box1);
			box.add(textfield2);
			box.add(box2);
			box.add(textfield3);
			panel2.add(box, BorderLayout.CENTER);
		}
		// 设置下面的标签、下拉框和复选框
		label2 = new JLabel("Print Quality:");
		label2.setFont(font);
		comboBox = new JComboBox<String>(names);
		comboBox.setBackground(Color.WHITE);
		comboBox.setFont(font);
		checkbox4 = new JCheckBox("Print to File");
		checkbox4.setFont(font);
		panel4.add(label2);
		panel4.add(comboBox);
		panel4.add(checkbox4);
	}

}
