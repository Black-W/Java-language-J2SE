package lab4_1;

import java.util.Random;

public class Craps {
	private static final Random randomNumbers = new Random();

	// enumeration with constants that represent the game status
	private enum Status {
		CONTINUE, WON, LOST
	};

	// constants that represent common rolls of the dice
	private static final int SNAKE_EYES = 2;
	private static final int TREY = 3;
	private static final int SEVEN = 7;
	private static final int YO_LEVEN = 11;
	private static final int BOX_CARS = 12;

	// plays one game of craps
	public static void main(String[] args) {
		final int runTimes = 1000000;
		int sumOfWin = 0;
		int winTimes[]=new int [21];	//winTimes[i]=j,means win times on the i roll is j
		int loseTimes[]=new int [21];	//loseTimes[i]=j,means lose times on the i roll is j
		int sumOfLenth = 0;
		
		//set zero
		for(int i=0;i<21;i++) {
			winTimes[i]=0;
			loseTimes[i]=0;
		}
		
		//run 1000000 games
		for (int round = 0; round < runTimes; round++) {
			int myPoint = 0; // point if no win or loss on first roll
			Status gameStatus;
			int sumOfDice = rollDice();
			
			//calculate gameLenth
			int gameLength=1;
			
			switch (sumOfDice) {
			case SEVEN: // win with 7 on first roll
			case YO_LEVEN:	//win with 11 on first roll
				gameStatus = Status.WON;
				sumOfWin++;
				winTimes[0]++;
				break;
			case SNAKE_EYES: // lose with 2 on first roll
			case TREY: // lose with 3 on first roll
			case BOX_CARS: // lose with 12 on first roll
				gameStatus = Status.LOST;
				loseTimes[0]++;
				break;
			default: // did not win or lose,so remember point
				gameStatus = Status.CONTINUE;
				myPoint = sumOfDice;
//				System.out.println("Point is" + myPoint);
				break;
			}// end switch
			
			//while game is no complete
			while(gameStatus==Status.CONTINUE) {
				sumOfDice=rollDice();	//roll dice again
				gameLength++;
				
				if(sumOfDice==myPoint) {// win by making the same point
					gameStatus=Status.WON;
					sumOfWin++;
					if(gameLength<=20)
					winTimes[gameLength-1]++;
					else
						winTimes[20]++;
				}
				else if(sumOfDice==SEVEN) {	//lose by roll 7 before point
					gameStatus=Status.LOST;
					if(gameLength<=20)
						loseTimes[gameLength-1]++;
						else
							loseTimes[20]++;
				}
			}//end while
			
			//add averageLenth
			sumOfLenth+=gameLength;
			
			//display won or lost message
//			if(gameStatus==Status.WON) {
//				System.out.println("Player wins");
//				sumOfDice++;
//			}
//			else {
//				System.out.println("Player loses");
//			}
		}//end for()
		
		//display calculate message
		for(int i=0;i<20;i++) {
			System.out.printf("the win times and chances of winning on the"
					+ " roll %d is %d and %.4f\n",i+1,winTimes[i],(double)winTimes[i]/(winTimes[i]+loseTimes[i]));
		}
		System.out.printf("the win times and chances of winning after the "
				+ "twentieth roll is %d and %.4f\n",winTimes[20],(double)winTimes[20]/(winTimes[20]+loseTimes[20]));
		System.out.println("*********************************************");
		for(int i=0;i<20;i++) {
			System.out.println("the lose times on the roll "+(i+1)+" is "+loseTimes[i]);
		}
		System.out.println("the lose times after the twentieth roll is "+loseTimes[20]);
		System.out.println("*********************************************");
		System.out.printf("the chances of winning at craps is %.4f\n",(double)sumOfWin/runTimes);
		
		System.out.printf("the average lenth of a game of craps is %.4f\n",(double)sumOfLenth/runTimes);
		
		System.out.println("the chances of winning don't improve with the length of the game");
		
	}// end main

	// roll dice,calculate sum and display results
	public static int rollDice() {
		int die1 = 1 + randomNumbers.nextInt(6);
		int die2 = 1 + randomNumbers.nextInt(6);
		int sum = die1 + die2;

		// display results of this roll
//		System.out.printf("Player rolled %d +%d=%d\n", die1, die2, sum);
		return sum;
	}// end method rollDice
}// end class Craps
