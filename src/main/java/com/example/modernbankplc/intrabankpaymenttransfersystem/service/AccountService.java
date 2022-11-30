package com.example.modernbankplc.intrabankpaymenttransfersystem.service;

import com.example.modernbankplc.intrabankpaymenttransfersystem.domain.Account;
import com.example.modernbankplc.intrabankpaymenttransfersystem.exception.InsufficientBalanceException;
import com.example.modernbankplc.intrabankpaymenttransfersystem.exception.NoSuchAccountException;

import java.math.BigDecimal;
import java.util.Currency;

public interface AccountService extends BaseService<Account, Long>{
	void makeTransaction(Long debtorId, Long creditorId, BigDecimal amount, Currency currency)
			throws InsufficientBalanceException, NoSuchAccountException;

//	Balance getBalance(Long id);
//
//	Statement getStatement(Long id);
//
//	Statement getMiniStatement(Long id);
}
