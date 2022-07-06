package com.example.book_store.controller;


import com.example.book_store.exception.CustomException;
import com.example.book_store.model.Author;

import com.example.book_store.service.AuthorService;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/author")
public class AuthorController {

	@Autowired
	AuthorService authorService;

	@PostMapping("/add")
	public ResponseEntity<Map<String, Object>> add(@RequestBody Author author) throws CustomException {
		authorService.add(author);

		Map<String, Object> map = new HashMap<>();
		map.put("Message", "Author with id " + author.getId().toString() + " added successfully.");
		map.put("Status", HttpStatus.OK);
		map.put("Timestamp", System.currentTimeMillis());
		return new ResponseEntity<>(map, HttpStatus.OK);
	}

	@GetMapping("/id/{id}")
	public Author getById(@PathVariable ObjectId id) throws CustomException {
		return authorService.getById(id);
	}

	@GetMapping("/authors")
	public List<Author> getAuthors() {
		return authorService.getAuthors();
	}

	@DeleteMapping("/delete/{id}")
	public ResponseEntity<Map<String, Object>> deleteById(@PathVariable ObjectId id) throws CustomException {
		authorService.deleteById(id);
		Map<String, Object> map = new HashMap<>();
		map.put("Message", "Author with id " + id.toString() + " deleted successfully.");
		map.put("Status", HttpStatus.OK);
		map.put("Timestamp", System.currentTimeMillis());
		return new ResponseEntity<>(map, HttpStatus.OK);
	}

	@PutMapping("/update")
	public Author update(@RequestBody Author author) throws CustomException {
		return authorService.update(author);
	}
}
