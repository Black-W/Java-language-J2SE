package mailMerge;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Formatter;
import java.util.Scanner;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

/**
 * 
 * 邮件合并软件控制类 ，实现了对UI界面监听器中的具体操作方法，对用户操作进行统一实现和管理
 * 
 * @author 王振伟
 * @email 664129552@qq.com
 * @date 2018-05-30
 *
 */
public class Controller {
	// 控制的界面
	private UI ui;

	// 标志是否已经替换过信件的占位符
	private boolean replaced = false;

	// 设置控制器控制的界面
	public Controller(UI ui) {
		this.ui = ui;
	}

	/**
	 * 读取指定模板
	 * 
	 * @param i
	 *            模板序号
	 */
	public void readTemplate(int i) {
		// 输入流
		Scanner inputTemp = null;
		Scanner inputSendTo = null;

		try {
			// 打开模板文件
			inputTemp = new Scanner(new File("Template" + i + ".txt"));

			// 打开发送信息文件
			inputSendTo = new Scanner(new File("sendToInfo.txt"));

			// 存放模板内容
			String text = "";

			// 读取模板信息
			while (inputTemp.hasNext()) {
				text += (inputTemp.nextLine() + "\n");
			}

			// 修改信件内容面板
			ui.textArea.setText(text);

			// 读取发送信息
			if (inputSendTo.hasNext())
				ui.nameText.setText(inputSendTo.next());
			if (inputSendTo.hasNext())
				ui.ageText.setText(inputSendTo.next());
			if (inputSendTo.hasNext())
				ui.genderText.setText(inputSendTo.next());

			// 修改替换标志
			replaced = false;
		} catch (SecurityException securityException) {
			// 捕获安全异常
			System.err.println("error");
		} catch (FileNotFoundException e) {
			// 捕获文件找不到异常
			JOptionPane.showMessageDialog(ui, "文件不存在！");
		} finally {
			// 关闭输入流
			if (inputTemp != null)
				inputTemp.close();
			if (inputSendTo != null)
				inputSendTo.close();
		}

	}

	/**
	 * 替换占位符
	 */
	public void replacepPlaceholders() {
		// 如果为空则拒绝替换
		if (ui.nameText.getText().equals("") || ui.ageText.getText().equals("") || ui.genderText.getText().equals("")) {
			JOptionPane.showMessageDialog(ui, "接收人的信息不完整！");
			return;
		}

		// 开始替换
		String s = ui.textArea.getText();
		int i = s.indexOf("<<N>>");
		if (i >= 0)
			ui.textArea.replaceRange(ui.nameText.getText(), i, i + 5);

		s = ui.textArea.getText();
		i = s.indexOf("<<A>>");
		if (i >= 0)
			ui.textArea.replaceRange(ui.ageText.getText(), i, i + 5);

		s = ui.textArea.getText();
		i = s.indexOf("<<G>>");
		if (i >= 0)
			ui.textArea.replaceRange(ui.genderText.getText(), i, i + 5);

		// 提示成功
		JOptionPane.showMessageDialog(ui, "替换成功");

		// 修改替换标志
		replaced = true;
	}

	/**
	 * 将信件内容保存到文件，并保存新的接收人信息
	 */
	public void saveFile() {
		// 如果新建内容为空拒绝保存
		if (ui.textArea.getText().equals("")) {
			JOptionPane.showMessageDialog(ui, "请先选择模板");
			return;
		}

		// 如果没有替换占位符拒绝保存
		if (!replaced) {
			JOptionPane.showMessageDialog(ui, "请先替换模板中的占位符");
			return;
		}

		// 文件选择器
		JFileChooser fileChooser = new JFileChooser();

		// 记录文件选择器的选择结果
		int result = fileChooser.showSaveDialog(ui);

		// 判断选择结果
		if (result == JFileChooser.APPROVE_OPTION) { // 成功选择文件
			// 获取用户所选的文件
			File temp = fileChooser.getSelectedFile();

			// 判断文件是否已经存在
			if (saveExistedFile(temp)) {// 可以保存
				// 输出流
				Formatter output = null;
				Formatter output2 = null;
				try {
					// 保存信件内容
					output = new Formatter(temp);
					output.format("%s", ui.textArea.getText());

					// 保存新的接收人信息
					output2 = new Formatter("sendToInfo.txt");
					output2.format("%s %s %s", ui.nameText.getText(), ui.ageText.getText(), ui.genderText.getText());

					// 输出提示信息
					JOptionPane.showMessageDialog(ui, "文件保存成功！");
				} catch (FileNotFoundException e) {
					// 输出错误信息
					JOptionPane.showMessageDialog(ui, "文件保存失败！");
				} finally {
					// 关闭输出流
					if (output != null)
						output.close();
					if (output2 != null)
						output2.close();
				}
			} else // 不可以保存
				return;
		}
	}

	/**
	 * 判断文件是否存在，以及是否替换
	 * 
	 * @param temp
	 *            要检查的文件
	 * @return 可以保存返回true，不可以返回false
	 */
	private boolean saveExistedFile(File temp) {
		// 如果文件已经存在，询问
		if (temp.exists()) {
			int choose = JOptionPane.showConfirmDialog(ui, temp.getName() + "文件已经存在，是否覆盖？");
			if (choose == JOptionPane.YES_OPTION)
				return true;
			else if (choose == JOptionPane.NO_OPTION) {
				return false;
			}
		}

		// 如果文件不存在，直接保存
		return true;
	}

	/**
	 * 退出方式
	 */
	public void exit() {
		// 询问是否退出
		int choose = JOptionPane.showConfirmDialog(ui, "确认退出?");
		if (choose == JOptionPane.YES_OPTION)
			System.exit(0);
		else if (choose == JOptionPane.NO_OPTION)
			return;
	}
}
