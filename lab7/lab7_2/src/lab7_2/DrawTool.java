package lab7_2;

//用ArrayList存放数组，每个数组保存5个数字，图形类型+4个坐标，每次Dragged前先清空画板然后重绘
import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

class shape {
	int type; // 图形类别
	int argument[] = new int[4]; // 参数

	public shape(int n1, int n2, int n3, int n4, int n5) {
		type = n1;
		argument[0] = n2;
		argument[1] = n3;
		argument[2] = n4;
		argument[3] = n5;
	}

	public int getType() {
		return type;
	}

	public int getArgument(int i) {
		return argument[i];
	}
}

public class DrawTool extends JFrame {
	private JPanel contentPane;
	private int x, y;
	private int type = 0;
	private static ArrayList<shape> shapes = new ArrayList<shape>();
	private static Font font = new Font("consola", Font.PLAIN, 15);
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
		} catch (Exception e) {
			e.printStackTrace();
		}
		DrawTool frame = new DrawTool();
		frame.setVisible(true);

	}

	/**
	 * Create the frame.
	 */
	public DrawTool() {
		setTitle("DrawTool");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);

		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));

		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.NORTH);

		DrawPanel panel_1 = new DrawPanel();
		// 画板添加鼠标按压、释放监听器
		panel_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				x = e.getX();
				y = e.getY();
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				int x1 = e.getX();
				int y1 = e.getY();
				if (type == 1) {
					shape a = new shape(1, x, y, x1, y1);
					shapes.add(a);
				} else if (type == 2) {
					shape a = new shape(2, x, y, x1, y1);
					shapes.add(a);
				}
			}
		});
		// 画板添加鼠标拖拽监听器
		panel_1.addMouseMotionListener(new MouseAdapter() {
			@Override
			public void mouseDragged(MouseEvent e) {
				if (type == 1)
					panel_1.paintLine(x, y, e.getX(), e.getY());
				else if (type == 2)
					panel_1.paintRec(x, y, e.getX(), e.getY());
				// 重绘，保持图形消失
				for (shape nowShape : shapes) {
					if (nowShape.getType() == 1)
						panel_1.repaintLine(nowShape.getArgument(0), nowShape.getArgument(1), nowShape.getArgument(2),
								nowShape.getArgument(3));
					else if (nowShape.getType() == 2)
						panel_1.repaintRec(nowShape.getArgument(0), nowShape.getArgument(1), nowShape.getArgument(2),
								nowShape.getArgument(3));
				}
			}
		});
		panel_1.setBackground(Color.white);
		contentPane.add(panel_1, BorderLayout.CENTER);

		JButton button_1 = new JButton("直线");
		button_1.setFont(font);
		button_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				type = 1;
			}
		});
		panel.add(button_1);

		JButton button_2 = new JButton("矩形");
		button_2.setFont(font);
		button_2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				type = 2;
			}
		});
		panel.add(button_2);

		JButton button_3 = new JButton("清空图形");
		button_2.setFont(font);
		button_3.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				panel_1.removaAll();
				shapes.removeAll(shapes);
			}
		});
		panel.add(button_3);

	}

}
