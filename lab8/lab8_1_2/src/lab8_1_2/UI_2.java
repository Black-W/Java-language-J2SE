package lab8_1_2;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.TextField;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UIManager;

public class UI_2 extends JFrame{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel panel1;
	private JPanel panel2;
	private String []buttonName= {"7","8","9","/","4","5","6","*","1","2","3","-","0",".","=","+"};
	private TextField textfield;
	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
		} catch (Exception e) {
			e.printStackTrace();
		}
		UI_2 test=new UI_2();
		test.setVisible(true);
		test.setSize(340, 250);
		test.setResizable(false);
	}
	public UI_2(){
		super("Calculator");
		setLayout(new BorderLayout());
		panel1=new JPanel(new BorderLayout());
		textfield=new TextField("0");
		textfield.setFont(new Font("微软雅黑", Font.PLAIN, 20));
		textfield.setPreferredSize(new Dimension(330, 300));
		panel1.add(textfield);
		panel2=new JPanel();
		panel2.setLayout(new GridLayout(4, 4,3,3));
		for(String nowName:buttonName) {
			JButton a=new JButton(nowName);
			a.setFont(new Font("微软雅黑", Font.PLAIN, 20));
			panel2.add(a);
		}
		
		add(panel1,BorderLayout.NORTH);
		add(panel2,BorderLayout.CENTER);
	}
}
