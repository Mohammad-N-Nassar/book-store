package com.example.book_store.controller;


import com.example.book_store.exception.CustomException;
import com.example.book_store.model.*;

import com.example.book_store.service.BuyService;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BuyController {

	@Autowired
	BuyService buyService;

	@PostMapping("/api/buy")
	public BillDto buy(@RequestBody OrderDto orderDto) throws CustomException {
		return buyService.buyBooks(orderDto.getBuyerId(),orderDto.getBookId());
	}
}
