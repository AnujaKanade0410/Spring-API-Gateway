package com.an.savingsa.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.an.savingsa.dto.AmountDto;
import com.an.savingsa.service.SavingsAService;

@RequestMapping("/a/balance")
@RestController
public class SavingsAController {
	
	@Autowired
	SavingsAService savingsAService;
	
	@GetMapping
	public ResponseEntity<String> getBalance() {
		String response=savingsAService.getBalanceForA();
		return ResponseEntity.ok(response);
	}
	
	@PostMapping
	public ResponseEntity<String> updateBalance(@RequestBody AmountDto amountDto) {
		String response=savingsAService.updateBalanceForA(amountDto.getAmount());
		return ResponseEntity.ok(response);
	}
}
