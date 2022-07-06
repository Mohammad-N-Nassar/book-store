package com.example.book_store.dao;

import com.example.book_store.exception.CustomException;
import com.example.book_store.model.Author;

import org.bson.types.ObjectId;

import java.util.List;

public interface AuthorDao {

	/**
	 * Returns an object with matching id from authors collection
	 * @param id id of the needed document
	 * @throws CustomException thrown when the author doesn't exist
	 * @return the requested author object
	 */
	Author getById(ObjectId id) throws CustomException;

	/**
	 * Adds an author to the collection if it doesn't exist
	 * @param author Object to be added
	 * @throws CustomException thrown when the author already exists
	 */
	void add(Author author) throws CustomException;

	/**
	 * Delete an author from collection if it exists
	 * @param id id of the Author to be deleted
	 * @throws CustomException thrown when the buyer doesn't exist
	 */
	void delete(ObjectId id) throws CustomException;

	/**
	 * Get all documents from the author collection
	 * @return list of type Author
	 */
	List<Author> getAuthors();

	/**
	 * Update author with the same id only if it exists
	 * @param author author object with the new data
	 * @return updated author object
	 * @throws CustomException thrown when the author to be added doesn't exist
	 */
	Author update(Author author) throws CustomException;

	boolean exists(ObjectId id);
}
