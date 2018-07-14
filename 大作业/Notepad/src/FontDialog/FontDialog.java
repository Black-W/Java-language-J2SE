package FontDialog;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Frame;
import java.awt.GraphicsEnvironment;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

/**
 * 
 * 字体选择对话框，继承自JDialog，用于选择所需的字体
 * 
 * @author 王振伟
 * @email 664129552@qq.com
 * @date 2018-05-15
 *
 */
public class FontDialog extends JDialog {
	private static final long serialVersionUID = 1L;
	// 选择取消的返回值
	public static final int CANCEL_OPTION = 0;
	// 选择确定的返回值
	public static final int APPROVE_OPTION = 1;
	// 中文预览的字符串
	private static final String CHINA_STRING = "我的记事本";
	// 英文预览的字符串
	private static final String ENGLISH_STRING = "Java Notepad";
	// 示例脚本
	private static final String[] INSTANCE_STRING = { "中文 GB2312", "西欧语言" };
	// 返回值，默认取消
	private int returnValue = CANCEL_OPTION;

	// 当前字体
	private Font font;
	// 字体选择器主容器
	private Box box;

	// 字体输入文本框
	private JTextField fontText;
	// 字形输入文本框
	private JTextField styleText;
	// 大小输入文本框
	private JTextField sizeText;
	// 字体列表
	private JList<String> fontList;
	// 字形列表
	private JList<String> styleList;
	// 大小列表
	private JList<String> sizeList;

	// 示例预览标签
	private JLabel instanceLabel;
	// 示例预览边框
	private Border instanceBorder = BorderFactory.createTitledBorder("示例");
	// 示例脚本下拉框
	private JComboBox<String> instanceComboBox;

	// 确定按钮
	private JButton approveButton;
	// 取消按钮
	private JButton cancelButton;

	// 所有字体
	private String[] fontArray;
	// 所有字形
	private String[] styleArray = { "常规", "粗体", "倾斜" };
	// 所有大小
	private String[] sizeArray = { "8", "9", "10", "11", "12", "14", "16", "18", "20", "22", "24", "26", "28", "36",
			"48", "72", "初号", "小初", "一号", "小一", "二号", "小二", "三号", "小三", "四号", "小四", "五号", "小五", "六号", "小六", "七号",
			"八号" };
	// 上面数组中对应的字体大小
	private int[] sizeIntArray = { 8, 9, 10, 11, 12, 14, 16, 18, 20, 22, 24, 26, 28, 36, 48, 72, 42, 36, 26, 24, 22, 18,
			16, 15, 14, 12, 10, 9, 8, 7, 6, 5 };

	/**
	 * 构造函数
	 * 
	 * @param owner
	 *            父窗口
	 * @param title
	 *            标题
	 * @param modal
	 *            模态
	 * @param editFont
	 *            当前编辑区的字体
	 */
	public FontDialog(Frame owner, String title, boolean modal, Font editFont) {
		/* 调用父类构造函数 */
		super(owner, title, modal);

		/* 设置初始默认字体 */
		font = editFont;

		/* 设置界面、监听器 */
		setUI(); // 设置JDialog界面
		addListener(); // 添加监听器
		setInstance(); // 设置示例预览

		// 设置界面大小
		pack(); // 自适应大小
	}// end FontChooser

