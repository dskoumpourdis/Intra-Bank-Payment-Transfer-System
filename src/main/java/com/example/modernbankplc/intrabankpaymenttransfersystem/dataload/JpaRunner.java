package com.example.modernbankplc.intrabankpaymenttransfersystem.dataload;

import com.example.modernbankplc.intrabankpaymenttransfersystem.base.BaseComponent;
import com.example.modernbankplc.intrabankpaymenttransfersystem.domain.Account;
import com.example.modernbankplc.intrabankpaymenttransfersystem.domain.Balance;
import com.example.modernbankplc.intrabankpaymenttransfersystem.domain.Statement;
import com.example.modernbankplc.intrabankpaymenttransfersystem.domain.Transaction;
import com.example.modernbankplc.intrabankpaymenttransfersystem.domain.TransactionType;
import com.example.modernbankplc.intrabankpaymenttransfersystem.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Currency;
import java.util.Set;

@Component
@RequiredArgsConstructor
public class JpaRunner extends BaseComponent implements CommandLineRunner {

	public static final int BALANCE_AMOUNT = 1000;
	public static final long ACCOUNT_ID_2 = 222L;
	public static final long ACCOUNT_ID_1 = 111L;
	public static final int TRANSACTION_AMOUNT = 100;
	private final AccountService accountService;

