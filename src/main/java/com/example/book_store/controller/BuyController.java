package com.example.book_store.controller;


import com.example.book_store.exception.CustomException;
import com.example.book_store.model.*;

import com.example.book_store.service.BuyService;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BuyController {

	@Autowired
	BuyService buyService;

	@PostMapping(
			value = "/api/buy",
			consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE
	)
	public BillDto buy(@RequestParam String orderDto) throws CustomException, JsonProcessingException {
		ObjectMapper objectMapper = new ObjectMapper();
		OrderDto mappedOrderDto = objectMapper.readValue(orderDto, OrderDto.class);
		return buyService.buyBooks(mappedOrderDto.getBuyerId(),mappedOrderDto.getBookId());
	}
}
