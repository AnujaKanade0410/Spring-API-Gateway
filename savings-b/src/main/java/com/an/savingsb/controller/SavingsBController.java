package com.an.savingsb.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.an.savingsb.dto.AmountDto;
import com.an.savingsb.service.SavingsBService;

@RequestMapping("/b/balance")
@RestController
public class SavingsBController {
	
	@Autowired
	SavingsBService savingsBService;
	
	@GetMapping
	public ResponseEntity<String> getBalance() {
		String response=savingsBService.getBalanceForB();
		return ResponseEntity.ok(response);
	}
	
	@PostMapping
	public ResponseEntity<String> updateBalance(@RequestBody AmountDto amountDto) {
		String response=savingsBService.updateBalanceForB(amountDto.getAmount());
		return ResponseEntity.ok(response);
	}
}
