package com.example.modernbankplc.intrabankpaymenttransfersystem.service;

import com.example.modernbankplc.intrabankpaymenttransfersystem.domain.Account;
import com.example.modernbankplc.intrabankpaymenttransfersystem.domain.Transaction;
import com.example.modernbankplc.intrabankpaymenttransfersystem.domain.TransactionType;
import com.example.modernbankplc.intrabankpaymenttransfersystem.exception.InsufficientBalanceException;
import com.example.modernbankplc.intrabankpaymenttransfersystem.repository.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Currency;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class AccountServiceImpl extends BaseServiceImpl<Account> implements AccountService {

	private final AccountRepository accountRepository;

	@Override
	public JpaRepository<Account, Long> getRepository() {
		return accountRepository;
	}

	@Override
	public void makeTransaction(final Long debtorId, final Long creditorId, final BigDecimal amount,
								final Currency currency) throws InsufficientBalanceException, NoSuchElementException {
		Account debtor = accountRepository.findById(debtorId).orElseThrow();
		Account creditor = accountRepository.findById(creditorId).orElseThrow();
		BigDecimal remainder = debtor.getBalance().getAmount().subtract(amount);
		if (remainder.compareTo(BigDecimal.ZERO) >= 0) {
			// Deduct the amount from the debtor
			debtor.getBalance().setAmount(remainder);
			// Add the amount to the creditor
			creditor.getBalance().setAmount(creditor.getBalance().getAmount().add(amount));
			// Create the transactions
			Transaction debtorTransaction = Transaction.builder()
													   .transactionType(TransactionType.DEBIT)
													   .amount(amount)
													   .currency(currency)
													   .accountNum(creditor.getAccountNum())
					                                   .statement(debtor.getStatement())
													   .build();
			Transaction creditorTransaction = Transaction.builder()
													   .transactionType(TransactionType.CREDIT)
													   .amount(amount)
													   .currency(currency)
													   .accountNum(debtor.getAccountNum())
													   .statement(creditor.getStatement())
													   .build();
			// Add the transactions to the statements
			debtor.getStatement().getTransactions().add(debtorTransaction);
			creditor.getStatement().getTransactions().add(creditorTransaction);
			// Persist the changes
			accountRepository.save(debtor);
			accountRepository.save(creditor);
		} else {
			throw new InsufficientBalanceException("Insufficient debtor balance");
		}
	}

}
