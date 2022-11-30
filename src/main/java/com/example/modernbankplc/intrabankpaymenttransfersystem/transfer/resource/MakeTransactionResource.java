package com.example.modernbankplc.intrabankpaymenttransfersystem.transfer.resource;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;
import java.util.Currency;

@Getter
@Setter
@ToString(callSuper = true)
public class MakeTransactionResource extends BaseResource {
	private Long creditorId;
	private Long debtorId;
	private BigDecimal amount;
	private Currency currency;
}
