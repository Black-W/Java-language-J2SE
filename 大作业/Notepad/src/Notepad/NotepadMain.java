package Notepad;

import javax.swing.SwingUtilities;
import javax.swing.UIManager;
/**
 * 
 * 记事本主类
 * 分配了UI线程，使用了WindowsLookAndFeel，并将记事本UI和管理器绑定在一起
 * 
 * @author 王振伟
 * @email 664129552@qq.com
 * @date 2018-05-15
 *
 */
public class NotepadMain {

	public static void main(String[] args) {
		//创建UI线程
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				//使用Windows风格的界面
				try {
					UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
				} catch (Exception e) {
					e.printStackTrace();
				}
				
				//创建UI和管理器，并绑定
				NotepadUI UI = new NotepadUI();
				NotepadManager notepadManager=new NotepadManager();
				UI.setManager(notepadManager);
				notepadManager.setUI(UI);
			}
		});
	}//end main
}
