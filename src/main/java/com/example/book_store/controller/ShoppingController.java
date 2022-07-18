package com.example.book_store.controller;

import com.example.book_store.exception.CustomException;
import com.example.book_store.model.BillDto;
import com.example.book_store.model.Order;
import com.example.book_store.model.OrderDto;

import com.example.book_store.service.ShoppingService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.bson.types.ObjectId;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("api/shop/")
public class ShoppingController {

	@Autowired
	ShoppingService shoppingService;

	@DeleteMapping("/delete-order/{id}")
	public ResponseEntity<Map<String, Object>> deleteById(@PathVariable String id) throws CustomException {
		shoppingService.deleteById(new ObjectId(id));

		Map<String, Object> map = new HashMap<>();
		map.put("Message", "Order " + id + " deleted successfully.");
		map.put("Status", HttpStatus.OK);
		map.put("Timestamp", System.currentTimeMillis());
		return new ResponseEntity<>(map, HttpStatus.OK);
	}

	@GetMapping("/orders")
	public List<Order> getOrders() {
		return shoppingService.getOrders();
	}

	@PostMapping(value = "/buy")
	public BillDto buy(@RequestParam String orderDto) throws CustomException, JsonProcessingException {
		ObjectMapper objectMapper = new ObjectMapper();
		OrderDto mappedOrderDto = objectMapper.readValue(orderDto, OrderDto.class);
		return shoppingService.buyBooks(mappedOrderDto.getBuyerId(),mappedOrderDto.getBookId());
	}
}
