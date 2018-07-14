package lab4_3;

import java.util.Scanner;
import java.util.ArrayList;

public class TurtleGraphics {

	public static void main(String[] args) {
		turtle myturtle=new turtle();
		Scanner input=new Scanner(System.in);
		ArrayList<Integer> commands=new ArrayList<>();
		
		System.out.println("please type the set of turtle commands (end with 9)");
		int a=input.nextInt();
		while(a!=9) {
			if(a==5) {
				commands.add(a);
				a=input.nextInt();
				commands.add(a);
				a=input.nextInt();
				continue;
			}
			commands.add(a);
			a=input.nextInt();
		}
		for(int i=0;i<commands.size();i++) {
			switch(commands.get(i)) {
			case 1:
				myturtle.penUp();
				break;
			case 2:
				myturtle.penDown();
				break;
			case 3:
				myturtle.turnRight();
				break;
			case 4:
				myturtle.turnLeft();
				break;
			case 5:
				myturtle.moveForward(commands.get(++i)-1);
				break;
			case 6:
				myturtle.displayPad();
				break;
			case 9:
				break;
			}
		}
		input.close();
	}//end main
}
