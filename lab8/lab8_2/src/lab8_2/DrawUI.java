package lab8_2;

//用ArrayList存放数组，每个数组保存5个数字，图形类型+4个坐标，每次Dragged前先清空画板然后重绘
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JColorChooser;
import javax.swing.JComboBox;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * 画图工具界面类，程序的主类 定义了程序的UI 定义了组件的事件
 * 
 * @author Wang
 *
 */
public class DrawUI extends JFrame {
	private static final long serialVersionUID = 1L;
	private JCheckBox checkbox1;
	private JCheckBox checkbox2;
	private JCheckBox checkbox3;
	private JTextField textfield1;
	private JTextField textfield2;
	private JButton button1;
	private JButton button2;
	private JButton button3;
	private JButton button4;
	private JLabel label1;
	private JLabel label2;
	private JLabel label3;
	private JLabel label4;
	private JComboBox<String> comboBox;
	private String names[] = { "Line", "Rectangle", "Oval" };
	private JPanel panel1;
	private JPanel panel2;
	private JPanel panel3;
	private JPanel panel4;
	private static Font font = new Font("consola", Font.PLAIN, 18);

	/**
	 * main函数
	 */
	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
		} catch (Exception e) {
			e.printStackTrace();
		}
		DrawUI frame = new DrawUI();
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(1200, 800);
		frame.setLocation(400, 150);
	}

	public DrawUI() {
		setTitle("DrawTool");
		setLayout(new BorderLayout());

		// 创建画图区
		DrawController drawArea = new DrawController();
		drawArea.setBackground(Color.white);

		// 画板添加鼠标按压、释放监听器
		drawArea.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				drawArea.setBeginPoint(e.getX(), e.getY());
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				drawArea.setFinalPoint(e.getX(), e.getY());
				drawArea.saveShape();
			}
		});

		// 画板添加鼠标拖拽、移动监听器
		drawArea.addMouseMotionListener(new MouseAdapter() {
			@Override
			public void mouseDragged(MouseEvent e) {
				drawArea.setFinalPoint(e.getX(), e.getY());
				drawArea.draw();
			}

			@Override
			public void mouseMoved(MouseEvent e) {
				label4.setText("(" + e.getX() + "," + e.getY() + ")");
			}
		});
		add(drawArea, BorderLayout.CENTER);

		// 设置按钮选择区
		panel1 = new JPanel(new BorderLayout()); // 存放第一排和第二排按钮
		add(panel1, BorderLayout.NORTH);
		panel2 = new JPanel(new FlowLayout()); // 存放第一排按钮
		panel3 = new JPanel(new FlowLayout()); // 存放第二排按钮
		panel1.add(panel2, BorderLayout.NORTH);
		panel1.add(panel3, BorderLayout.SOUTH);
		panel4 = new JPanel(new BorderLayout()); // 存放左下角坐标标签
		add(panel4, BorderLayout.SOUTH);

		// 设置第一排按钮
		button1 = new JButton("Undo");
		button2 = new JButton("Clear");
		label1 = new JLabel("Shape:");
		comboBox = new JComboBox<String>(names);
		checkbox1 = new JCheckBox("Filled");
		checkbox1.setSelected(true);

		button1.setFont(font);
		button2.setFont(font);
		label1.setFont(font);
		comboBox.setFont(font);
		checkbox1.setFont(font);

		panel2.add(button1);
		panel2.add(button2);
		panel2.add(label1);
		panel2.add(comboBox);
		panel2.add(checkbox1);

		// 添加事件
		button1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				drawArea.undo();
			}
		});
		button2.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				drawArea.removaAll();

			}
		});
		comboBox.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent event) {
				if (event.getStateChange() == ItemEvent.SELECTED)
					drawArea.setType(comboBox.getSelectedIndex());
			}
		});
		checkbox1.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				if (checkbox1.isSelected())
					drawArea.setFilled(true);
				else
					drawArea.setFilled(false);
			}
		});

		// 设置第二排按钮
		checkbox2 = new JCheckBox("Use Gridient");
		checkbox2.setSelected(true);
		button3 = new JButton("1st Color");
		button4 = new JButton("2nd Color");
		label2 = new JLabel("Line Width:");
		textfield1 = new JTextField("10", 3);
		textfield2 = new JTextField("15", 3);
		label3 = new JLabel("Dash Length:");
		checkbox3 = new JCheckBox("Dashed");
		checkbox3.setSelected(true);

		checkbox2.setFont(font);
		button3.setFont(font);
		button4.setFont(font);
		label2.setFont(font);
		textfield1.setFont(font);
		textfield2.setFont(font);
		label3.setFont(font);
		checkbox3.setFont(font);

		panel3.add(checkbox2);
		panel3.add(button3);
		panel3.add(button4);
		panel3.add(label2);
		panel3.add(textfield1);
		panel3.add(textfield2);
		panel3.add(label3);
		panel3.add(checkbox3);

		// 添加事件
		checkbox2.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				if (checkbox2.isSelected())
					drawArea.setGridient(true);
				else
					drawArea.setGridient(false);
			}
		});
		button3.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Color temp = JColorChooser.showDialog(DrawUI.this, "Choose 1st Color", Color.RED);
				if (temp == null)
					drawArea.setColor1(Color.RED);
				else
					drawArea.setColor1(temp);

			}
		});
		button4.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Color temp = JColorChooser.showDialog(DrawUI.this, "Choose 2nd Color", Color.YELLOW);
				if (temp == null)
					drawArea.setColor2(Color.YELLOW);
				else
					drawArea.setColor2(temp);

			}
		});
		textfield1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String text = textfield1.getText();
				double lineWidth = 0;
				try {
					lineWidth = Double.parseDouble(text);
					if (lineWidth <= 0)
						throw new Exception();
					else
						drawArea.setLineWidth(lineWidth);
				} catch (Exception e1) {
					JOptionPane.showMessageDialog(DrawUI.this, "请输入大于零的数字!");
				}

			}
		});
		textfield2.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String text = textfield2.getText();
				double dashLength = 0;
				try {
					dashLength = Double.parseDouble(text);
					if (dashLength <= 0)
						throw new Exception();
					else
						drawArea.setDashLength(dashLength);
				} catch (Exception e1) {
					JOptionPane.showMessageDialog(DrawUI.this, "请输入大于零的数字!");
				}

			}
		});
		checkbox3.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				if (checkbox3.isSelected())
					drawArea.setDashed(true);
				else
					drawArea.setDashed(false);
			}
		});
		// 设置左下角坐标标签
		label4 = new JLabel();
		label4.setFont(font);
		panel4.add(label4, BorderLayout.WEST);
	}
}
