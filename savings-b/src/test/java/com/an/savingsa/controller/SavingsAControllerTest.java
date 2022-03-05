package com.an.savingsa.controller;

import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.an.savingsb.controller.SavingsBController;
import com.an.savingsb.entity.AccountDetails;
import com.an.savingsb.repository.AccountDetailsRepository;
import com.an.savingsb.service.SavingsBService;

@RunWith(SpringRunner.class)
@WebMvcTest(controllers = SavingsBController.class)
public class SavingsAControllerTest {
	
	@MockBean
	private AccountDetailsRepository accountDetailsRepository;
	@MockBean
	private SavingsBService savingsAService;
	@InjectMocks
	private SavingsBController savingsAController;
	
	@Autowired
	private MockMvc mockMvc;
	
	AccountDetails accountDetails;
	
	public void createAccountDetails(){
		accountDetails = new AccountDetails();
		accountDetails.setAccount_id("1");
		accountDetails.setAccountname("saving-a");
		accountDetails.setBalance("4000.0");
	}
	
	@Before
	public void setUp() {
		createAccountDetails();
	}
	
	@Test
	public void testGetBalance() throws Exception {
		
		
		mockMvc.perform(MockMvcRequestBuilders
	            .get("/savings/a/balance")
	            .contentType(MediaType.APPLICATION_JSON))
	            .andExpect(status().isOk())
	            .andExpect(jsonPath("$[0].balance", is("4000.0")));
	}
	
}
