package lab12_3;

public class TransactionRecord {
	private int account;
	private double amount;

	public TransactionRecord() {
		this(0,0.0);
	}

	public TransactionRecord(int acct, double amt) {
		setAccount(acct);
		setAmount(amt);
	}
	
	public void setAccount(int account) {
		this.account = account;
	}
	
	public int getAccount() {
		return account;
	}
	
	public void setAmount(double amount) {
		this.amount = amount;
	}
	
	public double getAmount() {
		return amount;
	}
}
