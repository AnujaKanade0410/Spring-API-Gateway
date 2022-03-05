package com.an.savingsb.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "account_details")
@Entity
public class AccountDetails {
	 @Id
	 @Column(name = "account_id")
	 private String account_id;
	 
	 @Column(name = "accountname")
	 private String accountname;
	 
	 @Column(name = "balance")
	 private String balance;
	 
	public String getAccount_id() {
		return account_id;
	}
	public void setAccount_id(String account_id) {
		this.account_id = account_id;
	}
	
	public String getAccountname() {
		return accountname;
	}
	public void setAccountname(String accountname) {
		this.accountname = accountname;
	}
	public String getBalance() {
		return balance;
	}
	public void setBalance(String balance) {
		this.balance = balance;
	}
	@Override
	public String toString() {
		return "AccountDetails [account_id=" + account_id + ", accountname=" + accountname + ", balance=" + balance
				+ "]";
	} 
	 
}
