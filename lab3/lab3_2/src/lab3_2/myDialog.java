package lab3_2;

import javax.swing.JOptionPane;

public class myDialog {

	public static void main(String[] args) {
		// 确认对话框
		JOptionPane.showConfirmDialog(null, "确定保存吗", "温馨提示", JOptionPane.YES_NO_OPTION);
		// 错误对话框
		JOptionPane.showMessageDialog(null, "在对话框内显示的描述性的文字", "标题条字符串", JOptionPane.ERROR_MESSAGE);
		// 选项对话框
		Object[] options = { "帅", "很帅", "超级帅" };
		JOptionPane.showOptionDialog(null, "你帅吗", "帅不帅", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null,
				options, options[0]);
		// 输入对话框
		JOptionPane.showInputDialog(null, "请输入：\n", "输入对话框", JOptionPane.PLAIN_MESSAGE);
		// 下拉选择对话框
		Object[] options1 = { "男", "女" };
		JOptionPane.showInputDialog(null, "请选择你的性别", "性别选择", JOptionPane.INFORMATION_MESSAGE, null, options1,
				options1[0]);

	}

}
