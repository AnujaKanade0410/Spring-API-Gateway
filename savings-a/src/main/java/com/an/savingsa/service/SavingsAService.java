package com.an.savingsa.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.an.savingsa.entity.AccountDetails;
import com.an.savingsa.repository.AccountDetailsRepository;

@Service
public class SavingsAService {
	@Autowired
	private AccountDetailsRepository accountDetailsRepository;
	
	public String getBalanceForA() {
		double balance=0;
		Optional<AccountDetails> details=accountDetailsRepository.findByAccountname("savings-a");
		
		if(details.isPresent())
			balance= Double.parseDouble(details.get().getBalance());
		return String.valueOf(balance);
				
	}

	public String updateBalanceForA(double amount) {
		double updatedAmount=0;
		Optional<AccountDetails> details=accountDetailsRepository.findByAccountname("savings-a");
		if(details.isPresent()) {
			updatedAmount = Double.parseDouble(details.get().getBalance()) + amount;
			accountDetailsRepository.updateBalance("savings-a",String.valueOf(updatedAmount));
		}
		
		return "Updated balance: "+updatedAmount;
	}
}
