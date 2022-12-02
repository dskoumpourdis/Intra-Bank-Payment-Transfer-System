package com.example.modernbankplc.intrabankpaymenttransfersystem.transfer.resource;

import com.example.modernbankplc.intrabankpaymenttransfersystem.domain.TransactionType;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Currency;

/**
 * Simple DTO for Transaction objects
 */
@Getter
@Setter
@ToString(callSuper = true)
public class TransactionResource {
	private Long accountNum;
	private BigDecimal amount;
	private TransactionType transactionType;
	private Currency currency;
	private Timestamp transactionDate;
}

