package com.example.book_store.service;

import com.example.book_store.dao.BookDao;
import com.example.book_store.exception.CustomException;
import com.example.book_store.model.Book;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookServiceImpl implements BookService {

	@Autowired
	public BookDao bookDao;

	/**
	 * Get Book object by an id
	 * @param id id of the object to get
	 * @return Book object
	 * @throws CustomException When there is no object with the given id
	 */
	@Override
	public Book getById(ObjectId id) throws CustomException {
		return bookDao.getById(id);
	}

	/**
	 * Insert new Book to the database
	 * @param book Object to be Inserted
	 * @throws CustomException When there is an object with same id exists
	 */
	@Override
	public void add(Book book) throws CustomException {
		bookDao.add(book);
	}

	/**
	 * Delete a book with a specific id
	 * @param id id of the book to be deleted
	 * @throws CustomException when there is no book with the given id
	 */
	@Override
	public void deleteById(ObjectId id) throws CustomException {
		bookDao.delete(id);
	}

	/**
	 * Retrieve a list of all books present
	 * @return List of type Book
	 */
	@Override
	public List<Book> getBooks() {
		return bookDao.getBooks();
	}

	/**
	 * Update a book with new data if it already exists
	 * @param book object with the new data
	 * @return The updated object
	 * @throws CustomException when the object doesn't already exist
	 */
	@Override
	public Book update(Book book) throws CustomException {
		return bookDao.update(book);
	}

	/**
	 * increase quantity of a book
	 * @param id id of book to be affected
	 * @param quantity increase quantity by how much
	 * @throws CustomException when the book doesn't exist
	 */
	@Override
	public void increaseQuantity(ObjectId id, int quantity) throws CustomException {
		bookDao.increaseQuantity(id, quantity);
	}
}
