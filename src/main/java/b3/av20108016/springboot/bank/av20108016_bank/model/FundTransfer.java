package b3.av20108016.springboot.bank.av20108016_bank.model;

import com.fasterxml.jackson.annotation.JsonAlias;

public class FundTransfer {
	
	@JsonAlias("fromAccount")
	private int fromAccountNumber;
	
	@JsonAlias("toAccount")
	private int toAccountNumber;
	private double amount;
	
	public FundTransfer() {}
	
	public FundTransfer(int fromAccountNumber, int toAccountNumber, double amount) {
		super();
		this.fromAccountNumber = fromAccountNumber;
		this.toAccountNumber = toAccountNumber;
		this.amount = amount;
	}
	
	public int getFromAccountNumber() {
		return fromAccountNumber;
	}
	public void setFromAccountNumber(int fromAccountNumber) {
		this.fromAccountNumber = fromAccountNumber;
	}
	public int getToAccountNumber() {
		return toAccountNumber;
	}
	public void setToAccountNumber(int toAccountNumber) {
		this.toAccountNumber = toAccountNumber;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	
	

}
