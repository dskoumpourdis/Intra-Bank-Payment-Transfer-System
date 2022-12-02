package com.example.modernbankplc.intrabankpaymenttransfersystem.exception;

/**
 * Custom exception class which is used when the balance of an account is not enough for a transaction.
 */
public class InsufficientBalanceException extends RuntimeException {
	public InsufficientBalanceException(final String message) {
		super(message);
	}
}
