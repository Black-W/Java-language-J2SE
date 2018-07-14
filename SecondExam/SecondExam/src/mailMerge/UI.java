package mailMerge;

import java.awt.EventQueue;
import java.awt.Font;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JTextArea;
import javax.swing.JLabel;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JTextField;
import javax.swing.UIManager;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * 
 * 邮件合并软件界面类 ，设置了软件的界面，并为其添加了监听器
 * 
 * @author 王振伟
 * @email 664129552@qq.com
 * @date 2018-05-30
 *
 */
public class UI extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	// 界面控制器
	private static Controller controller;

	// 邮件内容面板
	protected JTextArea textArea;

	// 姓名输入框
	protected JTextField nameText;

	// 年龄输入框
	protected JTextField ageText;

	// 性别输入框
	protected JTextField genderText;

	/**
	 * 应用启动
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					// 使用Nimbus风格的界面
					UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");

					// 创建界面
					UI frame = new UI();
					// 创建界面控制器
					controller = new Controller(frame);
					frame.setVisible(true);
					frame.setLocationRelativeTo(null);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * 初始化界面
	 */
	public UI() {
		setTitle("Mail Merge App");
		setBounds(100, 100, 721, 444);

		// 设置退出方式
		this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		this.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				controller.exit();// 重写默认关闭按钮
			}
		});

		// 菜单栏
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		// 保存菜单选项
		JMenu mnSave = new JMenu("Save");
		mnSave.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				// 将信件内容保存到文件，并保存新的接收人信息
				controller.saveFile();
			}
		});
		menuBar.add(mnSave);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);

		// 内容面板
		textArea = new JTextArea();
		// 自动换行
		textArea.setLineWrap(true);
		textArea.setFont(new Font("consola", Font.PLAIN, 18));
		textArea.setEditable(false);

		JLabel lblMailContent = new JLabel("Mail Content");

		// 模板1按钮
		JButton btnTemplateOne = new JButton("Template 1");
		btnTemplateOne.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				// 读取模板1
				controller.readTemplate(1);
			}
		});

		// 模板2按钮
		JButton btnTemplateTow = new JButton("Template 2");
		btnTemplateTow.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				// 读取模板2
				controller.readTemplate(2);
			}
		});

		// 模板3按钮
		JButton btnTemplateThree = new JButton("Template 3");
		btnTemplateThree.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// 读取模板3
				controller.readTemplate(3);
			}
		});

		JLabel lblChooseTemplate = new JLabel("Choose Template");

		// 姓名输入框
		JLabel lblName = new JLabel("Name:");
		nameText = new JTextField();
		nameText.setToolTipText("Enter name here");
		nameText.setColumns(10);

		// 年龄输入框
		JLabel lblNewLabel = new JLabel("Age:");
		ageText = new JTextField();
		ageText.setColumns(10);

		// 性别输入框
		JLabel lblNewLabel_1 = new JLabel("Gender");
		genderText = new JTextField();
		genderText.setColumns(10);

		// 修改占位符按钮
		JButton btnModify = new JButton("Modify");
		btnModify.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// 替换占位符
				controller.replacepPlaceholders();
			}
		});

		// 界面布局，使用了grouplayout
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup().addGap(21)
						.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addComponent(textArea, GroupLayout.PREFERRED_SIZE, 526, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblMailContent))
						.addGap(18)
						.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
								.addComponent(lblChooseTemplate, Alignment.LEADING)
								.addComponent(btnTemplateTow, Alignment.LEADING)
								.addComponent(btnTemplateOne, Alignment.LEADING)
								.addComponent(btnTemplateThree, Alignment.LEADING)
								.addComponent(lblName, Alignment.LEADING)
								.addComponent(nameText, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 120,
										Short.MAX_VALUE)
								.addComponent(lblNewLabel, Alignment.LEADING)
								.addComponent(ageText, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 120,
										Short.MAX_VALUE)
								.addComponent(lblNewLabel_1, Alignment.LEADING).addComponent(genderText,
										Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 120, Short.MAX_VALUE)
								.addComponent(btnModify))
						.addContainerGap()));
		gl_contentPane.setVerticalGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup().addContainerGap()
						.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE).addComponent(lblMailContent)
								.addComponent(lblChooseTemplate))
						.addPreferredGap(ComponentPlacement.RELATED)
						.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(textArea, GroupLayout.PREFERRED_SIZE, 300, GroupLayout.PREFERRED_SIZE)
								.addGroup(gl_contentPane.createSequentialGroup().addComponent(btnTemplateOne).addGap(11)
										.addComponent(btnTemplateTow).addGap(9).addComponent(btnTemplateThree).addGap(8)
										.addComponent(lblName).addPreferredGap(ComponentPlacement.RELATED)
										.addComponent(nameText, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
												GroupLayout.PREFERRED_SIZE)
										.addGap(8).addComponent(lblNewLabel).addGap(5)
										.addComponent(ageText, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
												GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(ComponentPlacement.RELATED).addComponent(lblNewLabel_1)
										.addGap(3)
										.addComponent(genderText, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
												GroupLayout.PREFERRED_SIZE)
										.addGap(8).addComponent(btnModify)))
						.addContainerGap(117, Short.MAX_VALUE)));
		contentPane.setLayout(gl_contentPane);
	}
}
