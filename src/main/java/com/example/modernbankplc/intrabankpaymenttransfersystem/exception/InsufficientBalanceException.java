package com.example.modernbankplc.intrabankpaymenttransfersystem.exception;

public class InsufficientBalanceException extends RuntimeException {
	public InsufficientBalanceException(final String message) {
		super(message);
	}
}
