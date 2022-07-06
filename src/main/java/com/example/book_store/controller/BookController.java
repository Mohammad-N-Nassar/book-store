package com.example.book_store.controller;

import com.example.book_store.exception.CustomException;
import com.example.book_store.model.Book;

import com.example.book_store.service.BookService;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
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

	@PostMapping("/add")
	public ResponseEntity<Map<String, Object>> add(@RequestBody Book book) throws CustomException {
		bookService.add(book);

		Map<String, Object> map = new HashMap<>();
		map.put("Message", "Book with id " + book.getId().toString() + " added successfully.");
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

	@PutMapping("/update")
	public Book update(@RequestBody Book book) throws CustomException {
		return bookService.update(book);
	}

	@PostMapping("/increaseQuantity/id/{id}/quantity/{quantity}")
	public ResponseEntity<Map<String, Object>> increaseQuantity(@PathVariable ObjectId id, @PathVariable int quantity) throws CustomException {
		bookService.increaseQuantity(id, quantity);

		Map<String, Object> map = new HashMap<>();
		map.put("Message", "Book increased successfully by " + quantity + ".");
		map.put("Status", HttpStatus.OK);
		map.put("Timestamp", System.currentTimeMillis());
		return new ResponseEntity<>(map, HttpStatus.OK);
	}


}
