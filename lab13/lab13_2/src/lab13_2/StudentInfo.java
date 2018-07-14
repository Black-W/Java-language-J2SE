package lab13_2;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;
import javax.swing.JMenu;
import java.awt.FlowLayout;
import java.awt.Font;
import javax.swing.JTextArea;
import javax.swing.UIManager;
import java.awt.Dimension;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Formatter;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.FileNotFoundException;
import lab13_2.Student;
import java.awt.Rectangle;

public class StudentInfo extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextArea textArea;

	private static final int SHOW_ALL_RECORDS = 0;
	private static final int SHOW_ALL_SEARCH_RESULTS = 1;
	private static final ArrayList<Student> students = new ArrayList<Student>();// 暂时存储学生的存储信息
	private static final ArrayList<Integer> results = new ArrayList<Integer>();// 暂时存储学生的查询信息,在列表中的下标
	private int currentIndex = -1;// 正在展示的信息下标
	private final String title = String.format("%-5s\t%-5s\t%-11s\t%-14s\n", "ID", "Name", "Phone", "Email");
	private int showState = SHOW_ALL_RECORDS;// 表示当前显示的内容

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
					StudentInfo frame = new StudentInfo();
					frame.setLocationRelativeTo(null);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public StudentInfo() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 540, 246);

		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		textArea = new JTextArea();
		textArea.setEnabled(false);
		textArea.setEditable(false);
		textArea.setFont(new Font("Consolo", Font.BOLD, 17));
		textArea.setBounds(new Rectangle(5, 5, 5, 5));
		textArea.setPreferredSize(new Dimension(480, 120));
		contentPane.add(textArea);

		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		JMenu newMenu = new JMenu("新增");
		// 新增一条学生信息
		newMenu.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				// 123 Andy 11111111111 111111@qq.com
				String input = JOptionPane.showInputDialog(StudentInfo.this, "请输入学生信息：\n（学号、姓名、电话、邮箱，中间用空格隔开）:",
						"输入对话框", JOptionPane.PLAIN_MESSAGE);
				if (input == null)
					return;
				String[] stuInfo = input.split(" ");
				if (stuInfo.length == 4) {
					// 先修改下标
					currentIndex = students.size() - 1;

					Student newStudent = new Student(stuInfo[0], stuInfo[1], stuInfo[2], stuInfo[3]);
					students.add(newStudent);
					currentIndex++;

					// 显示
					showState = SHOW_ALL_RECORDS;
					setText(newStudent.toString());
				} else {
					JOptionPane.showMessageDialog(StudentInfo.this, "输入错误");
					return;
				}
			}
		});

		menuBar.add(newMenu);

		JMenu saveMenu = new JMenu("保存");
		// 将所有的学生信息输出到文件中
		saveMenu.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				saveFile();
			}
		});
		menuBar.add(saveMenu);

		JMenu researchMenu = new JMenu("查询");
		// 显示文件中存储的所有学生信息（借助“下一条”、“上一条”按钮查看下一条和上一条数据）
		researchMenu.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (students.size() == 0) {
					JOptionPane.showMessageDialog(StudentInfo.this, "还没有学生信息！");
					return;
				}

				String input = JOptionPane.showInputDialog(StudentInfo.this, "请输入要查询的学生姓名：", "输入对话框",
						JOptionPane.PLAIN_MESSAGE);
				if (input == null)
					return;

				// 遍历查找
				int index = 0;
				results.removeAll(results);// 清空之前的查找结果
				for (Student stu : students) {
					if (stu.getName().equals(input)) {
						results.add(index);
					}
					index++;
				}

				if (results.size() == 0) {
					JOptionPane.showMessageDialog(StudentInfo.this, "找不到该学生！");
				} else {
					// 输出查找结果
					showState = SHOW_ALL_SEARCH_RESULTS;
					currentIndex = 0;
					setText(students.get(results.get(currentIndex)).toString());
				}
			}
		});
		menuBar.add(researchMenu);

		JMenu showMenu = new JMenu("显示");
		// 显示文件中存储的所有学生信息（借助“下一条”、“上一条”按钮查看下一条和上一条数据）
		showMenu.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (students.size() == 0) {
					JOptionPane.showMessageDialog(StudentInfo.this, "还没有学生信息！");
					return;
				}

				showState = SHOW_ALL_RECORDS;
				currentIndex = 0;
				setText(students.get(currentIndex).toString());
			}
		});
		menuBar.add(showMenu);

		JMenu deleteMenu = new JMenu("删除");
		deleteMenu.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (students.size() == 0) {
					JOptionPane.showMessageDialog(StudentInfo.this, "还没有学生信息！");
					return;
				}

				int choice = JOptionPane.showConfirmDialog(StudentInfo.this, "确认删除当前显示的记录吗?");
				if (choice == JOptionPane.OK_OPTION) {
					if (showState == SHOW_ALL_RECORDS) {
						students.remove(currentIndex);
					} else {
						int index = results.get(currentIndex);
						students.remove(index);
						results.remove(currentIndex);
						// 将results里存的下标在删除记录后面的全部减一
						int i = 0;
						for (int n : results) {
							if (n > index)
								results.set(i, n - 1);
							i++;
						}
					}
				} else
					return;
				JOptionPane.showMessageDialog(StudentInfo.this, "删除记录成功！");

				// 删除之后判断是否学生信息为空
				if (showState == SHOW_ALL_RECORDS) {
					if (students.size() == 0)
						textArea.setText("All Records\n");
					else {
						if (currentIndex == students.size())// currenIndex就是原来的下一条记录的下标
						{
							currentIndex--;
							showRecord();
						} else {
							showRecord();
						}
					}
				} else {
					if (results.size() == 0)
						textArea.setText("Search Results\n");
					else {
						if (currentIndex == results.size())// currenIndex就是原来的下一条记录的下标
						{
							currentIndex--;
							showRecord();
						} else {
							showRecord();
						}
					}
				}
			}
		});
		menuBar.add(deleteMenu);

		JMenu updateMenu = new JMenu("修改");
		updateMenu.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int size = showState == SHOW_ALL_RECORDS ? students.size() : results.size();
				if (size == 0) {
					JOptionPane.showMessageDialog(StudentInfo.this, "还没有学生信息！");
					return;
				}

				// 开始修改
				int index = showState == SHOW_ALL_RECORDS ? currentIndex : results.get(currentIndex);// 获得要修改的记录下标
				String input = JOptionPane.showInputDialog(StudentInfo.this, "请输入修改后的学生信息：\n（学号、姓名、电话、邮箱，中间用空格隔开）:",
						"输入对话框", JOptionPane.PLAIN_MESSAGE);
				if (input == null)
					return;
				String[] stuInfo = input.split(" ");
				if (stuInfo.length == 4) {
					Student newStudent = new Student(stuInfo[0], stuInfo[1], stuInfo[2], stuInfo[3]);
					//替换
					students.set(index, newStudent);

					// 显示
					showRecord();
				} else {
					JOptionPane.showMessageDialog(StudentInfo.this, "输入错误");
					return;
				}
			}
		});
		menuBar.add(updateMenu);

		JButton preButton = new JButton("上一条");
		preButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (currentIndex == 0) {
					JOptionPane.showMessageDialog(StudentInfo.this, "已经是第一条信息！");
				} else {
					currentIndex--;
					showRecord();
				}
			}
		});
		contentPane.add(preButton);

		JButton nextButton_1 = new JButton("下一条");
		nextButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int size = showState == SHOW_ALL_RECORDS ? students.size() : results.size();
				if (size == 0) {
					JOptionPane.showMessageDialog(StudentInfo.this, "还没有学生信息！");
					return;
				}

				if (currentIndex == size - 1) {
					JOptionPane.showMessageDialog(StudentInfo.this, "已经是最后一条信息！");
				} else {
					currentIndex++;
					showRecord();
				}
			}
		});
		contentPane.add(nextButton_1);
	}

	// 显示当前下标的记录
	protected void showRecord() {
		if (showState == SHOW_ALL_RECORDS)
			setText(students.get(currentIndex).toString());
		else {
			setText(students.get(results.get(currentIndex)).toString());
		}
	}

	// 保存学生信息到文件
	protected void saveFile() {
		try {
			Formatter output = new Formatter("studentsInfo.txt");
			StringBuilder outStr = new StringBuilder();
			outStr.append(title);
			for (Student stu : students) {
				outStr.append(stu.toString() + "\n");
			}

			System.out.println(outStr);
			output.format("%s", outStr);

			output.close();

			JOptionPane.showMessageDialog(StudentInfo.this, "保存成功！", "成功", JOptionPane.PLAIN_MESSAGE);
		} catch (FileNotFoundException e1) {
			JOptionPane.showMessageDialog(StudentInfo.this, "保存失败！", "错误", JOptionPane.ERROR_MESSAGE);
		}
	}

	// 设置显示的信息
	private void setText(String stu) {
		if (showState == SHOW_ALL_RECORDS)
			textArea.setText("All Records\nRecord: " + (currentIndex + 1) + "\n" + title + stu);
		else {
			textArea.setText("Search Results\nRecord: " + (results.get(currentIndex) + 1) + "\n" + title + stu);
		}
	}

}
