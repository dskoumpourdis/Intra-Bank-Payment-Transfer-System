package com.example.modernbankplc.intrabankpaymenttransfersystem.service;

import com.example.modernbankplc.intrabankpaymenttransfersystem.domain.Account;
import com.example.modernbankplc.intrabankpaymenttransfersystem.domain.Balance;
import com.example.modernbankplc.intrabankpaymenttransfersystem.domain.Statement;
import com.example.modernbankplc.intrabankpaymenttransfersystem.domain.Transaction;
import com.example.modernbankplc.intrabankpaymenttransfersystem.exception.InsufficientBalanceException;
import com.example.modernbankplc.intrabankpaymenttransfersystem.exception.NoSuchAccountException;
import com.example.modernbankplc.intrabankpaymenttransfersystem.repository.AccountRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Currency;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
class AccountServiceImplTest {

	@Mock
	AccountRepository accountRepository;

	@InjectMocks
	AccountServiceImpl accountService;
	@Test
	@DisplayName("makeTransaction() success")
	void makeTransaction() {
		Statement creditorStatement = Statement.builder().build();
		Statement debtorStatement = Statement.builder().build();

		Balance creditorBalance =
				Balance.builder().amount(BigDecimal.valueOf(100)).currency(Currency.getInstance("GBP")).build();
		Balance debtorBalance =
				Balance.builder().amount(BigDecimal.valueOf(100)).currency(Currency.getInstance("GBP")).build();

		Account creditor = new Account();
		creditor.setId(2L);
		creditor.setAccountNum(2L);
		creditor.setStatement(creditorStatement);
		creditor.setBalance(creditorBalance);

		Account debtor = new Account();
		debtor.setId(1L);
		debtor.setAccountNum(1L);
		debtor.setStatement(debtorStatement);
		debtor.setBalance(debtorBalance);

		when(accountRepository.findById(2L)).thenReturn(Optional.of(creditor));
		when(accountRepository.findById(1L)).thenReturn(Optional.of(debtor));

		accountService.makeTransaction(1L,2L, BigDecimal.valueOf(50), Currency.getInstance("GBP"));

//

		assertEquals(debtor.getStatement().getTransactions().size(),1);
//		assertTrue(debtor.getStatement().getTransactions().contains(debtorTransaction));
		assertEquals(debtor.getBalance().getAmount(), BigDecimal.valueOf(50));


		assertEquals(creditor.getStatement().getTransactions().size(), 1);
//		assertTrue(creditor.getStatement().getTransactions().contains(creditorTransaction));
		assertEquals(creditor.getBalance().getAmount(), BigDecimal.valueOf(150));
	}

	@Test
	@DisplayName("makeTransaction() fail NoSuchAccountException")
	void makeTransaction_NoSuchAccount() {

		assertThrows(
				NoSuchAccountException.class,
				() -> accountService.makeTransaction(
						1L,
						2L,
						BigDecimal.valueOf(50),
						Currency.getInstance("GBP")));
	}

	@Test
	@DisplayName("makeTransaction() fail InsufficientBalanceException")
	void makeTransaction_InsufficientBalance() {

		Balance creditorBalance =
				Balance.builder().amount(BigDecimal.valueOf(100)).currency(Currency.getInstance("GBP")).build();
		Balance debtorBalance =
				Balance.builder().amount(BigDecimal.valueOf(10)).currency(Currency.getInstance("GBP")).build();

		Account creditor = new Account();
		creditor.setId(2L);
		creditor.setAccountNum(2L);
		creditor.setBalance(creditorBalance);

		Account debtor = new Account();
		debtor.setId(1L);
		debtor.setAccountNum(1L);
		debtor.setBalance(debtorBalance);

		when(accountRepository.findById(2L)).thenReturn(Optional.of(creditor));
		when(accountRepository.findById(1L)).thenReturn(Optional.of(debtor));

		assertThrows(
				InsufficientBalanceException.class,
				() -> accountService.makeTransaction(
						1L,
						2L,
						BigDecimal.valueOf(50),
						Currency.getInstance("GBP")));
	}

