// Fig. 24.14: TicTacToeServerTest.java
// Tests the TicTacToeServer.
package lab14_Server;

import javax.swing.JFrame;
import javax.swing.UIManager;

public class TicTacToeServerTest {
	public static void main(String args[]) {
		//使用Windows风格的界面
		try {
			UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		TicTacToeServer application = new TicTacToeServer();
		application.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		application.execute();
	} // end main
} // end class TicTacToeServerTest

/**************************************************************************
 * (C) Copyright 1992-2007 by Deitel & Associates, Inc. and * Pearson Education,
 * Inc. All Rights Reserved. * * DISCLAIMER: The authors and publisher of this
 * book have used their * best efforts in preparing the book. These efforts
 * include the * development, research, and testing of the theories and programs
 * * to determine their effectiveness. The authors and publisher make * no
 * warranty of any kind, expressed or implied, with regard to these * programs
 * or to the documentation contained in these books. The authors * and publisher
 * shall not be liable in any event for incidental or * consequential damages in
 * connection with, or arising out of, the * furnishing, performance, or use of
 * these programs. *
 *************************************************************************/
