package com.example.book_store.service;

import com.example.book_store.exception.CustomException;
import com.example.book_store.model.Author;

import org.bson.types.ObjectId;
//this is a test here
import java.util.List;

public interface AuthorService {

	/**
	 * Get Author object by an id
	 * @param id id of the object to get
	 * @return Author object
	 * @throws CustomException When there is no object with the given id
	 */
	Author getById(ObjectId id) throws CustomException;

	/**
	 * Insert new Author to the database
	 * @param author Object to be inserted
	 * @throws CustomException When there is an object with same id exists
	 */
	void add(Author author) throws CustomException;

	/**
	 * Delete an author with a specific id
	 * @param id id of the author to be deleted
	 * @throws CustomException when there is no author with the given id
	 */
	void deleteById(ObjectId id) throws CustomException;

	/**
	 * Retrieve a list of all authors present
	 * @return List of type Author
	 */
	List<Author> getAuthors();

	/**
	 * Update an author with new data if it already exists
	 * @param author object with the new data
	 * @return The updated object
	 * @throws CustomException when the object doesn't already exist
	 */
	Author update(Author author) throws CustomException;
}
