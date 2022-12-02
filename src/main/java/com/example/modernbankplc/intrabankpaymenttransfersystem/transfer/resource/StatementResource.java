package com.example.modernbankplc.intrabankpaymenttransfersystem.transfer.resource;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.HashSet;
import java.util.Set;

/**
 * Simple DTO for Statement objects
 */
@Getter
@Setter
@ToString(callSuper = true)
public class StatementResource {
	private Set<TransactionResource> transactions = new HashSet<>();
}