	/**
	 * 设置JDialog界面
	 */
	private void setUI() {
		// 获得系统字体
		fontArray = GraphicsEnvironment.getLocalGraphicsEnvironment().getAvailableFontFamilyNames();

		// 主容器
		box = Box.createVerticalBox();// 创建垂直的BOX
		box.setBorder(BorderFactory.createEmptyBorder(8, 8, 8, 8));// 设置边界

		// 字体、字形、大小列表
		fontList = new JList<String>(fontArray);
		styleList = new JList<String>(styleArray);
		sizeList = new JList<String>(sizeArray);
		fontList.setSelectedValue(String.valueOf(font.getName()), true);
		styleList.setSelectedIndex(font.getStyle());
		sizeList.setSelectedValue(String.valueOf(font.getSize()), true);

		// 字体输入框
		fontText = new JTextField(String.valueOf(fontList.getSelectedValue()));

		// 字形输入框
		styleText = new JTextField(String.valueOf(styleList.getSelectedValue()));

		// 大小输入框
		sizeText = new JTextField(String.valueOf(sizeList.getSelectedValue()));

		// 字体预览标签
		instanceLabel = new JLabel(CHINA_STRING);
		instanceLabel.setFont(font);
		instanceLabel.setHorizontalAlignment(JLabel.CENTER);// 设置水平对齐方式

		// 字体预览下拉框
		instanceComboBox = new JComboBox<String>(INSTANCE_STRING);

		// 确定、取消按钮
		approveButton = new JButton("确定");
		cancelButton = new JButton("取消");

		// 放置字体、字形、大小的标签
		Box labelBox = Box.createHorizontalBox();
		JLabel fontLabel = new JLabel("字体:");
		JLabel styleLabel = new JLabel("字形:");
		JLabel sizeLabel = new JLabel("大小:");
		fontLabel.setPreferredSize(new Dimension(280, 20));
		fontLabel.setMaximumSize(new Dimension(280, 20));
		fontLabel.setMinimumSize(new Dimension(280, 20));
		styleLabel.setPreferredSize(new Dimension(100, 20));
		styleLabel.setMaximumSize(new Dimension(100, 20));
		styleLabel.setMinimumSize(new Dimension(100, 20));
		sizeLabel.setPreferredSize(new Dimension(80, 20));
		sizeLabel.setMaximumSize(new Dimension(80, 20));
		sizeLabel.setMinimumSize(new Dimension(80, 20));
		labelBox.add(fontLabel);
		labelBox.add(Box.createHorizontalStrut(15));
		labelBox.add(styleLabel);
		labelBox.add(Box.createHorizontalStrut(15));
		labelBox.add(sizeLabel);

		// 放置字体、字形、大小的输入框
		Box textFieldBox = Box.createHorizontalBox();
		fontText.setPreferredSize(new Dimension(280, 20));
		fontText.setMaximumSize(new Dimension(280, 20));
		fontText.setMinimumSize(new Dimension(280, 20));
		textFieldBox.add(fontText);
		textFieldBox.add(Box.createHorizontalStrut(15));
		styleText.setPreferredSize(new Dimension(100, 20));
		styleText.setMaximumSize(new Dimension(100, 20));
		styleText.setMinimumSize(new Dimension(100, 20));
		textFieldBox.add(styleText);
		textFieldBox.add(Box.createHorizontalStrut(15));
		sizeText.setPreferredSize(new Dimension(80, 20));
		sizeText.setMaximumSize(new Dimension(80, 20));
		sizeText.setMinimumSize(new Dimension(80, 20));
		textFieldBox.add(sizeText);

		// 放置字体、字形、大小的列表
		Box listBox = Box.createHorizontalBox();
		JScrollPane fontScrp = new JScrollPane(fontList);
		// 设置自动跳转到选中的行
		int i = fontList.getSelectedIndex();
		Rectangle rect = fontList.getCellBounds(i, i);
		fontScrp.getViewport().scrollRectToVisible(rect);
		// 设置大小
		fontScrp.setPreferredSize(new Dimension(280, 150));
		fontScrp.setMaximumSize(new Dimension(280, 150));
		fontScrp.setMaximumSize(new Dimension(280, 150));
		listBox.add(fontScrp);
		listBox.add(Box.createHorizontalStrut(15));

		JScrollPane styleScrp = new JScrollPane(styleList);
		// 设置自动跳转到选中的行
		i = styleList.getSelectedIndex();
		rect = styleList.getCellBounds(i, i);
		styleScrp.getViewport().scrollRectToVisible(rect);
		// 设置大小
		styleScrp.setPreferredSize(new Dimension(100, 150));
		styleScrp.setMaximumSize(new Dimension(100, 150));
		styleScrp.setMinimumSize(new Dimension(100, 150));
		listBox.add(styleScrp);
		listBox.add(Box.createHorizontalStrut(15));

		JScrollPane sizeScrp = new JScrollPane(sizeList);
		// 设置自动跳转到选中的行
		i = sizeList.getSelectedIndex();
		rect = sizeList.getCellBounds(i, i);
		sizeScrp.getViewport().scrollRectToVisible(rect);
		// 设置大小
		sizeScrp.setPreferredSize(new Dimension(80, 150));
		sizeScrp.setMaximumSize(new Dimension(80, 150));
		sizeScrp.setMinimumSize(new Dimension(80, 150));
		listBox.add(sizeScrp);

		// 放置字体预览框
		Box instanceBox = Box.createHorizontalBox();
		JPanel instancePanel = new JPanel(new BorderLayout());// 为示例面板添加标题边框
		instancePanel.setBorder(instanceBorder);
		instancePanel.add(instanceLabel);
		instancePanel.setPreferredSize(new Dimension(195, 95));
		instancePanel.setMaximumSize(new Dimension(195, 95));
		instancePanel.setMinimumSize(new Dimension(195, 95));
		instanceBox.add(Box.createHorizontalStrut(295));
		instanceBox.add(instancePanel);

		// 放置脚本标签
		Box comLabelBox = Box.createHorizontalBox();
		JLabel comboboxLabel = new JLabel("脚本:");
		comboboxLabel.setPreferredSize(new Dimension(195, 20));
		comboboxLabel.setMaximumSize(new Dimension(195, 20));
		comboboxLabel.setMinimumSize(new Dimension(195, 20));
		comLabelBox.add(Box.createHorizontalStrut(295));
		comLabelBox.add(comboboxLabel);

		// 放置脚本下拉框
		Box comboboxBox = Box.createHorizontalBox();
		instanceComboBox.setPreferredSize(new Dimension(195, 20));
		instanceComboBox.setMaximumSize(new Dimension(195, 20));
		instanceComboBox.setMinimumSize(new Dimension(195, 20));
		comboboxBox.add(Box.createHorizontalStrut(295));
		comboboxBox.add(instanceComboBox);

		// 放置确定和取消按钮
		Box buttonBox = Box.createHorizontalBox();
		buttonBox.add(Box.createHorizontalGlue());
		buttonBox.add(approveButton);
		buttonBox.add(Box.createHorizontalStrut(10));
		buttonBox.add(cancelButton);
		box.add(labelBox);
		box.add(textFieldBox);
		box.add(listBox);
		box.add(Box.createVerticalStrut(20));
		box.add(instanceBox);
		box.add(Box.createVerticalStrut(10));
		box.add(comLabelBox);
		box.add(comboboxBox);
		box.add(Box.createVerticalStrut(50));
		box.add(buttonBox);

		// 将主容器box,添加到JDialog的内容面板中
		getContentPane().add(box);
	}// end setUI

