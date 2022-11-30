package com.example.modernbankplc.intrabankpaymenttransfersystem.controller;

import com.example.modernbankplc.intrabankpaymenttransfersystem.base.BaseComponent;
import com.example.modernbankplc.intrabankpaymenttransfersystem.exception.InsufficientBalanceException;
import com.example.modernbankplc.intrabankpaymenttransfersystem.exception.NoSuchAccountException;
import com.example.modernbankplc.intrabankpaymenttransfersystem.transfer.ApiError;
import com.example.modernbankplc.intrabankpaymenttransfersystem.transfer.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.util.NoSuchElementException;

@RestControllerAdvice
public class CustomisedExceptionHandler extends BaseComponent {
	@ExceptionHandler(Exception.class)
	public final ResponseEntity<ApiResponse<?>> handleAllExceptions(final Exception ex, final WebRequest request) {
		logger.error("Unexpected exception occurred.", ex);
		return new ResponseEntity<>(
				ApiResponse.builder().apiError(getApiError(ex, HttpStatus.INTERNAL_SERVER_ERROR, request)).build(),
				HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@ExceptionHandler(NoSuchElementException.class)
	public final ResponseEntity<ApiResponse<?>> handleNonExistence(final NoSuchElementException ex,
																   final WebRequest request) {
		logger.error("Reference to a non-existent object.", ex);
		return new ResponseEntity<>(ApiResponse.builder().apiError(
				getApiError(ex, HttpStatus.NOT_FOUND, request, "Reference to a non-existent object.")).build(),
									HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(NoSuchAccountException.class)
	public final ResponseEntity<ApiResponse<?>> handleAccountNonExistence(final NoSuchAccountException ex,
																   final WebRequest request) {
		logger.error("Invalid account number", ex);
		return new ResponseEntity<>(ApiResponse.builder().apiError(getApiError(ex, HttpStatus.NOT_FOUND, request,
																			   "Invalid account number"))
											   .build(), HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(InsufficientBalanceException.class)
	public ResponseEntity<ApiResponse<?>> handleInsufficientBalance(final InsufficientBalanceException ex,
																	final WebRequest request) {
		logger.error("Insufficient funds available", ex);
		return new ResponseEntity<>(ApiResponse.builder().apiError(
				getApiError(ex, HttpStatus.BAD_REQUEST, request, "Insufficient funds available")).build(),
									HttpStatus.BAD_REQUEST);
	}

	private ApiError getApiError(final Exception ex, final HttpStatus status, final WebRequest request) {
		String path = request.getDescription(false);
		if (path.indexOf("uri=") == 0) {
			path = StringUtils.replace(path, "uri=", "");
		}
		return new ApiError(status.value(), ex.getMessage(), path);
	}

	private ApiError getApiError(final Exception ex, final HttpStatus status, final WebRequest request,
								 String customMessage) {
		String path = request.getDescription(false);
		if (path.indexOf("uri=") == 0) {
			path = StringUtils.replace(path, "uri=", "");
		}
		return new ApiError(status.value(), customMessage != null ? customMessage : ex.getMessage(), path);
	}
}
