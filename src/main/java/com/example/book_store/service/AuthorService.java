package com.example.book_store.service;

import com.example.book_store.exception.CustomException;
import com.example.book_store.model.Author;

import org.bson.types.ObjectId;

import java.util.List;

public interface AuthorService {

	Author getById(ObjectId id) throws CustomException;

	void add(Author author) throws CustomException;

	void deleteById(ObjectId id) throws CustomException;

	List<Author> getAuthors();

	Author update(Author author) throws CustomException;
}
