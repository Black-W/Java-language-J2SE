package lab7_1;

import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class LoginFrame extends JFrame {
	private final Label label1;
	private final Label label2;
	private final JTextField name;
	private final JPasswordField password;
	private final JButton button1;
	private final JButton button2;
	private static Font font = new Font("consola", Font.PLAIN, 15);
	public LoginFrame() {
		super("登陆");
		setResizable(false); // 固定窗口大小
		setLayout(new FlowLayout());
		label1 = new Label("    account ：");
		label2 = new Label("password ：");
		name = new JTextField(20);
		password = new JPasswordField(20);
		button1 = new JButton("ok");
		button2 = new JButton("cancel");
		
		label1.setFont(font);
		label2.setFont(font);
		name.setFont(font);
		password.setFont(font);
		button1.setFont(font);
		button2.setFont(font);
		
		button1.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String inputName = name.getText();
				String inputPassword = String.valueOf(password.getPassword());
				if (inputName.equals("admin") && inputPassword.equals("admin"))
					JOptionPane.showMessageDialog(LoginFrame.this, "login success");
				else if (!inputName.equals("admin") || !inputPassword.equals("admin")) {
					if (inputName.length() == 0)
						JOptionPane.showMessageDialog(LoginFrame.this, "account can't be empty!", "ERROR",
								JOptionPane.ERROR_MESSAGE);
					else if (inputPassword.length() == 0)
						JOptionPane.showMessageDialog(LoginFrame.this, "password can't be empty!", "ERROR",
								JOptionPane.ERROR_MESSAGE);
					else if (inputPassword.length() < 3)
						JOptionPane.showMessageDialog(LoginFrame.this, "the length of password should above 3!",
								"ERROR", JOptionPane.ERROR_MESSAGE);
					else if (!inputName.equals("admin"))
						JOptionPane.showMessageDialog(LoginFrame.this, "account is not exist!", "ERROR",
								JOptionPane.ERROR_MESSAGE);
					else if (!inputPassword.equals("admin"))
						JOptionPane.showMessageDialog(LoginFrame.this, "password is wrong!", "ERROR",
								JOptionPane.ERROR_MESSAGE);
				}

			}
		});

		button2.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);

			}
		});
		add(label1);
		add(name);
		add(label2);
		add(password);
		add(button1);
		add(button2);
	}
}
