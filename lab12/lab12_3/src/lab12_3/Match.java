package lab12_3;

public class Match {
	public static void main(String[] args) {
		//create data on file trans.txt and oldmast.txt
		//if you need to rewrite data cancel the comment
//		CreateTextFile createData = new CreateTextFile();
//		createData.openFile();
//		createData.addAcctRecords();
//		createData.addTransRecords();
//		createData.closeFile();
		
		//file-matching output the result on file newmast.txt
		FileMatch fileMatch=new FileMatch();
		fileMatch.openfile();
		fileMatch.startMatch();
		fileMatch.closeFile();
		
	}
}
