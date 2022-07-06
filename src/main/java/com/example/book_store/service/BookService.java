package com.example.book_store.service;

import com.example.book_store.exception.CustomException;
import com.example.book_store.model.Book;

import org.bson.types.ObjectId;

import java.util.List;

public interface BookService {

	/**
	 * Get Book object by an id
	 * @param id id of the object to get
	 * @return Book object
	 * @throws CustomException When there is no object with the given id
	 */
	Book getById(ObjectId id) throws CustomException;

	/**
	 * Insert new Book to the database
	 * @param book Object to be Inserted
	 * @throws CustomException When there is an object with same id exists
	 */
	void add(Book book) throws CustomException;

	/**
	 * Delete a book with a specific id
	 * @param id id of the book to be deleted
	 * @throws CustomException when there is no book with the given id
	 */
	void deleteById(ObjectId id) throws CustomException;

	/**
	 * Retrieve a list of all books present
	 * @return List of type Book
	 */
	List<Book> getBooks();

	/**
	 * Update a book with new data if it already exists
	 * @param book object with the new data
	 * @return The updated object
	 * @throws CustomException when the object doesn't already exist
	 */
	Book update(Book book) throws CustomException;

	/**
	 * increase quantity of a book
	 * @param id id of book to be affected
	 * @param quantity increase quantity by how much
	 * @throws CustomException when the book doesn't exist
	 */
	void increaseQuantity(ObjectId id, int quantity) throws CustomException;
}
