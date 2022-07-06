package com.example.book_store.dao;

import com.example.book_store.exception.CustomException;
import com.example.book_store.model.Book;

import org.bson.types.ObjectId;

import java.util.List;
import java.util.Map;

public interface BookDao {

	/**
	 * Returns an object with matching id from the books collection
	 * @param id id of the needed object
	 * @throws CustomException thrown when the book doesn't exist
	 * @return the requested book object
	 */
	Book getById(ObjectId id) throws CustomException;

	/**
	 * Adds a book to the database if it doesn't exist
	 * @param book Object to be added
	 * @throws CustomException thrown when the book already exists
	 */
	void add(Book book) throws CustomException;

	/**
	 * Delete a book from the collection if it exists
	 * @param id id of the author to be deleted
	 * @throws CustomException thrown when the author doesn't exist
	 */
	void delete(ObjectId id) throws CustomException;

	/**
	 * Get all books from the collection
	 * @return list of type Book
	 */
	List<Book> getBooks();

	/**
	 * Update author with the same id only if it exists
	 * @param book book object with the new data
	 * @return updated book object
	 * @throws CustomException thrown when the book to be added doesn't exist
	 */
	Book update(Book book) throws CustomException;

	/**
	 * check if all books are available in a certain quantity or more in the collection
	 * @param bookQuantity map of book id matched with quantity needed
	 * @return boolean This return if all books available or not
	 * @throws CustomException when one of the books are not available in the needed quantity
	 */
	boolean booksAvailable(Map<ObjectId, Integer> bookQuantity) throws CustomException;

	/**
	 * decreases 1 from the quantity field for a book in the collection
	 * @param bookId book's id to be affected
	 */
	void decreaseQuantity(ObjectId bookId, int quantity);

	/**
	 * increases 1 to the sold field for a book in the collection
	 * @param bookId book's id to be affected
	 */
	void increaseSold(ObjectId bookId, int quantity);

	void increaseQuantity(ObjectId bookId, int quantity) throws CustomException;

	boolean exists(ObjectId id);
}
