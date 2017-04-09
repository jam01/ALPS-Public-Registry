package com.jam01.alps.domain.exception;

/**
 * Created by jam01 on 4/8/17.
 */
public class ValidationException extends RuntimeException {
	public ValidationException(String message) {
		super(message);
	}

	public ValidationException(String message, Exception exception) {
		super(message, exception);
	}
}
