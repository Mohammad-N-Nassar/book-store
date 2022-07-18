package com.example.book_store.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class ExceptionsHandler {

	@ExceptionHandler
	public ResponseEntity<Map<String, Object>> customExceptionHandler(CustomException customException) {
		Map<String, Object> mapResponse = new HashMap<>();
		mapResponse.put("Status", customException.getStatus());
		mapResponse.put("Message", customException.getMessage());
		mapResponse.put("TimeStamp", System.currentTimeMillis());
		return new ResponseEntity<>(mapResponse, customException.getStatus());
	}

	@ExceptionHandler
	public ResponseEntity<Map<String, Object>> handleNotReadableException(HttpMessageNotReadableException httpMessageNotReadableException) {
		Map<String, Object> mapResponse = new HashMap<>();
		mapResponse.put("Status", HttpStatus.BAD_REQUEST);
		mapResponse.put("Message", httpMessageNotReadableException.getMessage());
		mapResponse.put("TimeStamp", System.currentTimeMillis());
		return new ResponseEntity<>(mapResponse, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler
	public ResponseEntity<Map<String, Object>> unknownError(Exception e) {
		Map<String, Object> map = new HashMap<>();
		map.put("Message", e.getMessage());
		map.put("Status", HttpStatus.INTERNAL_SERVER_ERROR);
		map.put("Timestamp", System.currentTimeMillis());
		return new ResponseEntity<>(map, HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
