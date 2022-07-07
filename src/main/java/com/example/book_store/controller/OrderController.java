package com.example.book_store.controller;

import com.example.book_store.exception.CustomException;

import com.example.book_store.model.Order;
import com.example.book_store.service.OrderService;

import org.bson.types.ObjectId;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/order")
public class OrderController {

	@Autowired
	OrderService orderService;

	@DeleteMapping("/delete/{id}")
	public ResponseEntity<Map<String, Object>> deleteById(@PathVariable String id) throws CustomException {
		orderService.deleteById(new ObjectId(id));

		Map<String, Object> map = new HashMap<>();
		map.put("Message", "Order deleted successfully.");
		map.put("Status", HttpStatus.OK);
		map.put("Timestamp", System.currentTimeMillis());
		return new ResponseEntity<>(map, HttpStatus.OK);
	}

	@GetMapping("/orders")
	public List<Order> getOrders() {
		return orderService.getOrders();
	}
}
