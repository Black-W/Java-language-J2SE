package lab12_3;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Formatter;
import java.util.Scanner;

public class FileMatch {
	private Scanner oldmastInput;
	private Scanner transInput;
	private Formatter newMastOutput;
	
	public void openfile() {
		try {
			oldmastInput = new Scanner(new File("oldmast.txt"));//read file
			transInput = new Scanner(new File("trans.txt"));//read file
			newMastOutput=new Formatter("newmast.txt");//output file
		}
		catch (SecurityException securityException) {
			System.err.println("You do not have write access to this file.");
			System.exit(1);
		}
		catch (FileNotFoundException fileNotFoundException) {
			System.err.println("Error opening or creating file.");
			System.exit(1);
		} 
	}
	
	public void startMatch() {
		ArrayList<AccountRecord> acctRecords=new ArrayList<AccountRecord>();//store all accountRecords
		ArrayList<TransactionRecord> transRecords=new ArrayList<TransactionRecord>();//store all transactionRecords
		
		//read all accountRecords
		while(oldmastInput.hasNext()) {
			AccountRecord acctRecord=new AccountRecord();
			acctRecord.setAccount(oldmastInput.nextInt());
			acctRecord.setFirstName(oldmastInput.next()); 
			acctRecord.setLastName(oldmastInput.next());
			acctRecord.setBalance(oldmastInput.nextDouble());
			acctRecords.add(acctRecord);
		}
		
		//rend all transactionRecords
		while(transInput.hasNext()) {
			TransactionRecord transRecord=new TransactionRecord();
			transRecord.setAccount(transInput.nextInt());
			transRecord.setAmount(transInput.nextDouble());
			transRecords.add(transRecord);
		}
		
		//matching
		System.out.println("matching result:\n"+"account   firstname  lastname  balance");
		for(AccountRecord acctRecord:acctRecords) {
			for(TransactionRecord transRecord:transRecords) {
				if(acctRecord.getAccount()==transRecord.getAccount()) {//if matched
					acctRecord.combine(transRecord);
					break;
				}
			}
			//output matching result to file newmast.txt and Console
			System.out.println(acctRecord.getAccount()+"\t  "+acctRecord.getFirstName()+"\t     "+
					acctRecord.getLastName()+"     "+acctRecord.getBalance());
			newMastOutput.format("%d %s %s %.2f\n", acctRecord.getAccount(), acctRecord.getFirstName(), acctRecord.getLastName(),
					acctRecord.getBalance());
		}
	}
	
	public void closeFile() {
		transInput.close();
		oldmastInput.close();
		newMastOutput.close();
	}
	
}
