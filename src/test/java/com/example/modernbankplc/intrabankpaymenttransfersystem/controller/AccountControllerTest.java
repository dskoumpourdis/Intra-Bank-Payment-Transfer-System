package com.example.modernbankplc.intrabankpaymenttransfersystem.controller;

import com.example.modernbankplc.intrabankpaymenttransfersystem.domain.Account;
import com.example.modernbankplc.intrabankpaymenttransfersystem.exception.InsufficientBalanceException;
import com.example.modernbankplc.intrabankpaymenttransfersystem.exception.NoSuchAccountException;
import com.example.modernbankplc.intrabankpaymenttransfersystem.mapper.AccountMapper;
import com.example.modernbankplc.intrabankpaymenttransfersystem.service.AccountService;
import com.example.modernbankplc.intrabankpaymenttransfersystem.transfer.resource.AccountResource;
import com.example.modernbankplc.intrabankpaymenttransfersystem.transfer.resource.BalanceResource;
import com.example.modernbankplc.intrabankpaymenttransfersystem.transfer.resource.MakeTransactionResource;
import com.example.modernbankplc.intrabankpaymenttransfersystem.transfer.resource.StatementResource;
import com.example.modernbankplc.intrabankpaymenttransfersystem.transfer.resource.TransactionResource;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.util.Currency;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Set;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(AccountController.class)
class AccountControllerTest {

	@MockBean
	AccountService accountService;
	@MockBean
	AccountMapper accountMapper;
	@Autowired
	MockMvc mockMvc;

	@Test
	void find() throws Exception {
		AccountResource accountResource = new AccountResource();
		BalanceResource balanceResource = new BalanceResource();
		TransactionResource transactionResource = new TransactionResource();
		StatementResource statementResource = new StatementResource();
		Account account = new Account();

		statementResource.setTransactions(Set.of(transactionResource));
		accountResource.setBalance(balanceResource);
		accountResource.setId(1L);
		accountResource.setAccountNum(111L);
		accountResource.setStatement(statementResource);

		account.setId(1L);

		when(accountService.get(1L)).thenReturn(account);

		when(accountMapper.toResource(account)).thenReturn(accountResource);

		mockMvc.perform(get("/accounts/1")).andExpect(status().isOk()).andExpect(jsonPath("$.data.id", Matchers.is(1)))
			   .andExpect(jsonPath("$.data.accountNum", Matchers.is(111))).andExpect(
					   jsonPath("$.data.balance").exists()).andExpect(jsonPath("$.data.statement.transactions").exists());
	}

	@Test
	void find_NoSuchAccount() throws Exception {
		when(accountService.get(111L)).thenThrow(new NoSuchElementException());

		mockMvc.perform(get("/accounts/111")).andExpect(status().isNotFound()).andExpect(
				jsonPath("$.apiError").exists()).andExpect(jsonPath("$.apiError.status", Matchers.is(404))).andExpect(
				jsonPath("$.apiError.message", Matchers.is("Reference to a non-existent object.")));
	}

	@Test
	void findAll() throws Exception {

		List<Account> accounts = List.of(new Account(), new Account(), new Account());

		accounts.get(0).setAccountNum(111L);
		accounts.get(1).setAccountNum(112L);
		accounts.get(2).setAccountNum(113L);

		List<AccountResource> accountResources = List.of(new AccountResource(), new AccountResource(),
														 new AccountResource());

		accountResources.get(0).setAccountNum(111L);
		accountResources.get(1).setAccountNum(112L);
		accountResources.get(2).setAccountNum(113L);

		when(accountService.findAll()).thenReturn(accounts);

		when(accountMapper.toResources(accounts)).thenReturn(accountResources);

		mockMvc.perform(get("/accounts")).andExpect(status().isOk()).andExpect(jsonPath("$.data", hasSize(3)))
			   .andExpect(jsonPath("$.data[0].accountNum", Matchers.is(111))).andExpect(
					   jsonPath("$.data[1].accountNum", Matchers.is(112))).andExpect(
					   jsonPath("$.data[2].accountNum", Matchers.is(113)));
	}

	@Test
	void findAll_NoAccountsFound() throws Exception {
		mockMvc.perform(get("/accounts")).andExpect(jsonPath("$.data").exists()).andExpect(
				jsonPath("$.data").isEmpty());
	}

	@Test
	void getBalance() throws Exception {
		AccountResource account = new AccountResource();
		BalanceResource balanceResource = new BalanceResource();
		balanceResource.setAccountNum(111L);
		balanceResource.setCurrency(Currency.getInstance("GBP"));
		balanceResource.setAmount(BigDecimal.valueOf(1000));
		account.setBalance(balanceResource);

		when(accountMapper.toResource(any())).thenReturn(account);

		mockMvc.perform(get("/accounts/1/balance")).andExpect(status().isOk()).andExpect(
					   jsonPath("$.data.accountNum", Matchers.is(111))).andExpect(jsonPath("$.data.amount", Matchers.is(1000)))
			   .andExpect(jsonPath("$.data.currency", Matchers.is("GBP")));
	}

