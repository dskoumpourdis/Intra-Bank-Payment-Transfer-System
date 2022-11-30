package com.example.modernbankplc.intrabankpaymenttransfersystem.exception;

public class InsufficientBalanceException extends Exception {
	public InsufficientBalanceException(final String message) {
		super(message);
	}
}