	@Test
	@DisplayName("getMiniStatement() success")
	void getMiniStatement() {
		Set<Transaction> initialTransactions = Set.of(
				Transaction.builder().transactionDate(Timestamp.valueOf("2022-12-01 01:54:10.934")).build(),
				Transaction.builder().transactionDate(Timestamp.valueOf("2022-12-01 02:54:10.934")).build(),
				Transaction.builder().transactionDate(Timestamp.valueOf("2022-12-01 03:54:10.934")).build(),
				Transaction.builder().transactionDate(Timestamp.valueOf("2022-12-01 04:54:10.934")).build(),
				Transaction.builder().transactionDate(Timestamp.valueOf("2022-12-01 05:54:10.934")).build(),
				Transaction.builder().transactionDate(Timestamp.valueOf("2022-12-01 06:54:10.934")).build(),
				Transaction.builder().transactionDate(Timestamp.valueOf("2022-12-01 07:54:10.934")).build(),
				Transaction.builder().transactionDate(Timestamp.valueOf("2022-12-01 08:54:10.934")).build(),
				Transaction.builder().transactionDate(Timestamp.valueOf("2022-12-01 09:54:10.934")).build(),
				Transaction.builder().transactionDate(Timestamp.valueOf("2022-12-01 10:54:10.934")).build(),
				Transaction.builder().transactionDate(Timestamp.valueOf("2022-12-01 11:54:10.934")).build(),
				Transaction.builder().transactionDate(Timestamp.valueOf("2022-12-01 12:54:10.934")).build(),
				Transaction.builder().transactionDate(Timestamp.valueOf("2022-12-01 13:54:10.934")).build(),
				Transaction.builder().transactionDate(Timestamp.valueOf("2022-12-01 14:54:10.934")).build(),
				Transaction.builder().transactionDate(Timestamp.valueOf("2022-12-01 15:54:10.934")).build(),
				Transaction.builder().transactionDate(Timestamp.valueOf("2022-12-01 16:54:10.934")).build(),
				Transaction.builder().transactionDate(Timestamp.valueOf("2022-12-01 17:54:10.934")).build(),
				Transaction.builder().transactionDate(Timestamp.valueOf("2022-12-01 18:54:10.934")).build(),
				Transaction.builder().transactionDate(Timestamp.valueOf("2022-12-01 19:54:10.934")).build(),
				Transaction.builder().transactionDate(Timestamp.valueOf("2022-12-01 20:54:10.934")).build(),
				Transaction.builder().transactionDate(Timestamp.valueOf("2022-12-01 21:54:10.934")).build(),
				Transaction.builder().transactionDate(Timestamp.valueOf("2022-12-01 22:54:10.934")).build(),
				Transaction.builder().transactionDate(Timestamp.valueOf("2022-12-01 23:54:10.934")).build(),
				Transaction.builder().transactionDate(Timestamp.valueOf("2022-12-02 13:54:10.934")).build(),
				Transaction.builder().transactionDate(Timestamp.valueOf("2022-12-03 13:54:10.934")).build(),
				Transaction.builder().transactionDate(Timestamp.valueOf("2022-12-04 13:54:10.934")).build()
											  );
		Statement statement = Statement.builder().transactions(initialTransactions).build();
		Account account = new Account();
		account.setId(1L);
		account.setStatement(statement);

		when(accountRepository.findById(1L)).thenReturn(Optional.of(account));

		Set<Transaction> transactions = accountService.getMiniStatement(1L);

		assertEquals(20, transactions.size());
	}

	@Test
	@DisplayName("getMiniStatement() fail NoSuchAccountException")
	void getMiniStatement_NoSuchAccount() {
		assertThrows(NoSuchAccountException.class, () -> accountService.getMiniStatement(1L));
	}
}