	@Test
	void getBalance_AccountNotFound() throws Exception {
		when(accountService.get(111L)).thenThrow(new NoSuchAccountException("Invalid account number."));

		mockMvc.perform(get("/accounts/111/balance")).andExpect(status().isNotFound()).andExpect(
				jsonPath("$.apiError").exists()).andExpect(jsonPath("$.apiError.status", Matchers.is(404))).andExpect(
				jsonPath("$.apiError.message", Matchers.is("Invalid account number.")));
	}

	@Test
	void getStatement() throws Exception {
		Set<TransactionResource> transactionResources = Set.of(new TransactionResource(), new TransactionResource(),
															   new TransactionResource(), new TransactionResource(),
															   new TransactionResource(), new TransactionResource(),
															   new TransactionResource(), new TransactionResource(),
															   new TransactionResource(), new TransactionResource(),
															   new TransactionResource(), new TransactionResource(),
															   new TransactionResource(), new TransactionResource(),
															   new TransactionResource(), new TransactionResource());
		StatementResource statementResource = new StatementResource();
		statementResource.setTransactions(transactionResources);

		AccountResource accountResource = new AccountResource();
		accountResource.setStatement(statementResource);
		accountResource.setId(1L);

		when(accountMapper.toResource(any())).thenReturn(accountResource);

		mockMvc.perform(get("/accounts/1/statements")).andExpect(status().isOk()).andExpect(
				jsonPath("$.data.transactions").exists()).andExpect(jsonPath("$.data.transactions", hasSize(16)));
	}

	@Test
	void getStatement_NoSuchAccount() throws Exception {
		when(accountService.get(111L)).thenThrow(new NoSuchAccountException("Invalid account number."));

		mockMvc.perform(get("/accounts/111/statements")).andExpect(status().isNotFound()).andExpect(
				jsonPath("$.apiError").exists()).andExpect(jsonPath("$.apiError.status", Matchers.is(404))).andExpect(
				jsonPath("$.apiError.message", Matchers.is("Invalid account number.")));
	}

	@Test
	void getMiniStatement_NoSuchAccount() throws Exception {
		when(accountService.getMiniStatement(111L)).thenThrow(new NoSuchAccountException("Invalid account number."));

		mockMvc.perform(get("/accounts/111/statements/mini")).andExpect(status().isNotFound()).andExpect(
				jsonPath("$.apiError").exists()).andExpect(jsonPath("$.apiError.status", Matchers.is(404))).andExpect(
				jsonPath("$.apiError.message", Matchers.is("Invalid account number.")));
	}

	@Test
	void getMiniStatement() throws Exception {
		Set<TransactionResource> transactionResources = Set.of(new TransactionResource(), new TransactionResource(),
															   new TransactionResource(), new TransactionResource(),
															   new TransactionResource(), new TransactionResource(),
															   new TransactionResource(), new TransactionResource(),
															   new TransactionResource(), new TransactionResource(),
															   new TransactionResource(), new TransactionResource(),
															   new TransactionResource(), new TransactionResource(),
															   new TransactionResource(), new TransactionResource());
		StatementResource statementResource = new StatementResource();
		statementResource.setTransactions(transactionResources);

		AccountResource accountResource = new AccountResource();
		accountResource.setStatement(statementResource);
		accountResource.setId(1L);

		when(accountMapper.toResource(any())).thenReturn(accountResource);

		mockMvc.perform(get("/accounts/1/statements/mini")).andExpect(status().isOk()).andExpect(
				jsonPath("$.data.transactions").exists()).andExpect(jsonPath("$.data.transactions", hasSize(16)));
	}

	@Test
	void makeTransaction() throws Exception {
		MakeTransactionResource makeTransactionResource = new MakeTransactionResource();

		mockMvc.perform(post("/accounts/makeTransaction").contentType(MediaType.APPLICATION_JSON).content(
				new ObjectMapper().writeValueAsString(makeTransactionResource))).andExpect(status().isOk());
	}

	@Test
	void makeTransaction_InsufficientBalance() throws Exception {
		MakeTransactionResource makeTransactionResource = new MakeTransactionResource();

		doThrow(new InsufficientBalanceException("Insufficient debtor balance.")).when(accountService).makeTransaction(any(), any(), any(), any());

		mockMvc.perform(post("/accounts/makeTransaction").contentType(MediaType.APPLICATION_JSON).content(
				new ObjectMapper().writeValueAsString(makeTransactionResource))).andExpect(status().isBadRequest());
	}

	@Test
	void makeTransaction_EmptyRequestBody() throws Exception {
		mockMvc.perform(post("/accounts/makeTransaction")).andExpect(status().isInternalServerError());
	}

	@Test
	void makeTransaction_NoSuchAccount() throws Exception {
		MakeTransactionResource makeTransactionResource = new MakeTransactionResource();

		doThrow(new NoSuchAccountException("Invalid account number.")).when(accountService).makeTransaction(any(), any(), any(), any());

		mockMvc.perform(post("/accounts/makeTransaction").contentType(MediaType.APPLICATION_JSON).content(
				new ObjectMapper().writeValueAsString(makeTransactionResource))).andExpect(status().isNotFound());
	}
}
