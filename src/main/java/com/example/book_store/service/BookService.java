package com.example.book_store.service;

import com.example.book_store.exception.CustomException;
import com.example.book_store.model.Book;

import org.bson.types.ObjectId;

import java.util.List;

public interface BookService {

	Book getById(ObjectId id) throws CustomException;

	void add(Book book) throws CustomException;

	void deleteById(ObjectId id) throws CustomException;

	List<Book> getBooks();

	Book update(Book book) throws CustomException;

	void increaseQuantity(ObjectId id, int quantity) throws CustomException;
}
