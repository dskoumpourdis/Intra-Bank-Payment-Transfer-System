package com.example.modernbankplc.intrabankpaymenttransfersystem.service;

import com.example.modernbankplc.intrabankpaymenttransfersystem.domain.Account;
import com.example.modernbankplc.intrabankpaymenttransfersystem.domain.Balance;
import com.example.modernbankplc.intrabankpaymenttransfersystem.domain.Statement;
import com.example.modernbankplc.intrabankpaymenttransfersystem.domain.Transaction;
import com.example.modernbankplc.intrabankpaymenttransfersystem.domain.TransactionType;
import com.example.modernbankplc.intrabankpaymenttransfersystem.repository.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Currency;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AccountServiceImpl extends BaseServiceImpl<Account> implements AccountService {

	private final AccountRepository accountRepository;

	@Override
	public JpaRepository<Account, Long> getRepository() {
		return accountRepository;
	}

	@Override
	public void makeTransaction(final Account debtor, final Account creditor, final BigDecimal amount,
								final Currency currency) {
		if (accountRepository.findById(debtor.getId()).isPresent()) {
			if (accountRepository.findById(creditor.getId()).isPresent()) {
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
															   .accountId(creditor.getAccountId())
															   .build();
					Transaction creditorTransaction = Transaction.builder()
															   .transactionType(TransactionType.CREDIT)
															   .amount(amount)
															   .currency(currency)
															   .accountId(debtor.getAccountId())
															   .build();
					// Add the transactions to the statements
					debtor.getStatement().getTransactions().add(debtorTransaction);
					creditor.getStatement().getTransactions().add(creditorTransaction);
					// Persist the changes
					accountRepository.save(debtor);
					accountRepository.save(creditor);
				}
			}
		}
	}

	@Override
	public Balance getBalance(final Account account) {
		Optional<Account> optionalAccount = accountRepository.findById(account.getId());
		return optionalAccount.orElseThrow().getBalance();
	}

	@Override
	public Statement getStatement(final Account account) {
		Optional<Account> optionalAccount = accountRepository.findById(account.getId());
		return optionalAccount.orElseThrow().getStatement();
	}

	@Override
	public Statement getMiniStatement(final Account account) {
		return null;
	}

}
