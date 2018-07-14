package lab9_2;

import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.UIManager;

public class Judge {
	
	private static Font font = new Font("微软雅黑", Font.PLAIN, 15);// 字体
	
	public static void main(String[] args) {
		//设置界面风格
		try {
			UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
		} catch (Exception e) {
			e.printStackTrace();
		}
		//设置字体
		UIManager.put("Label.font", font);
		UIManager.put("TextField.font", font);
		UIManager.put("Button.font", font);
		UIManager.put("OptionPane.font", font);
		//设置文本框和按钮
		JFrame frame=new JFrame();
		frame.setLayout(new FlowLayout());
		
		JLabel label1 = new JLabel("用户名：");
		JLabel label2 = new JLabel("密码    ：");
		JLabel label3 = new JLabel("邮箱    ：");
		JTextField account=new JTextField(20);
		JTextField password=new JTextField(20);
		JTextField email=new JTextField(20);
		JButton button1 = new JButton("确认");
		JButton button2 = new JButton("取消");
		//判断输入内容
		button1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String inputAccount = account.getText();
				String inputPassword = password.getText();
				String inputEmail = email.getText();
				String result=new String();
				
				if (inputAccount.matches("[a-zA-Z_][0-9a-zA-Z_]*"))
					result+="用户名格式正确\n";
				else 
					result+="用户名格式错误\n";
				
				if (inputPassword.matches("[0-9a-zA-Z_]{8,}")) 
					result+="密码格式正确\n";
				else 
					result+="密码格式错误\n";
				
				if (inputEmail.matches(".+@\\w+(\\.\\w+)+"))
					result+="邮箱格式正确\n";
				else 
					result+="邮箱格式错误\n";
				JOptionPane.showMessageDialog(frame, result);
			}
		});
		button2.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		frame.add(label1);
		frame.add(account);
		frame.add(label2);
		frame.add(password);
		frame.add(label3);
		frame.add(email);
		frame.add(button1);
		frame.add(button2);
		
		frame.setVisible(true);
		frame.setLocationRelativeTo(null);
		frame.setSize(400, 200);
		frame.setResizable(false);
	}

}
