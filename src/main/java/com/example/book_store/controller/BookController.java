package com.example.book_store.controller;

import com.example.book_store.exception.CustomException;
import com.example.book_store.model.Book;

import com.example.book_store.service.BookService;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/book")
public class BookController {

	@Autowired
	BookService bookService;

	public BookController(BookService bookService) {
		this.bookService = bookService;
	}

	@PostMapping(
			value = "/add",
			consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE
	)
	public ResponseEntity<Map<String, Object>> add(@RequestParam String book) throws CustomException, JsonProcessingException {
		ObjectMapper objectMapper = new ObjectMapper();
		Book mappedBook = objectMapper.readValue(book, Book.class);
		mappedBook.setId(new ObjectId());
		bookService.add(mappedBook);

		Map<String, Object> map = new HashMap<>();
		map.put("Message", "Book with id " + mappedBook.getId().toString() + " added successfully.");
		map.put("Status", HttpStatus.OK);
		map.put("Timestamp", System.currentTimeMillis());
		return new ResponseEntity<>(map, HttpStatus.OK);
	}

	@GetMapping("/id/{id}")
	public Book getById(@PathVariable ObjectId id) throws CustomException {
		return bookService.getById(id);
	}

	@GetMapping("/books")
	public List<Book> getBooks() {
		return bookService.getBooks();
	}

	@DeleteMapping("/delete/{id}")
	public ResponseEntity<Map<String, Object>> deleteById(@PathVariable ObjectId id) throws CustomException {
		bookService.deleteById(id);

		Map<String, Object> map = new HashMap<>();
		map.put("Message", "Book with id " + id.toString() + " deleted successfully.");
		map.put("Status", HttpStatus.OK);
		map.put("Timestamp", System.currentTimeMillis());
		return new ResponseEntity<>(map, HttpStatus.OK);
	}

	@PutMapping(
			value = "/update",
			consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE
	)
	public Book update(@RequestParam String book) throws CustomException, JsonProcessingException {
		ObjectMapper objectMapper = new ObjectMapper();
		Book mappedBook = objectMapper.readValue(book, Book.class);
		return bookService.update(mappedBook);
	}

	@PostMapping(
			value = "/increaseQuantity",
			consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE
	)
	public ResponseEntity<Map<String, Object>> increaseQuantity(@RequestParam ObjectId id, @RequestParam int quantity) throws CustomException {
		bookService.increaseQuantity(id, quantity);

		Map<String, Object> map = new HashMap<>();
		map.put("Message", "Book increased successfully by " + quantity + ".");
		map.put("Status", HttpStatus.OK);
		map.put("Timestamp", System.currentTimeMillis());
		return new ResponseEntity<>(map, HttpStatus.OK);
	}


}
