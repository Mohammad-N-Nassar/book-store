package com.example.book_store.controller;


import com.example.book_store.exception.CustomException;
import com.example.book_store.model.Buyer;
import com.example.book_store.service.BuyerService;

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
@RequestMapping("/api/buyer")
public class BuyerController {

	@Autowired
	BuyerService buyerService;

	@PostMapping("/add")
	public ResponseEntity<Map<String, Object>> add(@RequestParam String buyer) throws CustomException, JsonProcessingException {
		ObjectMapper objectMapper = new ObjectMapper();
		Buyer mappedBuyer = objectMapper.readValue(buyer, Buyer.class);
		mappedBuyer.setId(new ObjectId());
		buyerService.add(mappedBuyer);

		Map<String, Object> map = new HashMap<>();
		map.put("Message", "Buyer with id " + mappedBuyer.getId() + " added successfully.");
		map.put("Status", HttpStatus.OK);
		map.put("Timestamp", System.currentTimeMillis());
		return new ResponseEntity<>(map, HttpStatus.OK);
	}

	@GetMapping("/id/{id}")
	public Buyer getById(@PathVariable ObjectId id) throws CustomException {
		return buyerService.getById(id);
	}

	@GetMapping("/buyers")
	public List<Buyer> getBooks() {
		return buyerService.getBuyers();
	}

	@DeleteMapping("/delete/{id}")
	public ResponseEntity<Map<String, Object>> deleteById(@PathVariable ObjectId id) throws CustomException {
		buyerService.deleteById(id);
		Map<String, Object> map = new HashMap<>();
		map.put("Message", "Buyer with id " + id + " deleted successfully.");
		map.put("Status", HttpStatus.OK);
		map.put("Timestamp", System.currentTimeMillis());
		return new ResponseEntity<>(map, HttpStatus.OK);
	}

	@PutMapping(value = "/update")
	public Buyer update(@RequestParam String buyer) throws CustomException, JsonProcessingException {
		ObjectMapper objectMapper = new ObjectMapper();
		Buyer mappedBuyer = objectMapper.readValue(buyer, Buyer.class);
		return buyerService.update(mappedBuyer);
	}
}
