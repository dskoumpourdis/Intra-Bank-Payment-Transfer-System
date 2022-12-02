package com.example.modernbankplc.intrabankpaymenttransfersystem.exception;

/**
 * Custom exception class which is used when a specific account cannot be found in the database.
 */
public class NoSuchAccountException extends RuntimeException {
	public NoSuchAccountException(final String message) {
		super(message);
	}
}
