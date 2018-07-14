package lab7_3;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.JTextArea;

public class KeyboardGame extends JFrame implements KeyListener{
	 private JTextArea textArea; // textarea to display output
	 private String words[]= {"word","apple","window","happy","good","bad","water","sand","nose","hate"};
	 private String nowWord=words[random.nextInt(10)];;
	 private String line1 = nowWord; // first line of textarea
	 private String line2 = "Key pressed: "; // second line of textarea
	 private String line3 = ""; // third line of textarea
	 private String entered = "";
	 private int score = 0;
	 private static Random random=new Random();
	   // KeyboardGame constructor
	   public KeyboardGame()
	   {
	      super("Typing Game");
	      setLayout(new BorderLayout());
	      setBackground(Color.WHITE);
	      addKeyListener(this); // allow frame to process key events

	      textArea = new JTextArea(100, 100); // set up JTextArea
	      textArea.setFont(new Font("黑体", Font.BOLD,25));
	      setLines();
	      textArea.setEnabled(false);
	      textArea.setDisabledTextColor(Color.BLACK);
	      
	      add(textArea,BorderLayout.CENTER); // add textarea to JFrame
	   } 

	   // handle press of any key
	   @Override
	   public void keyPressed(KeyEvent event){}

	   // handle release of any key
	   @Override
	   public void keyReleased(KeyEvent event){}

	   // handle press of an action key
	   @Override
	   public void keyTyped(KeyEvent event)
	   {
		   line1=nowWord;
		   entered+=event.getKeyChar();
		   line2 = "Key pressed: "+entered; 
		   //输入正确
		      if(entered.equals(nowWord)) {
		    	  System.out.println(nowWord);
		    	  line3="Type right!";
		    	  nowWord=words[random.nextInt(10)];
		    	  entered="";
		    	  line1=nowWord;
		    	  line2="Key pressed: ";
		    	  score+=10;
		      }
		      else {
		    	  //输入错误
		    	  boolean Wrong=false;
		    	  for(int i=0;i<entered.length();i++) {
		    		  if(entered.charAt(i)!=nowWord.charAt(i)) {
		    			  line3="Type wrong!Please try again!";
		    			  line2="Key pressed: ";
		    			  entered="";
		    			  score-=5;
		    			  Wrong=true;
		    			  break;
		    				  }
		    	  }
		    	  //未输入完
		    	  if(!Wrong)
		    		  line3="";
		      }
		   setLines(); // set output lines two and three
	   }

	   // set second and third lines of output
	   private void setLines()
	   {
	      textArea.setText(String.format("score:%d\n              %s\n\n  %s\n  %s\n", 
	         score,line1, line2, line3)); // output three lines of text
	   }
}
