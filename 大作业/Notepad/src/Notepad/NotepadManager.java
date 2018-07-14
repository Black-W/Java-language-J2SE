package Notepad;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.Transferable;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.BadLocationException;

import FontDialog.FontDialog;

/**
 * 
 * 记事本管理器类 ，实现了对UI界面监听器中的具体操作方法，对用户操作进行统一实现和管理
 * 
 * @author 王振伟
 * @email 664129552@qq.com
 * @date 2018-05-15
 *
 */

public class NotepadManager {
	// 是否已经被修改过
	protected boolean isEdited = false;
	// 选中的文件
	protected File nowFile = null;
	// 编码方式，默认为GBK
	protected String encoding = "GBK";
	// 要管理的界面
	private NotepadUI UI;
	// 文件对话框
	private JFileChooser fileChooser = new JFileChooser();
	// 标记是否保存成功
	private boolean saveSuccess = false;
	// 要查找的内容
	private String searchText = "";
	// 要替换的内容
	private String replaceText = "";
	// 查找下一个按钮
	private JButton searchNext;
	// 替换按钮
	private JButton replace;
	// 全部替换按钮
	private JButton replaceAll;
	// 查找区分大小写
	private JCheckBox matchCase;
	// 查找方向，向上
	private JRadioButton up;
	// 查找方向，向下
	private JRadioButton down;

	/**
	 * 设置要管理的UI
	 * 
	 * @param UI
	 */
	public void setUI(NotepadUI UI) {
		this.UI = UI;
	}// end setUI

	/**
	 * 新建
	 */
	public void createFile() {
		// 原文未修改，直接新建
		if (!isEdited) {
			UI.textArea.setText("");
		}
		// 原文已经修改则询问用户是否保存修改后的信息
		else {
			int choose = askSave();
			// 选择是
			if (choose == JOptionPane.YES_OPTION) {
				saveFile();
				if (saveSuccess) {
					UI.textArea.setText("");
					saveSuccess = false;// 重置标记
				} else
					return;
				// 选择否
			} else if (choose == JOptionPane.NO_OPTION) {
				UI.textArea.setText("");
			} else
				return;
		}

		// 更新标题
		UI.fileName = "无标题";
		updateTitle();
		isEdited = false;
	}// end createFile

	/**
	 * 打开文件
	 */
	public void openFile() {
		int result = -1; // 记录文件选择器的选择结果

		// 如果没有未保存的文本，直接打开文件选择器
		if (!isEdited) {
			result = fileChooser.showOpenDialog(UI);
		} else { // 否则询问用户是否先保存
			int choose = askSave();
			if (choose == JOptionPane.YES_OPTION) {
				saveFile();
				if (saveSuccess) {
					result = fileChooser.showOpenDialog(UI);
					saveSuccess = false;
				} else
					return;
			} else if (choose == JOptionPane.NO_OPTION) {
				result = fileChooser.showOpenDialog(UI);
			} else
				return;
		}

		// 根据文件选择器的选择结果进行操作
		if (result == JFileChooser.APPROVE_OPTION) { // 成功选择文件
			// 获取用户所选的文件
			nowFile = fileChooser.getSelectedFile();
			// 判断文件是否存在,如果存在则读取文件
			if (nowFile.exists()) {
				// 把原来的内容清空
				UI.textArea.setText("");
				UI.fileName = nowFile.getName();

				// 按字节流方式读取文件
				File file = new File(nowFile.getPath());
				Long fileLength = file.length();
				byte[] fileContent = new byte[fileLength.intValue()];
				try {
					FileInputStream in = new FileInputStream(file);
					in.read(fileContent);
					String text = new String(fileContent, encoding);
					UI.textArea.append(text);
					in.close();
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				} // 读取完成

				// 更新标题
				updateTitle();
				isEdited = false;
			} else {
				JOptionPane.showMessageDialog(null, "您选择的文件不存在，请检查！");
			}
		}
	}// end openFile

