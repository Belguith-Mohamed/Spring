package com.threetee.formationSpring.model.entity;

import java.util.Date;

public class Account extends AbstractEntity {
	
	private Long accountNumber;
	private long balance;

	public Account() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Account(Long id, Date modifiedAt, Date createdAt) {
		super(id, modifiedAt, createdAt);
		// TODO Auto-generated constructor stub
	}

	public Account(Long accountNumber, long balance) {
		super();
		this.accountNumber = accountNumber;
		this.balance = balance;
	}

	public Long getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(Long accountNumber) {
		this.accountNumber = accountNumber;
	}

	public long getBalance() {
		return balance;
	}

	public void setBalance(long balance) {
		this.balance = balance;
	}

	@Override
	public String toString() {
		return "Account [accountNumber=" + accountNumber + ", balance=" + balance + "]";
	}
	
	

}
