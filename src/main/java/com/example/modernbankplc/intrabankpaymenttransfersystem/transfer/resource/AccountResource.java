package com.example.modernbankplc.intrabankpaymenttransfersystem.transfer.resource;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * Simple DTO for Account objects
 */
@Getter
@Setter
@ToString(callSuper = true)
public class AccountResource extends BaseResource {
	private Long accountNum;
	private BalanceResource balance;
	private StatementResource statement;
}