	/**
	 * 保存文件
	 */
	public void saveFile() {
		// 如果当前文件路径存在，直接保存
		if (nowFile != null) {
			outFile();
		} else { // 当前不存在，则选择路径保存
			saveForFile();
		}
	}// end saveFile

	/**
	 * 另存为
	 */
	public void saveForFile() {
		// 记录文件选择器的选择结果
		int result = -1;
		result = fileChooser.showSaveDialog(UI);

		if (result == JFileChooser.APPROVE_OPTION) { // 成功选择文件
			// 获取用户所选的文件
			File temp = fileChooser.getSelectedFile();
			// 判断文件是否已经存在
			if (saveExistedFile(temp)) {// 可以保存
				nowFile = temp;
				outFile();
			} else // 不可以保存
				return;
		}
	}// end saveForFile

	/**
	 * 保存已经存在的文件
	 * 
	 * @param temp
	 *            需要检查是否存在的文件
	 * @return 存在返回true，不存在返回false
	 */
	private boolean saveExistedFile(File temp) {
		// 如果文件已经存在，询问
		if (temp.exists()) {
			int choose = JOptionPane.showConfirmDialog(UI, temp.getName() + "文件已经存在，是否覆盖？");
			if (choose == JOptionPane.YES_OPTION)
				return true;
			else if (choose == JOptionPane.NO_OPTION) {
				saveForFile();
				return false;
			} else
				return false;
		} else // 不存在直接保存
			return true;
	}// end saveExistedFile

	/**
	 * 保存到nowFile的位置
	 */
	private void outFile() {
		UI.fileName = nowFile.getName();

		PrintWriter printWriter;
		try {
			FileOutputStream out = new FileOutputStream(nowFile);
			OutputStreamWriter writer = new OutputStreamWriter(out, encoding);
			printWriter = new PrintWriter(writer);
			printWriter.print(UI.textArea.getText());
			printWriter.close();
		} catch (IOException e) {
			e.printStackTrace();
		} // 保存完成

		// 更新标题
		updateTitle();
		isEdited = false;
		saveSuccess = true;
	}// end outFile

	/**
	 * 退出
	 */
	public void exit() {
		if (!isEdited) {
			System.exit(0);
		} else {
			// 获取用户点击的信息
			int choose = askSave();
			if (choose == JOptionPane.YES_OPTION) {
				saveFile();
				System.exit(0);
			} else if (choose == JOptionPane.NO_OPTION) {
				System.exit(0);
			} else
				return;
		}
	}// end exit

	/**
	 * 单步撤销
	 */
	public void undo() {
		if (UI.undoManager.canUndoOrRedo()) {
			UI.undoManager.undoOrRedo();
			// 若不能再撤销，菜单失效
			if (!UI.undoManager.canUndoOrRedo()) {
				UI.undoItem.setEnabled(false);
				UI.popupUndoItem.setEnabled(false);
			}
		}
	}// end undo

	/**
	 * 复制
	 */
	public void copy() {
		// 获得选中的文本
		Transferable trans = new StringSelection(UI.textArea.getSelectedText());
		// 把文本内容设置到系统剪贴板
		UI.clipboard.setContents(trans, null);
	}// end copy

	/**
	 * 剪切
	 */
	public void cut() {
		copy();
		UI.textArea.replaceSelection("");
		UI.setEnabledAll();
	}// end cut

