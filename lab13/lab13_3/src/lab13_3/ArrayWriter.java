package lab13_3;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Font;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.UIManager;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ArrayWriter extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private static JTextArea textArea;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
					ArrayWriter frame = new ArrayWriter();
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
	public ArrayWriter() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 750, 884);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));

		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.NORTH);

		JScrollPane scrollPane = new JScrollPane();
		contentPane.add(scrollPane, BorderLayout.CENTER);

		textArea = new JTextArea();
		textArea.setFont(new Font("Consolo", Font.PLAIN, 20));
		scrollPane.setViewportView(textArea);

		JButton btnStart = new JButton("Start");
		// 按钮监听
		btnStart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				// 建立数组
				Array array = new Array(50, textArea);

				// 建立5个线程任务
				ArrayTask task1 = new ArrayTask(array);
				ArrayTask task2 = new ArrayTask(array);
				ArrayTask task3 = new ArrayTask(array);
				ArrayTask task4 = new ArrayTask(array);
				ArrayTask task5 = new ArrayTask(array);

				// 建立线程池
				ExecutorService executorService = Executors.newCachedThreadPool();
				executorService.execute(task1);
				executorService.execute(task2);
				executorService.execute(task3);
				executorService.execute(task4);
				executorService.execute(task5);

				// 关闭线程
				executorService.shutdown();

				try {
					// 等待1分钟让所有的任务完成
					boolean tasksEnded = executorService.awaitTermination(1, TimeUnit.MINUTES);
					if (tasksEnded) {
						textArea.append("任务完成,数组为：\n");
						textArea.append(array.toString() + "\n");
					} else {
						textArea.append("超时未完成！\n");
					}
				} catch (InterruptedException e) {
					e.printStackTrace();
				}

			}
		});

		panel.add(btnStart);
	}

}
