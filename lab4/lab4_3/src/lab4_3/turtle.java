package lab4_3;

//海龟朝向
enum direction {
	RIGHT,DOWN,LEFT,UP;
	private static direction[] vals = values();
	
	//返回上一个枚举值
    public direction previous() {
        return vals[(this.ordinal() - 1) % vals.length];
    }
    
	//返回下一个枚举值
    public direction next() {
        return vals[(this.ordinal() + 1) % vals.length];
    }

};//end direction

public class turtle {
	// 画板
	private int[][] pad;
	// 海龟坐标
	private int row;
	private int col;
	// 画笔位置，true表示down,false表示up
	private boolean penPosition;
	private direction turtleDirection;
	// 构造函数
	public turtle() {
		pad = new int[20][20];
		for (int i = 0; i < 20; i++) {
			for (int j = 0; j < 20; j++) {
				pad[i][j] = 0;
			}
		}
		row = 0;
		col = 0;
		penPosition = false;
		turtleDirection=direction.RIGHT;
	}// end 构造函数

	public void penUp() {
		penPosition = false;
	}// end penUp

	public void penDown() {
		penPosition = true;
	}// end penDown

	public void turnRight() {
		turtleDirection=turtleDirection.next();
	}// end turnRight
	
	public void turnLeft() {
		turtleDirection=turtleDirection.previous();
	}// end turnRight
	
	public boolean moveForward(int pace) {
		switch(turtleDirection){
			case LEFT:
				if((col-pace)<0) {
					System.out.println("Error.Moving across the border.");
					return false;
				}
				if(penPosition==true){
					for(int i=col;i>=col-pace;i--) {
						pad[row][i]=1;
					}
					col=col-pace;
				}
				else {
					col=col-pace;
				}
				return true;
			case RIGHT:
				if((col+pace)>19) {
					System.out.println("Error.Moving across the border.");
					return false;
				}
				if(penPosition==true) {
					for(int i=col;i<=col+pace;i++) {
						pad[row][i]=1;
					}
					col=col+pace;
				}
				else {
					col=col+pace;
				}
				return true;
			case UP:
				if((row-pace)<0) {
					System.out.println("Error.Moving across the border.");
					return false;
				}
				if(penPosition==true) {
					for(int i=row;i>=row-pace;i--) {
						pad[i][col]=1;
					}
					row=row-pace;
				}
				else {
					row=row-pace;
				}
				return true;
			case DOWN:
				if((row+pace)>19) {
					System.out.println("Error.Moving across the border.");
					return false;
				}
				if(penPosition==true) {
					for(int i=row;i<=row+pace;i++) {
						pad[i][col]=1;
					}
					row=row+pace;
				}
				else {
					row=row+pace;
				}
				return true;
			default: return false;
		}
	}//end moveForward
	
	public void displayPad() {
		for(int i=0;i<20;i++) {
			for(int j=0;j<20;j++) {
				if(i==row&&j==col)System.out.print("◉");
				else if(pad[i][j]==0)System.out.print("▢");
				else if(pad[i][j]==1)System.out.print("▣");
			}
			System.out.println();
		}
	}//end diplayPad
}//end turtle
