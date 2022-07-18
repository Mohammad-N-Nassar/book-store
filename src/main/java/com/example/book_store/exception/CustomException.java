package com.example.book_store.exception;

import org.springframework.http.HttpStatus;

public class CustomException extends Exception{

	private HttpStatus status;
	public CustomException(String message, HttpStatus httpStatus) {
		super(message);
		this.status = httpStatus;
	}

	public CustomException(String message) {
		super(message);
	}

	public HttpStatus getStatus() {
		return status;
	}

	public void setStatus(HttpStatus status) {
		this.status = status;
	}
}
