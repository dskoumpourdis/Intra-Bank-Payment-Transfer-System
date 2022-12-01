package com.example.modernbankplc.intrabankpaymenttransfersystem.exception;

public class NoSuchAccountException extends RuntimeException {
	public NoSuchAccountException(final String message) {
		super(message);
	}
}
