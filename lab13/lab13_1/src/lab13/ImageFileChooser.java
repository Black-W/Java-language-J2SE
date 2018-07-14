package lab13;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileFilter;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import java.awt.event.ActionListener;
import java.io.File;
import java.awt.event.ActionEvent;

public class ImageFileChooser extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JFileChooser fileChooser;
	private JPanel contentPane;
	private JTextArea textArea;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ImageFileChooser frame = new ImageFileChooser();
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
	public ImageFileChooser() {
		// 初始化文件选择器
		fileChooser = new JFileChooser(new File("C:\\Users\\Wang\\Desktop"));
		fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
		//设置选择多个文件
		fileChooser.setMultiSelectionEnabled(true);
		//添加文件过滤器
		fileChooser.setFileFilter(new ImageFilter());
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);

		JButton button = new JButton("打开文件");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int result = fileChooser.showOpenDialog(ImageFileChooser.this);
				if (result == JFileChooser.APPROVE_OPTION) {
					File[] files = fileChooser.getSelectedFiles();
					for (File file : files) {
						textArea.append(file.getAbsolutePath() + "\n");
					}
				}

			}
		});
		contentPane.add(button, BorderLayout.NORTH);

		JScrollPane scrollPane = new JScrollPane();
		contentPane.add(scrollPane, BorderLayout.CENTER);

		textArea = new JTextArea();
		scrollPane.setViewportView(textArea);
	}

	//图片文件过滤器类
	class ImageFilter extends FileFilter {

		public boolean accept(File f) {
			if (f.isDirectory()) {
				return true;
			}

			int index = f.getName().lastIndexOf('.');
			String extension = f.getName().substring(index + 1).toLowerCase();
			if (extension != null) {
				if (extension.equals("jpeg") || extension.equals("jpg") || extension.equals("png")) {
					return true;
				} else {
					return false;
				}
			}
			return false;
		}

		public String getDescription() {
			return "图片文件(*.jpg, *.jpeg, *.png)";
		}
	}
}
