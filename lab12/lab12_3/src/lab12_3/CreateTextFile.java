package lab12_3;

import java.io.FileNotFoundException;
import java.util.Formatter;
import java.util.FormatterClosedException;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class CreateTextFile {
	private Formatter transOutput; // object used to output text to file trans.txt
	private Formatter oldMastOutput; // object used to output text to file oldmast.txt

	//open the file to output
	public void openFile() {
		try {
			transOutput = new Formatter("trans.txt"); // open the file
			oldMastOutput=new Formatter("oldmast.txt");
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
	
	// add acctRecords to file
	public void addAcctRecords() {
		// objects to be written to file
		AccountRecord acctRecord = new AccountRecord();
		
		Scanner input = new Scanner(System.in);
		System.out.println("please enter account records,enter account number 0 to quit");

		System.out.printf("%s\n%s", "Enter account number (> 0), first name, last name and balance.", "? ");
		
		while (input.hasNext()) // loop until end-of-file indicator
		{
			try
			{
				acctRecord.setAccount(input.nextInt()); // read account number
				acctRecord.setFirstName(input.next()); // read first name
				acctRecord.setLastName(input.next()); // read last name
				acctRecord.setBalance(input.nextDouble()); // read balance
				if (acctRecord.getAccount() > 0) {
					// write new record
					oldMastOutput.format("%d %s %s %.2f\n", acctRecord.getAccount(), acctRecord.getFirstName(), acctRecord.getLastName(),
							acctRecord.getBalance());
				}
				else {
					System.out.println("create account records success");
					break;
				}
			}
			catch (FormatterClosedException formatterClosedException) {
				System.err.println("Error writing to file.");
				return;
			}
			catch (NoSuchElementException elementException) {
				System.err.println("Invalid input. Please try again.");
				input.nextLine(); // discard input so user can try again
			}

			System.out.printf("%s %s\n%s", "Enter account number (>0),", "first name, last name and balance.", "? ");
		} // end while
	}

	// add records to file
		public void addTransRecords() {
			// objects to be written to file
			TransactionRecord transRecord=new TransactionRecord();
			
			Scanner input = new Scanner(System.in);
			System.out.println("please enter transaction records,enter account number 0 to quit");

			System.out.printf("%s\n%s", "Enter account number (> 0) and amount.", "? ");

			while (input.hasNext()) // loop until end-of-file indicator
			{
				try
				{
					transRecord.setAccount(input.nextInt()); // read account number
					transRecord.setAmount(input.nextDouble()); // read amount
					if (transRecord.getAccount() > 0) {
						// write new record
						transOutput.format("%d %.2f\n", transRecord.getAccount(),transRecord.getAmount());
					}
					else {
						System.out.println("create transaction records success");
						break;
					}
				}
				catch (FormatterClosedException formatterClosedException) {
					System.err.println("Error writing to file.");
					return;
				}
				catch (NoSuchElementException elementException) {
					System.err.println("Invalid input. Please try again.");
					input.nextLine(); // discard input so user can try again
				}

				System.out.printf("%s\n%s", "Enter account number (> 0) and amount.", "? ");
			} // end while
		}
		
	// close file
	public void closeFile() {
		if (transOutput != null)
			transOutput.close();
		if (oldMastOutput != null)
			oldMastOutput.close();
	} // end method closeFile

}
