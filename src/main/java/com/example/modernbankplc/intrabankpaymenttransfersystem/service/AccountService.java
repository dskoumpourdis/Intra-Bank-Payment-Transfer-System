package com.example.modernbankplc.intrabankpaymenttransfersystem.service;

import com.example.modernbankplc.intrabankpaymenttransfersystem.domain.Account;
import com.example.modernbankplc.intrabankpaymenttransfersystem.domain.Balance;
import com.example.modernbankplc.intrabankpaymenttransfersystem.domain.Statement;
import com.example.modernbankplc.intrabankpaymenttransfersystem.exception.InsufficientBalanceException;

import java.math.BigDecimal;
import java.util.Currency;
import java.util.NoSuchElementException;

public interface AccountService extends BaseService<Account, Long>{
	void makeTransaction(Long debtorId, Long creditorId, BigDecimal amount, Currency currency)
			throws InsufficientBalanceException, NoSuchElementException;

//	Balance getBalance(Long id);
//
//	Statement getStatement(Long id);
//
//	Statement getMiniStatement(Long id);
}