	@Override
	public void run(final String... args) {
		Set<Transaction> transactionList_1 = Set.of(
			Transaction.builder()
				.transactionType(TransactionType.DEBIT)
				.amount(BigDecimal.valueOf(TRANSACTION_AMOUNT))
				.currency(Currency.getInstance("GBP"))
				.accountNum(ACCOUNT_ID_2)
				.build(),
			Transaction.builder()
				.transactionType(TransactionType.CREDIT)
				.amount(BigDecimal.valueOf(TRANSACTION_AMOUNT))
				.currency(Currency.getInstance("GBP"))
				.accountNum(ACCOUNT_ID_2)
				.build(),
			Transaction.builder()
				.transactionType(TransactionType.CREDIT)
				.amount(BigDecimal.valueOf(TRANSACTION_AMOUNT))
				.currency(Currency.getInstance("GBP"))
				.accountNum(ACCOUNT_ID_2)
				.build(),
			Transaction.builder()
				.transactionType(TransactionType.CREDIT)
				.amount(BigDecimal.valueOf(TRANSACTION_AMOUNT))
				.currency(Currency.getInstance("GBP"))
				.accountNum(ACCOUNT_ID_2)
				.build(),
			Transaction.builder()
				.transactionType(TransactionType.CREDIT)
				.amount(BigDecimal.valueOf(TRANSACTION_AMOUNT))
				.currency(Currency.getInstance("GBP"))
				.accountNum(ACCOUNT_ID_2)
				.build(),
			Transaction.builder()
				.transactionType(TransactionType.CREDIT)
				.amount(BigDecimal.valueOf(TRANSACTION_AMOUNT))
				.currency(Currency.getInstance("GBP"))
				.accountNum(ACCOUNT_ID_2)
				.build(),
			Transaction.builder()
				.transactionType(TransactionType.CREDIT)
				.amount(BigDecimal.valueOf(TRANSACTION_AMOUNT))
				.currency(Currency.getInstance("GBP"))
				.accountNum(ACCOUNT_ID_2)
				.build(),
			Transaction.builder()
				.transactionType(TransactionType.CREDIT)
				.amount(BigDecimal.valueOf(TRANSACTION_AMOUNT))
				.currency(Currency.getInstance("GBP"))
				.accountNum(ACCOUNT_ID_2)
				.build(),
			Transaction.builder()
				.transactionType(TransactionType.CREDIT)
				.amount(BigDecimal.valueOf(TRANSACTION_AMOUNT))
				.currency(Currency.getInstance("GBP"))
				.accountNum(ACCOUNT_ID_2)
				.build(),
			Transaction.builder()
				.transactionType(TransactionType.CREDIT)
				.amount(BigDecimal.valueOf(TRANSACTION_AMOUNT))
				.currency(Currency.getInstance("GBP"))
				.accountNum(ACCOUNT_ID_2)
				.build(),
			Transaction.builder()
				.transactionType(TransactionType.CREDIT)
				.amount(BigDecimal.valueOf(TRANSACTION_AMOUNT))
				.currency(Currency.getInstance("GBP"))
				.accountNum(ACCOUNT_ID_2)
				.build(),
			Transaction.builder()
				.transactionType(TransactionType.CREDIT)
				.amount(BigDecimal.valueOf(TRANSACTION_AMOUNT))
				.currency(Currency.getInstance("GBP"))
				.accountNum(ACCOUNT_ID_2)
				.build(),
			Transaction.builder()
				.transactionType(TransactionType.CREDIT)
				.amount(BigDecimal.valueOf(TRANSACTION_AMOUNT))
				.currency(Currency.getInstance("GBP"))
				.accountNum(ACCOUNT_ID_2)
				.build(),
			Transaction.builder()
				.transactionType(TransactionType.CREDIT)
				.amount(BigDecimal.valueOf(TRANSACTION_AMOUNT))
				.currency(Currency.getInstance("GBP"))
				.accountNum(ACCOUNT_ID_2)
				.build(),
			Transaction.builder()
				.transactionType(TransactionType.CREDIT)
				.amount(BigDecimal.valueOf(TRANSACTION_AMOUNT))
				.currency(Currency.getInstance("GBP"))
				.accountNum(ACCOUNT_ID_2)
				.build(),
			Transaction.builder()
				.transactionType(TransactionType.CREDIT)
				.amount(BigDecimal.valueOf(TRANSACTION_AMOUNT))
				.currency(Currency.getInstance("GBP"))
				.accountNum(ACCOUNT_ID_2)
				.build(),
			Transaction.builder()
				.transactionType(TransactionType.CREDIT)
				.amount(BigDecimal.valueOf(TRANSACTION_AMOUNT))
				.currency(Currency.getInstance("GBP"))
				.accountNum(ACCOUNT_ID_2)
				.build(),
			Transaction.builder()
				.transactionType(TransactionType.CREDIT)
				.amount(BigDecimal.valueOf(TRANSACTION_AMOUNT))
				.currency(Currency.getInstance("GBP"))
				.accountNum(ACCOUNT_ID_2)
				.build(),
			Transaction.builder()
				.transactionType(TransactionType.CREDIT)
				.amount(BigDecimal.valueOf(TRANSACTION_AMOUNT))
				.currency(Currency.getInstance("GBP"))
				.accountNum(ACCOUNT_ID_2)
				.build(),
			Transaction.builder()
				.transactionType(TransactionType.CREDIT)
				.amount(BigDecimal.valueOf(TRANSACTION_AMOUNT))
				.currency(Currency.getInstance("GBP"))
				.accountNum(ACCOUNT_ID_2)
				.build(),
			Transaction.builder()
				.transactionType(TransactionType.CREDIT)
				.amount(BigDecimal.valueOf(TRANSACTION_AMOUNT))
				.currency(Currency.getInstance("GBP"))
				.accountNum(ACCOUNT_ID_2)
				.build(),
			Transaction.builder()
				.transactionType(TransactionType.CREDIT)
				.amount(BigDecimal.valueOf(TRANSACTION_AMOUNT))
				.currency(Currency.getInstance("GBP"))
				.accountNum(ACCOUNT_ID_2)
				.build(),
			Transaction.builder()
				.transactionType(TransactionType.CREDIT)
				.amount(BigDecimal.valueOf(TRANSACTION_AMOUNT))
				.currency(Currency.getInstance("GBP"))
				.accountNum(ACCOUNT_ID_2)
				.build(),
			Transaction.builder()
				.transactionType(TransactionType.CREDIT)
				.amount(BigDecimal.valueOf(TRANSACTION_AMOUNT))
				.currency(Currency.getInstance("GBP"))
				.accountNum(ACCOUNT_ID_2)
				.build(),
			Transaction.builder()
				.transactionType(TransactionType.CREDIT)
				.amount(BigDecimal.valueOf(TRANSACTION_AMOUNT))
				.currency(Currency.getInstance("GBP"))
				.accountNum(ACCOUNT_ID_2)
				.build()
		);

		Set<Transaction> transactionList_2 = Set.of(
			Transaction.builder()
				.transactionType(TransactionType.DEBIT)
				.amount(BigDecimal.valueOf(TRANSACTION_AMOUNT))
				.currency(Currency.getInstance("GBP"))
				.accountNum(ACCOUNT_ID_1)
				.build(),
			Transaction.builder()
				.transactionType(TransactionType.CREDIT)
				.amount(BigDecimal.valueOf(TRANSACTION_AMOUNT))
				.currency(Currency.getInstance("GBP"))
				.accountNum(ACCOUNT_ID_1)
				.build()
		);

		Statement statement_1 = Statement.builder().transactions(transactionList_1).build();
		Statement statement_2 = Statement.builder().transactions(transactionList_2).build();

		Balance balance_1 =
				Balance.builder().currency(Currency.getInstance("GBP")).amount(BigDecimal.valueOf(BALANCE_AMOUNT)).build();
		Balance balance_2 =
				Balance.builder().currency(Currency.getInstance("GBP")).amount(BigDecimal.valueOf(BALANCE_AMOUNT)).build();

		Account account_1 = Account.builder().accountNum(ACCOUNT_ID_1).statement(statement_1).balance(balance_1).build();
		Account account_2 = Account.builder().accountNum(ACCOUNT_ID_1).statement(statement_2).balance(balance_2).build();

		balance_1.setAccount(account_1);
		balance_2.setAccount(account_2);

		balance_1.setAccountNum(ACCOUNT_ID_1);
		balance_2.setAccountNum(ACCOUNT_ID_2);

		statement_1.setAccount(account_1);
		statement_2.setAccount(account_2);

		transactionList_1.forEach(i-> i.setStatement(statement_1));
		transactionList_2.forEach(i-> i.setStatement(statement_2));


		accountService.create(account_1);
		accountService.create(account_2);

		System.out.println(accountService.findAll());
	}
}
