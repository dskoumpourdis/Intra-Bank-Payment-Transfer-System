package com.example.modernbankplc.intrabankpaymenttransfersystem.transfer.resource;

import com.example.modernbankplc.intrabankpaymenttransfersystem.domain.Balance;
import com.example.modernbankplc.intrabankpaymenttransfersystem.domain.Statement;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString(callSuper = true)
public class AccountResource extends BaseResource {
	private Long accountNum;
	private BalanceResource balance;
	private StatementResource statement;
}
