package lab12_2;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

import lab8_2.DrawUI;

public class DesktopFrame extends JFrame {
	private final JDesktopPane desktopPane;
	
	public DesktopFrame() {
		super("DrawTool");
		// 创建菜单栏
		JMenuBar bar = new JMenuBar();
		JMenu addMenu = new JMenu("Add");
		JMenuItem newDrawPanel = new JMenuItem("NewDrawPanel");

		addMenu.add(newDrawPanel);
		bar.add(addMenu);
		setJMenuBar(bar);

		// 将desktopPane添加到JFrame中
		desktopPane = new JDesktopPane();
		add(desktopPane);

		// 给创建新画图板的菜单添加监听器
		newDrawPanel.addActionListener(new ActionListener() // 使用匿名内部类
		{
			@Override
			public void actionPerformed(ActionEvent event) {
						// 创建内部窗口
						JInternalFrame internalFrame = new JInternalFrame("DrawPanel", true, true, true, true);
						internalFrame.setSize(500, 500);
						
						DrawUI panel = new DrawUI();
						internalFrame.add(panel, BorderLayout.CENTER);

						desktopPane.add(internalFrame); // attach internal frame
						internalFrame.setVisible(true); // show internal frame
			}
		});
	} // end constructor DesktopFrame
} // end class DesktopFrame
