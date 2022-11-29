package com.example.modernbankplc.intrabankpaymenttransfersystem.service;

import com.example.modernbankplc.intrabankpaymenttransfersystem.domain.Account;
import com.example.modernbankplc.intrabankpaymenttransfersystem.domain.Balance;
import com.example.modernbankplc.intrabankpaymenttransfersystem.domain.Statement;

import java.math.BigDecimal;
import java.util.Currency;

public interface AccountService extends BaseService<Account, Long>{
	void makeTransaction(Account debtor, Account creditor, BigDecimal amount, Currency currency);

	Balance getBalance(Account account);

	Statement getStatement(Account account);

	Statement getMiniStatement(Account account);
}
