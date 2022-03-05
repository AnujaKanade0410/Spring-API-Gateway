package com.an.savingsb.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.an.savingsb.entity.AccountDetails;
import com.an.savingsb.repository.AccountDetailsRepository;

@Service
public class SavingsBService {
	@Autowired
	private AccountDetailsRepository accountDetailsRepository;
	
	public String getBalanceForB() {
		double balance=0;
		Optional<AccountDetails> details=accountDetailsRepository.findByAccountname("savings-b");
		
		if(details.isPresent())
			balance= Double.parseDouble(details.get().getBalance());
		return String.valueOf(balance);
				
	}

	public String updateBalanceForB(double amount) {
		double updatedAmount=0;
		Optional<AccountDetails> details=accountDetailsRepository.findByAccountname("savings-b");
		if(details.isPresent()) {
			updatedAmount = Double.parseDouble(details.get().getBalance()) + amount;
			accountDetailsRepository.updateBalance("savings-b",String.valueOf(updatedAmount));
		}
		
		return "Updated balance: "+updatedAmount;
	}
}