	/**
	 * 粘贴
	 */
	public void paste() {
		Transferable trans = UI.clipboard.getContents(null);
		if (trans != null) {
			// 判断剪贴板中的内容是否支持文本
			if (trans.isDataFlavorSupported(DataFlavor.stringFlavor)) {
				try {
					// 获取剪贴板中的文本内容
					String text = (String) trans.getTransferData(DataFlavor.stringFlavor);
					UI.textArea.replaceSelection(text);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}// end paste

	/**
	 * 删除
	 */
	public void delete() {
		UI.textArea.replaceSelection("");
		UI.setEnabledAll();
	}// end delete

	/**
	 * 查找
	 */
	public void search() {
		// 创建JDialog窗体
		JDialog findDialog = new JDialog(UI, "查找", false);
		Container container = findDialog.getContentPane();
		container.setLayout(new FlowLayout());

		// 输入查找内容
		JLabel searchContentLabel = new JLabel("查找内容:");
		JTextField findText = new JTextField(searchText, 22);
		findText.selectAll();

		// "查找下一个"按钮
		searchNext = new JButton("查找下一个");
		searchNext.setPreferredSize(new Dimension(110, 30));
		if (searchText.equals(""))
			searchNext.setEnabled(false);
		else
			searchNext.setEnabled(true);

		// "取消"按钮
		JButton cancel = new JButton("取消");
		cancel.setPreferredSize(new Dimension(110, 30));

		// 复选框，是否区分大小写
		matchCase = new JCheckBox("区分大小写");

		// 单选框，选择查找方向
		ButtonGroup group = new ButtonGroup();
		up = new JRadioButton("向上");
		down = new JRadioButton("向下");
		down.setSelected(true);
		group.add(up);
		group.add(down);
		// 给方向添加边框
		JPanel direction = new JPanel();
		direction.add(up);
		direction.add(down);
		direction.setBorder(BorderFactory.createTitledBorder("方向"));

		// 为"查找内容"添加文档内容变化监听
		findText.getDocument().addDocumentListener(new DocumentListener() {
			@Override
			public void removeUpdate(DocumentEvent e) {
				updateSearchComponent(findText.getText());
			}

			@Override
			public void insertUpdate(DocumentEvent e) {
				updateSearchComponent(findText.getText());
			}

			@Override
			public void changedUpdate(DocumentEvent e) {
				updateSearchComponent(findText.getText());
			}
		});

		// "查找下一个"按钮事件处理
		searchNext.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// 点击按钮后保存查找内容的文本
				searchText = findText.getText();
				// 开始查找
				startSearch(false);// false表示不是替换中的查找
			}
		});

		// "取消"按钮事件处理
		cancel.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				findDialog.dispose();
			}
		});

		// 设置组件位置
		JPanel leftPanel = new JPanel(new BorderLayout());// 左边
		JPanel leftUpPanel = new JPanel();// 左上角
		JPanel leftDownPanel = new JPanel();// 左下角
		JPanel rightPanel = new JPanel(new BorderLayout());// 右边

		// 查找内容放在左上角
		leftUpPanel.add(searchContentLabel);
		leftUpPanel.add(findText);

		// 区分大小写和方向放在左下角
		leftDownPanel.add(matchCase);
		leftDownPanel.add(direction);
		leftPanel.add(leftUpPanel, BorderLayout.NORTH);
		leftPanel.add(leftDownPanel, BorderLayout.SOUTH);

		// 2个button放在2个面板中再放到右边，保持间隔
		JPanel button1 = new JPanel();
		JPanel button2 = new JPanel();
		button1.add(searchNext);
		button2.add(cancel);
		rightPanel.add(button1, BorderLayout.NORTH);
		rightPanel.add(button2, BorderLayout.SOUTH);

		container.add(leftPanel);
		container.add(rightPanel);

		// 设置对话框的大小、可更改大小(否)、位置和可见性
		findDialog.setSize(420, 160);
		findDialog.setResizable(false);
		findDialog.setLocationRelativeTo(UI);
		findDialog.setVisible(true);
	}// end search

	/**
	 * 根据是否输入查找内容，改变查找相关按钮的可点击属性
	 * 
	 * @param text
	 *            输入的查找内容
	 */
	private void updateSearchComponent(String text) {
		if (!text.equals("")) {
			if (searchNext != null)
				searchNext.setEnabled(true);
			if (replace != null)
				replace.setEnabled(true);
			if (replaceAll != null)
				replaceAll.setEnabled(true);
		} else {
			if (searchNext != null)
				searchNext.setEnabled(false);
			if (replace != null)
				replace.setEnabled(false);
			if (replaceAll != null)
				replaceAll.setEnabled(false);
		}
	}// end updateSearchContent

	/**
	 * 开始查找
	 * 
	 * @param inReplace
	 *            标志是否为替换中的查找
	 */
	private void startSearch(boolean inReplace) {
		int substringIndex = 0;// 找到的子串的下标

		// 获取文本插入符号的位置
		int startPosition = UI.textArea.getCaretPosition();

		String allText = UI.textArea.getText();// 全部的文本内容
		String allTextLower = allText.toLowerCase();// 全部文本转换成小写
		String searchTextLower = searchText.toLowerCase();// 要查找的文本转换成小写
		String strA, strB;// strA为全部文本，strB为要查找的文本

		// 根据，"区分大小写"是否被选中，确定strA strB
		if (matchCase.isSelected()) {
			strA = allText;
			strB = searchText;
		} else {
			strA = allTextLower;
			strB = searchTextLower;
		}

		// 如果是替换中的查找，只支持向下查找
		if (inReplace == true) {
			// 第一次查找，没有选中任何文本，从最开始的位置找
			if (UI.textArea.getSelectedText() == null) {
				substringIndex = strA.indexOf(strB, startPosition);
			} else {
				// 第二次以后的查找每次都要先减去已经选中的文本长度，并前进一位
				substringIndex = strA.indexOf(strB, startPosition - strB.length() + 1);
			}
		}
		// 如果不是替换中的查找，则根据查找方向up或者down查找子串的下标
		else {
			if (up.isSelected()) {
				if (UI.textArea.getSelectedText() == null) {
					substringIndex = strA.lastIndexOf(strB, startPosition - 1);
				} else {
					substringIndex = strA.lastIndexOf(strB, startPosition - strB.length() - 1);
				}
			} else if (down.isSelected()) {
				if (UI.textArea.getSelectedText() == null) {
					substringIndex = strA.indexOf(strB, startPosition);
				} else {
					substringIndex = strA.indexOf(strB, startPosition - strB.length() + 1);
				}
			}
		}

		if (substringIndex > -1) {// 若找到，选中选到的子串
			UI.textArea.setCaretPosition(substringIndex);
			UI.textArea.select(substringIndex, substringIndex + searchText.length());
		} else {// 若找不到，弹出提醒
			JOptionPane.showMessageDialog(null, "找不到" + "\"" + searchText + "\"", "记事本",
					JOptionPane.INFORMATION_MESSAGE);
		}
	}// end startSearch

	/**
	 * 查找下一个
	 */
	public void searchNext() {
		if (searchText.equals("")) {// 若要查找的内容还没输入，功能同查找
			search();
		} else
			startSearch(false);// 若已经输入，直接开始查找，false表示不是替换中的查找
	}// end searchNext

	/**
	 * 替换
	 */
	public void replace() {
		// 创建JDialog窗体
		JDialog replaceDialog = new JDialog(UI, "替换", false);
		Container container = replaceDialog.getContentPane();
		container.setLayout(new FlowLayout());

		// 输入查找内容
		JLabel searchContentLabel = new JLabel("查找内容:");
		searchContentLabel.setPreferredSize(new Dimension(80, 30));
		JTextField findText = new JTextField(searchText, 22);
		findText.selectAll();

		// 输入替换内容
		JLabel replaceLabel = new JLabel("替换为:");
		replaceLabel.setPreferredSize(new Dimension(80, 30));
		JTextField replaceContent = new JTextField(replaceText, 22);

		// "查找下一个"按钮
		searchNext = new JButton("查找下一个");
		searchNext.setPreferredSize(new Dimension(110, 30));
		if (searchText.equals(""))
			searchNext.setEnabled(false);
		else
			searchNext.setEnabled(true);

		// "替换"按钮
		replace = new JButton("替换");
		replace.setPreferredSize(new Dimension(110, 30));
		if (searchText.equals(""))
			replace.setEnabled(false);
		else
			replace.setEnabled(true);

		// "全部替换"按钮
		replaceAll = new JButton("全部替换");
		replaceAll.setPreferredSize(new Dimension(110, 30));
		if (searchText.equals(""))
			replaceAll.setEnabled(false);
		else
			replaceAll.setEnabled(true);

		// "取消"按钮
		JButton cancel = new JButton("取消");
		cancel.setPreferredSize(new Dimension(110, 30));

		// 复选框，是否区分大小写
		matchCase = new JCheckBox("区分大小写");

		// 为"查找内容"添加文档内容变化监听
		findText.getDocument().addDocumentListener(new DocumentListener() {
			@Override
			public void removeUpdate(DocumentEvent e) {
				updateSearchComponent(findText.getText());
			}

			@Override
			public void insertUpdate(DocumentEvent e) {
				updateSearchComponent(findText.getText());
			}

			@Override
			public void changedUpdate(DocumentEvent e) {
				updateSearchComponent(findText.getText());
			}
		});

		// "查找下一个"按钮事件处理
		searchNext.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// 点击按钮后保存查找内容和替换内容的文本
				searchText = findText.getText();
				replaceText = replaceContent.getText();
				// 开始查找
				startSearch(true);// true表示这是替换中的查找，只支持向下查找
			}
		});

		// "替换"按钮事件处理
		replace.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// 点击按钮后保存查找内容和替换内容的文本
				searchText = findText.getText();
				replaceText = replaceContent.getText();

				// 区分大小写
				if (matchCase.isSelected()) {
					// 如果当前没有选择文本，或者选择的文本不是要查找的内容，则查找下一个
					if (UI.textArea.getSelectedText() == null || !UI.textArea.getSelectedText().equals(searchText))
						startSearch(true);
					// 如果当前选择的是要替换的内容，直接替换
					else if (UI.textArea.getSelectedText().equals(searchText))
						UI.textArea.replaceSelection(replaceText);
				}
				// 不区分大小写
				else {
					if (UI.textArea.getSelectedText() == null)
						startSearch(true);
					else {
						String text = UI.textArea.getSelectedText().toLowerCase();
						String search = searchText.toLowerCase();
						if (!text.equals(search))
							startSearch(true);
						else
							UI.textArea.replaceSelection(replaceText);
					}
				}
			}
		});

		// "全部替换"按钮事件处理
		replaceAll.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// 点击按钮后保存查找内容和替换内容的文本
				searchText = findText.getText();
				replaceText = replaceContent.getText();
				// 全部替换
				String text = UI.textArea.getText();
				// 区分大小
				if (matchCase.isSelected()) {
					text = text.replaceAll(searchText, replaceText);
					UI.textArea.setText(text);
				}
				// 不区分大小写
				else {
					text = text.replaceAll(searchText.toLowerCase(), replaceText);// 替换所有大写
					text = text.replaceAll(searchText.toUpperCase(), replaceText);// 替换所有小写
					UI.textArea.setText(text);
				}
			}
		});

		// "取消"按钮事件处理
		cancel.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				replaceDialog.dispose();
			}
		});

		// 设置组件位置
		JPanel leftPanel = new JPanel(new BorderLayout());// 左边
		JPanel leftUpPanel = new JPanel();// 左上
		JPanel leftCenterPanel = new JPanel();// 左中
		JPanel leftDownPanel = new JPanel();// 左下
		JPanel rightPanel = new JPanel(new BorderLayout());// 右边
		JPanel rightUpPanel = new JPanel(new BorderLayout());// 右上
		JPanel rightDownPanel = new JPanel(new BorderLayout());// 右下

		// 查找内容在左上
		leftUpPanel.add(searchContentLabel);
		leftUpPanel.add(findText);
		// 替换内容在左中
		leftCenterPanel.add(replaceLabel);
		leftCenterPanel.add(replaceContent);
		// 区分大小写在左下
		leftDownPanel.add(matchCase);
		leftPanel.add(leftUpPanel, BorderLayout.NORTH);
		leftPanel.add(leftCenterPanel, BorderLayout.CENTER);
		leftPanel.add(leftDownPanel, BorderLayout.SOUTH);

		// 2个button放在2个面板中再放到右边，保持间隔
		JPanel button1 = new JPanel();
		JPanel button2 = new JPanel();
		JPanel button3 = new JPanel();
		JPanel button4 = new JPanel();
		button1.add(searchNext);
		button2.add(replace);
		button3.add(replaceAll);
		button4.add(cancel);
		rightUpPanel.add(button1, BorderLayout.NORTH);
		rightUpPanel.add(button2, BorderLayout.SOUTH);
		rightDownPanel.add(button3, BorderLayout.NORTH);
		rightDownPanel.add(button4, BorderLayout.SOUTH);
		rightPanel.add(rightUpPanel, BorderLayout.NORTH);
		rightPanel.add(rightDownPanel, BorderLayout.SOUTH);
		container.add(leftPanel);
		container.add(rightPanel);

		// 设置对话框的大小、可更改大小(否)、位置和可见性
		replaceDialog.setSize(460, 220);
		replaceDialog.setResizable(false);
		replaceDialog.setLocationRelativeTo(UI);
		replaceDialog.setVisible(true);
	}// end replace

	/**
	 * 转到
	 */
	public void jumpTo() {
		// 创建JDialog窗体
		JDialog jumpDialog = new JDialog(UI, "转到指定行", true);
		Container container = jumpDialog.getContentPane();
		container.setLayout(new FlowLayout());

		// 获取当前行号
		int caretPosition = UI.textArea.getCaretPosition();// 获得光标位置
		int row = 1;
		try {
			row = UI.textArea.getLineOfOffset(caretPosition) + 1;// 获得光标所在行号
		} catch (BadLocationException e1) {
			e1.printStackTrace();
		} // 获得光标所在的行号

		// 输入行号
		JLabel lineLabel = new JLabel("行号:", JLabel.LEFT);
		JTextField lineText = new JTextField(row + "", 25);
		lineText.selectAll();

		// "转到"按钮
		JButton jump = new JButton("转到");
		jump.setPreferredSize(new Dimension(80, 30));

		// "取消"按钮
		JButton cancel = new JButton("取消");
		cancel.setPreferredSize(new Dimension(80, 30));

		// "转到"按钮事件处理
		jump.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int input = 0;
				boolean inputSuccess = false;
				// 输入行数
				try {
					input = Integer.parseInt(lineText.getText()) - 1;
					inputSuccess = true;
				} catch (Exception exception) {
					JOptionPane.showMessageDialog(jumpDialog, "只能输入数字", "记事本 - 跳行", JOptionPane.ERROR_MESSAGE);
				}

				// 获得该行的首字符位置
				if (inputSuccess) {
					int index = 0;
					try {
						index = UI.textArea.getLineStartOffset(input);
						UI.textArea.setCaretPosition(index);
						jumpDialog.dispose();// 跳转成功关闭对话框
					} catch (BadLocationException e1) {
						// 行数超过总行数，提示并跳转到最后一行，不关闭对话框
						JOptionPane.showMessageDialog(jumpDialog, "行数超过了总行数", "记事本 - 跳行", JOptionPane.ERROR_MESSAGE);
						// 跳转到最后一行
						try {
							index = UI.textArea.getLineStartOffset(UI.textArea.getLineCount() - 1);
							UI.textArea.setCaretPosition(index);
							lineText.setText((index + 1) + "");
						} catch (BadLocationException e2) {
							e2.printStackTrace();
						}
					}
				}
			}
		});

		// "取消"按钮事件处理
		cancel.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				jumpDialog.dispose();
			}
		});

		// 设置组件位置
		JPanel upPanel = new JPanel();// 上
		JPanel centerPanel = new JPanel();// 中
		JPanel downPanel = new JPanel(new FlowLayout());// 下

		upPanel.add(lineLabel);
		centerPanel.add(lineText);
		downPanel.add(jump);
		downPanel.add(cancel);

		container.add(upPanel);
		container.add(centerPanel);
		container.add(downPanel);

		// 设置对话框的大小、可更改大小(否)、位置和可见性
		jumpDialog.setSize(240, 180);
		jumpDialog.setResizable(false);
		jumpDialog.setLocationRelativeTo(UI);
		jumpDialog.setVisible(true);
	}// end jumpTo

	/**
	 * 全选
	 */
	public void selectAll() {
		// 全选
		UI.textArea.selectAll();
		// 设置复制、剪切、删除菜单是否失效
		UI.setEnabledAll();
	}// end selectAll

	/**
	 * 时间、日期
	 */
	public void time() {
		SimpleDateFormat timeFormat = new SimpleDateFormat("H:mm yyyy/M/d");// 设置日期格式
		String time = timeFormat.format(new Date());// new Date()为获取当前系统时间
		UI.textArea.insert(time, UI.textArea.getCaretPosition());
		UI.textArea.replaceSelection(time);
	}// end time

	/**
	 * 自动换行
	 */
	public void autoTransfer() {
		if (UI.autoTransfer.isSelected()) {
			// 选择自动换行则不能转到某一行，状态栏也不能显示
			UI.jumpToItem.setEnabled(false);
			UI.statusBar.setEnabled(false);
			UI.bottom.setVisible(false);
			UI.textArea.setLineWrap(true);
		} else {
			UI.textArea.setLineWrap(false);
			UI.statusBar.setEnabled(true);
			// 取消自动换行，如果状态栏被打开，则显示
			if (UI.statusBar.isSelected())
				UI.bottom.setVisible(true);
			UI.jumpToItem.setEnabled(true);
		}
	}// end autoTransfer

	/**
	 * 选择字体
	 */
	public void selectFont() {
		FontDialog fontDialog = new FontDialog(UI, "字体", true, UI.editFont);
		int result = fontDialog.showFontDialog();
		if (result == FontDialog.APPROVE_OPTION) {
			UI.editFont = fontDialog.getSelectFont();
			UI.textArea.setFont(UI.editFont);
		}
	}// end selectFont

	/**
	 * 状态栏
	 */
	public void statusBar() {
		if (UI.statusBar.isSelected())
			UI.bottom.setVisible(true);
		else
			UI.bottom.setVisible(false);
	}// end statusBar

	/**
	 * 查看帮助
	 */
	public void lookHelp() {
		JOptionPane.showMessageDialog(UI, "本应用为JAVA编写的记事本\n使用方法同Windows记事本", "查看帮助", JOptionPane.PLAIN_MESSAGE);
	}// end lookHelp

	/**
	 * 关于记事本
	 */
	public void about() {
		JOptionPane.showMessageDialog(UI, "记事本\n" + "Copyright© 2018\n" + "author：王振伟\n" + "  email：664129552@qq.com",
				"关于记事本", JOptionPane.PLAIN_MESSAGE);
	}// end about

	/**
	 * 询问是否保存当前文件
	 * 
	 * @return 返回整形代表用户的选择，参照JOptionPane的常量字段YES_OPTION和NO_OPTION
	 */
	private int askSave() {
		int choose = JOptionPane.showConfirmDialog(UI, "是否将更改保存到 " + UI.fileName + "?", "记事本",
				JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
		return choose;
	}// end askSave

	/**
	 * 更新标题
	 */
	private void updateTitle() {
		UI.setTitle(UI.fileName + " - 记事本");
	}// end updateTitle
}
