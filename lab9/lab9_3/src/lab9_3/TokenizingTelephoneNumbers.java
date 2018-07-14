package lab9_3;

import javax.swing.JOptionPane;

public class TokenizingTelephoneNumbers {

	public static void main(String[] args) {
		// done标记输入是否正确完成
		boolean done = false;
		while (!done) {
			String input = JOptionPane.showInputDialog(null, "请输入电话号码，格式如：(555)555-5555", "输入电话号码");
			if (input.matches("[(（]\\d{3}[)）]\\d{3}-\\d{4}")) {
				done = true;
				String[] tokens = input.split("[（）()-]");
				JOptionPane.showMessageDialog(null, String.format("(区号-电话号码)\n%s-%s%s", tokens[1], tokens[2], tokens[3])
						,"结果",JOptionPane.PLAIN_MESSAGE);
			}
			else {
				JOptionPane.showMessageDialog(null, "号码输入格式错误！","错误",JOptionPane.ERROR_MESSAGE);
			}
		}
		
	}

}
