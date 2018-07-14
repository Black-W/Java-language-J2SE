package lab8_1_3;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UIManager;

public class UI_3 extends JFrame{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JComboBox<String> comboBox;
	private String []names= {"RED","BLUE","YELLO","BLACK","WHITE"};
	private JCheckBox checkbox1;
	private JCheckBox checkbox2;
	private JPanel panel1;
	private JPanel panel2;
	private JButton button1;
	private JButton button2;
	
	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
		} catch (Exception e) {
			e.printStackTrace();
		}
		UI_3 test = new UI_3();
		test.setVisible(true);
		test.setLocation(500, 500);
		test.setSize(400, 170);
		test.setResizable(false);
	}
	public UI_3() {
		super("Colorselect");
		setLayout(new BorderLayout());
		//设置下拉框
		comboBox=new JComboBox<String>(names);
		comboBox.setMaximumRowCount(3);	//设置下拉显示个数
		comboBox.setBackground(Color.white);
		comboBox.setFont(new Font("微软雅黑", Font.PLAIN, 20));
		add(comboBox,BorderLayout.NORTH);
		//设置复选框
		checkbox1 = new JCheckBox("Background");
		checkbox2 = new JCheckBox("Foreground");
		checkbox1.setFont(new Font("微软雅黑", Font.PLAIN, 20));
		checkbox2.setFont(new Font("微软雅黑", Font.PLAIN, 20));
		panel1=new JPanel(new FlowLayout());
		panel1.add(checkbox1);
		panel1.add(checkbox2);
		add(panel1,BorderLayout.CENTER);
		//设置按钮
		button1 = new JButton("Ok");
		button2 = new JButton("Cancel");
		button1.setFont(new Font("微软雅黑", Font.PLAIN, 20));
		button2.setFont(new Font("微软雅黑", Font.PLAIN, 20));
		panel2=new JPanel(new FlowLayout());
		panel2.add(button1);
		panel2.add(button2);
		add(panel2, BorderLayout.SOUTH);
	}
}
