package com.example.book_store.dao;

import com.example.book_store.exception.CustomException;
import com.example.book_store.model.Book;

import org.bson.types.ObjectId;

import java.util.List;
import java.util.Map;

public interface BookDao {

	Book getById(ObjectId id) throws CustomException;

	void add(Book book) throws CustomException;

	void delete(ObjectId id) throws CustomException;

	List<Book> getBooks();

	Book update(Book book) throws CustomException;

	boolean booksAvailable(Map<ObjectId, Integer> bookQuantity) throws CustomException;

	void decreaseQuantity(ObjectId bookId, int quantity);

	void increaseSold(ObjectId bookId, int quantity);

	void increaseQuantity(ObjectId bookId, int quantity) throws CustomException;

	boolean exists(ObjectId id);
}
