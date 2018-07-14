package Notepad;

import javax.swing.ButtonGroup;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPopupMenu;
import javax.swing.JRadioButton;
import javax.swing.JPopupMenu.Separator;
import javax.swing.undo.UndoManager;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.KeyStroke;
import javax.swing.JMenu;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.datatransfer.Clipboard;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;
import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

/**
 * 
 * 记事本界面类 ，设置了记事本的界面，并为其添加了监听器
 * 
 * @author 王振伟
 * @email 664129552@qq.com
 * @date 2018-05-15
 *
 */

public class NotepadUI extends JFrame {
	private static final long serialVersionUID = 1L;
	// 底部状态栏面板
	protected JPanel bottom;
	// 显示当前行号和列号的标签
	protected JLabel state;
	// 编辑区
	protected JTextArea textArea;
	// 弹出菜单
	protected JPopupMenu popupMenu;
	// 文档标题
	protected String fileName = "无标题";
	// 撤销
	protected JMenuItem undoItem;
	// 剪切
	protected JMenuItem cutItem;
	// 复制
	protected JMenuItem copyItem;
	// 粘贴
	protected JMenuItem pasteItem;
	// 全选
	protected JMenuItem selectAllItem;
	// 删除
	protected JMenuItem deleteItem;
	// 查找
	protected JMenuItem searchItem;
	// 查找下一个
	protected JMenuItem searchNextItem;
	// 跳转到某一行
	protected JMenuItem jumpToItem;
	// 自动换行
	protected JCheckBoxMenuItem autoTransfer;
	// 状态栏
	protected JCheckBoxMenuItem statusBar;
	// popMenu撤销
	protected JMenuItem popupUndoItem;
	// popMenu剪切
	protected JMenuItem popupCutItem;
	// popMenu复制
	protected JMenuItem popupCopyItem;
	// popMenu粘贴
	protected JMenuItem popupPasteItem;
	// popMenu全选
	protected JMenuItem popupSelectAllItem;
	// popMenu删除
	protected JMenuItem popupDeleteItem;
	// 撤销管理器
	protected UndoManager undoManager;
	// 获取剪贴板
	protected Clipboard clipboard = getToolkit().getSystemClipboard();
	// 编辑区字体
	protected Font editFont = new Font("宋体", Font.PLAIN, 20);
	// 主菜单
	private JMenuBar menuBar;
	// UI的管理器
	private NotepadManager uiManager;

	/**
	 * 设置UI的管理器
	 * 
	 * @param uiManager
	 */
	public void setManager(NotepadManager uiManager) {
		this.uiManager = uiManager;
	}

	/**
	 * 设置UI并添加监听事件
	 */
	public NotepadUI() {
		/* 设置JFrame的属性 */
		setJFrame();

		/* 创建菜单栏 */
		menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		createFileMenu();// 文件菜单栏
		createEditMenu();// 编辑菜单栏
		createFomatMenu();// 格式菜单栏
		createLookMenu();// 查看菜单栏
		createHelpMenu();// 帮助菜单栏

		/* 创建文本编辑区 */
		createTextArea();

		/* 创建状态栏 */
		createStateBar();
	}