	/**
	 * 添加所需的事件监听器
	 */
	private void addListener() {
		// 字体列表选择监听器
		fontList.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent e) {
				// 根据选择的内容修改输入框文本
				fontText.setText(String.valueOf(fontList.getSelectedValue()));
				// 设置示例字体
				setInstance();
			}
		});

		// 字形列表选择监听器
		styleList.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent e) {
				styleText.setText(String.valueOf(styleList.getSelectedValue()));
				setInstance();
			}
		});

		// 大小列表选择监听器
		sizeList.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent e) {
				sizeText.setText(String.valueOf(sizeList.getSelectedValue()));
				setInstance();
			}
		});

		// 脚本下拉框监听器
		instanceComboBox.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent event) {
				if (event.getStateChange() == ItemEvent.SELECTED) {
					int index = instanceComboBox.getSelectedIndex();
					String text = index == 0 ? CHINA_STRING : ENGLISH_STRING;
					instanceLabel.setText(text);
				}
			}
		});

		// 确定按钮的事件监听
		approveButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// 组合字体
				font = findFont();
				if (font != null) {
					// 设置返回值
					returnValue = APPROVE_OPTION;
					// 关闭窗口
					disposeDialog();
				}
			}
		});

		// 取消按钮事件监听
		cancelButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				disposeDialog();
			}
		});
	}// end addListener

	/**
	 * 设置预览
	 */
	private void setInstance() {
		Font f = findFont();
		instanceLabel.setFont(f);
	}// end setInstance

	/**
	 * 按照输入查找字体,成功则返回该字体，失败则返回null
	 * 
	 * @return 当前组合的字体
	 */
	private Font findFont() {
		String fontName = fontText.getText();
		String styleStr = styleText.getText().trim();
		String sizeStr = sizeText.getText().trim();
		// 判断输入的字体是否在列表内
		boolean exist = false;
		if (fontName.length() > 0) {
			for (int i = 0; i < fontArray.length; i++) {
				if (fontName.equals(fontArray[i])) {
					fontList.setSelectedIndex(i);
					exist = true;
					break;
				}
			}
		}
		if (!exist) {
			showErrorDialog("没有该名称的字体。\n请从字体列表中选择字体。");
			return null;
		}
		// 判断输入的字形是否在列表内
		int styleInt = 0;// 字形对应的INT值
		exist = false;
		if (styleStr.length() > 0) {
			for (int i = 0; i < styleArray.length; i++) {
				if (styleStr.equals(styleArray[i])) {
					styleInt = i;
					styleList.setSelectedIndex(i);
					exist = true;
					break;
				}
			}
		}
		if (!exist) {
			showErrorDialog("没有该字形的字体。\n请从字形列表中选择字形。");
			return null;
		}

		int sizeInt = 8;// 大小所对应的INT值，默认是8
		exist = false;
		// 先检查大小是否在列表中
		for (int i = 0; i < sizeArray.length; i++) {
			if (sizeStr.equals(sizeArray[i])) {
				sizeInt = sizeIntArray[i];
				styleList.setSelectedIndex(i);
				exist = true;
				break;
			}
		}
		// 如果不存在列表中，检查大小是否是有效数字
		if (!exist) {
			try {
				sizeInt = Integer.parseInt(sizeStr);
				if (sizeInt < 1) {
					sizeInt = 8;
				}
			} catch (NumberFormatException nfe) {
				showErrorDialog("大小必须是数字。");
				return null;
			}
		}

		return new Font(fontName, styleInt, sizeInt);
	}// end findFont

	/**
	 * 显示错误消息
	 * 
	 * @param errorMessage
	 *            要输出的错误信息
	 */
	private void showErrorDialog(String errorMessage) {
		JOptionPane.showMessageDialog(this, errorMessage, "字体", JOptionPane.INFORMATION_MESSAGE);
	}// end showErrorDialog

	/**
	 * 显示字体选择器
	 * 
	 * @param owner
	 * @return 该整形返回值表示用户点击了字体选择器的确定按钮或取消按钮，参考本类常量字段APPROVE_OPTION和CANCEL_OPTION
	 */
	public final int showFontDialog() {
		// 对话框相对位置
		setLocationRelativeTo(getOwner());
		// 对话框大小不可变
		setResizable(false);
		// 对话框可见
		setVisible(true);

		return returnValue;
	}// end showFontChooser

	/**
	 * 获得选择的字体
	 * 
	 * @return font
	 */
	public final Font getSelectFont() {
		return font;
	}// end getSelectFont

	/**
	 * 关闭窗口
	 */
	private void disposeDialog() {
		FontDialog.this.removeAll();
		FontDialog.this.dispose();
	}// end disposeDialog

}