	/**
	 * 设置文件菜单,包含新建、打开、保存、另存为、退出
	 */
	private void createFileMenu() {
		JMenu fileMenu = new JMenu("文件(F)");
		fileMenu.setMnemonic(KeyEvent.VK_F);// 添加主菜单快捷键

		// 新建
		JMenuItem newItem = new JMenuItem("新建(N)");
		newItem.setMnemonic(KeyEvent.VK_N);// 添加子菜单快捷键
		newItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, KeyEvent.CTRL_MASK));
		newItem.addActionListener(new ActionListener() {// 添加事件监听器
			@Override
			public void actionPerformed(ActionEvent e) {
				uiManager.createFile();
			}
		});
		// 打开
		JMenuItem openItem = new JMenuItem("打开(O)");
		openItem.setMnemonic(KeyEvent.VK_O);
		openItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, KeyEvent.CTRL_MASK));
		openItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				uiManager.openFile();
			}

		});
		// 保存
		JMenuItem saveItem = new JMenuItem("保存(S)");
		saveItem.setMnemonic(KeyEvent.VK_S);
		saveItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, KeyEvent.CTRL_MASK));
		saveItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				uiManager.saveFile();
			}

		});
		// 另存为
		JMenuItem saveForItem = new JMenuItem("另存为(A)...");
		saveForItem.setMnemonic(KeyEvent.VK_A);
		saveForItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				uiManager.saveForFile();
			}
		});
		// 退出
		JMenuItem exitItem = new JMenuItem("退出(X)");
		exitItem.setMnemonic(KeyEvent.VK_X);
		exitItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				uiManager.exit();
			}
		});
		fileMenu.add(newItem);
		fileMenu.add(openItem);
		fileMenu.add(saveItem);
		fileMenu.add(saveForItem);
		fileMenu.add(new Separator());
		fileMenu.add(exitItem);
		menuBar.add(fileMenu);
	}// end createFileMenu

	/**
	 * 设置编辑菜单，包含撤销、剪切、复制、粘贴、删除、查找、查找下一个、替换、转到、全选、时间/日期
	 */
	private void createEditMenu() {
		JMenu editMenu = new JMenu("编辑(E)");
		editMenu.setMnemonic(KeyEvent.VK_E);

		// 撤销
		undoItem = new JMenuItem("撤销(U)");
		undoItem.setEnabled(false);
		undoItem.setMnemonic(KeyEvent.VK_U);
		undoItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Z, KeyEvent.CTRL_MASK));
		undoItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				uiManager.undo();
			}
		});
		// 剪切
		cutItem = new JMenuItem("剪切(T)");
		cutItem.setEnabled(false);
		cutItem.setMnemonic(KeyEvent.VK_T);
		cutItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X, KeyEvent.CTRL_MASK));
		cutItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				uiManager.cut();
			}
		});
		// 复制
		copyItem = new JMenuItem("复制(C)");
		copyItem.setEnabled(false);
		copyItem.setMnemonic(KeyEvent.VK_C);
		copyItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C, KeyEvent.CTRL_MASK));
		copyItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				uiManager.copy();
			}
		});
		// 粘贴
		pasteItem = new JMenuItem("粘贴(P)");
		pasteItem.setMnemonic(KeyEvent.VK_P);
		pasteItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_V, KeyEvent.CTRL_MASK));
		if (clipboard.getContents(null) != null) {
			pasteItem.setEnabled(true);
		} else {
			pasteItem.setEnabled(false);
		}
		pasteItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				uiManager.paste();
			}
		});
		// 删除
		deleteItem = new JMenuItem("删除(L)");
		deleteItem.setEnabled(false);
		deleteItem.setMnemonic(KeyEvent.VK_L);
		deleteItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_DELETE, 0));
		deleteItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				uiManager.delete();
			}
		});
		// 查找
		searchItem = new JMenuItem("查找(F)...");
		searchItem.setEnabled(false);
		searchItem.setMnemonic(KeyEvent.VK_F);
		searchItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F, KeyEvent.CTRL_MASK));
		searchItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				uiManager.search();
			}
		});
		// 查找下一个
		searchNextItem = new JMenuItem("查找下一个(N)");
		searchNextItem.setEnabled(false);
		searchNextItem.setMnemonic(KeyEvent.VK_N);
		searchNextItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F3, 0));
		searchNextItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				uiManager.searchNext();
			}
		});
		// 替换
		JMenuItem replaceItem = new JMenuItem("替换(R)...");
		replaceItem.setMnemonic(KeyEvent.VK_R);
		replaceItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_R, KeyEvent.CTRL_MASK));
		replaceItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				uiManager.replace();
			}
		});
		// 转到
		jumpToItem = new JMenuItem("转到(G)...");
		jumpToItem.setMnemonic(KeyEvent.VK_G);
		jumpToItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_G, KeyEvent.CTRL_MASK));
		jumpToItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				uiManager.jumpTo();
			}
		});
		// 全选
		selectAllItem = new JMenuItem("全选(A)");
		selectAllItem.setMnemonic(KeyEvent.VK_A);
		selectAllItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A, KeyEvent.CTRL_MASK));
		selectAllItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				uiManager.selectAll();
			}
		});
		// 时间/日期
		JMenuItem timeItem = new JMenuItem("时间/日期(D)");
		timeItem.setMnemonic(KeyEvent.VK_D);
		timeItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F5, 0));
		timeItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				uiManager.time();
			}
		});
		editMenu.add(undoItem);
		editMenu.add(new Separator());
		editMenu.add(cutItem);
		editMenu.add(copyItem);
		editMenu.add(pasteItem);
		editMenu.add(deleteItem);
		editMenu.add(new Separator());
		editMenu.add(searchItem);
		editMenu.add(searchNextItem);
		editMenu.add(replaceItem);
		editMenu.add(jumpToItem);
		editMenu.add(new Separator());
		editMenu.add(selectAllItem);
		editMenu.add(timeItem);
		menuBar.add(editMenu);
	}// end createEditMenu

	/**
	 * 设置格式菜单,包含自动换行、字体
	 */
	private void createFomatMenu() {
		JMenu formatMenu = new JMenu("格式(O)");
		formatMenu.setMnemonic(KeyEvent.VK_O);

		// 自动换行
		autoTransfer = new JCheckBoxMenuItem("自动换行(W)");
		autoTransfer.setMnemonic(KeyEvent.VK_W);
		autoTransfer.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				uiManager.autoTransfer();
			}
		});
		// 字体
		JMenuItem selectFont = new JMenuItem("字体(F)...");
		selectFont.setMnemonic(KeyEvent.VK_F);
		selectFont.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				uiManager.selectFont();
			}
		});
		// 编码方式
		JMenu encoding = new JMenu("编码方式");
		JRadioButton UFT_8 = new JRadioButton("UTF-8");
		JRadioButton GBK = new JRadioButton("GBK");
		ButtonGroup group = new ButtonGroup();
		group.add(UFT_8);
		group.add(GBK);
		GBK.setSelected(true);
		encoding.add(UFT_8);
		encoding.add(GBK);
		UFT_8.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				uiManager.encoding = "UTF-8";
			}
		});
		GBK.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				uiManager.encoding = "GBK";
			}
		});

		formatMenu.add(autoTransfer);
		formatMenu.add(selectFont);
		formatMenu.add(encoding);
		menuBar.add(formatMenu);
	}// end createFomatMenu

	/**
	 * 设置查看菜单,包含状态栏
	 */
	private void createLookMenu() {
		JMenu lookMenu = new JMenu("查看(V)");
		lookMenu.setMnemonic(KeyEvent.VK_V);

		// 自动换行
		statusBar = new JCheckBoxMenuItem("状态栏(S)");
		statusBar.setMnemonic(KeyEvent.VK_S);
		statusBar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				uiManager.statusBar();
			}
		});
		lookMenu.add(statusBar);
		menuBar.add(lookMenu);
	}// end createLookOverMenu

	/**
	 * 设置帮助菜单,包含查看帮助、关于记事本
	 */
	private void createHelpMenu() {
		JMenu helpMenu = new JMenu("帮助(H)");
		helpMenu.setMnemonic(KeyEvent.VK_H);

		// 查看帮助
		JMenuItem lookHelp = new JMenuItem("查看帮助(H)");
		lookHelp.setMnemonic(KeyEvent.VK_H);
		lookHelp.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				uiManager.lookHelp();
			}
		});
		// 关于记事本
		JMenuItem about = new JMenuItem("关于记事本(A)");
		about.setMnemonic(KeyEvent.VK_A);
		about.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				uiManager.about();
			}
		});
		helpMenu.add(lookHelp);
		helpMenu.add(about);
		menuBar.add(helpMenu);
	}// end createHelpMenu

	/**
	 * 创建文本编辑区
	 */
	private void createTextArea() {
		JScrollPane scrollPane = new JScrollPane();
		// 水平滚动条始终显示
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		textArea = new JTextArea();
		textArea.setFont(editFont);
		scrollPane.setViewportView(textArea);
		getContentPane().add(scrollPane);

		// 为文本编辑区添加右键弹出菜单
		popupMenu = new JPopupMenu();
		// 撤销
		popupUndoItem = new JMenuItem("撤销(U)");
		popupUndoItem.setEnabled(false);
		popupUndoItem.setMnemonic(KeyEvent.VK_U);// 键盘快捷键
		popupUndoItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				popupMenu.setVisible(false);
				uiManager.undo();
			}
		});
		// 剪切
		popupCutItem = new JMenuItem("剪切(T)");
		popupCutItem.setEnabled(false);
		popupCutItem.setMnemonic(KeyEvent.VK_T);
		popupCutItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				popupMenu.setVisible(false);
				uiManager.cut();
			}
		});
		// 复制
		popupCopyItem = new JMenuItem("复制(C)");
		popupCopyItem.setEnabled(false);
		popupCopyItem.setMnemonic(KeyEvent.VK_C);
		popupCopyItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				popupMenu.setVisible(false);
				uiManager.copy();
			}
		});
		// 粘贴
		popupPasteItem = new JMenuItem("粘贴(P)");
		if (clipboard.getContents(null) != null) {
			popupPasteItem.setEnabled(true);
		} else {
			popupPasteItem.setEnabled(false);
		}
		popupPasteItem.setMnemonic(KeyEvent.VK_P);
		popupPasteItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				popupMenu.setVisible(false);
				uiManager.paste();
			}
		});
		// 删除
		popupDeleteItem = new JMenuItem("删除(L)");
		popupDeleteItem.setEnabled(false);
		popupDeleteItem.setMnemonic(KeyEvent.VK_L);
		popupDeleteItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				popupMenu.setVisible(false);
				uiManager.delete();
			}
		});
		// 全选
		popupSelectAllItem = new JMenuItem("全选(A)");
		popupSelectAllItem.setMnemonic(KeyEvent.VK_A);
		popupSelectAllItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				popupMenu.setVisible(false);
				uiManager.selectAll();
			}
		});
		popupMenu.add(popupUndoItem);
		popupMenu.add(new Separator());
		popupMenu.add(popupCutItem);
		popupMenu.add(popupCopyItem);
		popupMenu.add(popupPasteItem);
		popupMenu.add(popupDeleteItem);
		popupMenu.add(new Separator());
		popupMenu.add(popupSelectAllItem);

		// 为文本添加undo监听器
		undoManager = new UndoManager();
		textArea.getDocument().addUndoableEditListener(undoManager);

		// 为文本添加文档内容变化监听
		textArea.getDocument().addDocumentListener(new DocumentListener() {
			@Override
			public void changedUpdate(DocumentEvent e) {
				updateTextArea();
			}

			@Override
			public void insertUpdate(DocumentEvent e) {
				updateTextArea();
			}

			@Override
			public void removeUpdate(DocumentEvent e) {
				updateTextArea();
			}
		});

		// 为文本添加鼠标监听器
		textArea.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// 判断是否是弹出菜单
				checkForTriggerEvent(e);
			}

			// 鼠标释放后判断菜单是否失效
			@Override
			public void mouseReleased(MouseEvent e) {
				// 判断是否是弹出菜单
				checkForTriggerEvent(e);
				// 判断复制、剪切、删除菜单是否失效
				setEnabledAll();
			}

			private void checkForTriggerEvent(MouseEvent event) {
				if (event.isPopupTrigger())
					popupMenu.show(event.getComponent(), event.getX(), event.getY());
			}
		});

		// 为文本添加插入符监听器
		textArea.addCaretListener(new CaretListener() {
			@Override
			public void caretUpdate(CaretEvent e) {
				try {
					// e.getDot() 获得插入符的位置。
					int offset = e.getDot();

					// getLineOfOffset(int offset) 将组件文本中的偏移量转换为行号
					int row = textArea.getLineOfOffset(offset);

					// getLineStartOffset(int line) 取得给定行起始处的偏移量。
					int column = e.getDot() - textArea.getLineStartOffset(row);

					// 在状态栏中显示当前光标所在行号、所在列号
					state.setText("第" + (row + 1) + "行" + "，" + "第" + (column + 1) + "列");
				} catch (Exception ex) {
					ex.printStackTrace();
				}
			}
		});
	}// end createTextArea

	/**
	 * 文本区变化后的处理
	 */
	public void updateTextArea() {
		uiManager.isEdited = true;
		undoItem.setEnabled(true);
		popupUndoItem.setEnabled(true);
		if (!textArea.getText().equals("")) {
			searchItem.setEnabled(true);
			searchNextItem.setEnabled(true);
		} else {
			searchItem.setEnabled(false);
			searchNextItem.setEnabled(false);
		}
	}// end updateTextArea

	/**
	 * 设置复制、剪切、删除菜单是否失效
	 */
	public void setEnabledAll() {
		if (textArea.getSelectedText() != null) {
			copyItem.setEnabled(true);
			cutItem.setEnabled(true);
			deleteItem.setEnabled(true);
			popupCopyItem.setEnabled(true);
			popupCutItem.setEnabled(true);
			popupDeleteItem.setEnabled(true);
		} else {
			copyItem.setEnabled(false);
			cutItem.setEnabled(false);
			deleteItem.setEnabled(false);
			popupCopyItem.setEnabled(false);
			popupCutItem.setEnabled(false);
			popupDeleteItem.setEnabled(false);
		}
	}// end setEnabledAll

	/**
	 * 创建状态栏
	 */
	private void createStateBar() {
		bottom = new JPanel(new FlowLayout());
		// 状态栏左边的空白
		JLabel block = new JLabel();
		block.setPreferredSize(new Dimension((int) (getWidth() * 0.6), 17));
		// 显示当前光标所在行数和列数
		state = new JLabel("第 1 行，第 1 列");
		state.setHorizontalAlignment(SwingConstants.RIGHT);
		bottom.add(block);
		bottom.add(state);
		bottom.setVisible(false);
		add(bottom, BorderLayout.SOUTH);
	}// end createStateBar

	/**
	 * 设置JFrame的属性
	 */
	private void setJFrame() {
		// 设置UI的大小位置
		setSize(1000, 600);
		setLocationRelativeTo(null);
		setVisible(true);

		// 设置标题
		setTitle(fileName + " - 记事本");

		// 设置退出方式
		this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		this.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				uiManager.exit();// 重写默认关闭按钮
			}
		});
	}// end setJFrame
}
