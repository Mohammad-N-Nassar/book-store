package com.example.book_store.service;

import com.example.book_store.dao.AuthorDao;
import com.example.book_store.exception.CustomException;
import com.example.book_store.model.Author;

import org.bson.types.ObjectId;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthorServiceImpl implements AuthorService {

	@Autowired
	public AuthorDao authorDao;

	/**
	 * Get Author object by an id
	 * @param id id of the object to get
	 * @return Author object
	 * @throws CustomException When there is no object with the given id
	 */
	@Override
	public Author getById(ObjectId id) throws CustomException {
		return authorDao.getById(id);
	}

	/**
	 * Insert new Author to the database
	 * @param author Object to be inserted
	 * @throws CustomException When there is an object with same id exists
	 */
	@Override
	public void add(Author author) throws CustomException {
		authorDao.add(author);
	}

	/**
	 * Delete an author with a specific id
	 * @param id id of the author to be deleted
	 * @throws CustomException when there is no author with the given id
	 */
	@Override
	public void deleteById(ObjectId id) throws CustomException {
		authorDao.delete(id);
	}

	/**
	 * Retrieve a list of all authors present
	 * @return List of type Author
	 */
	@Override
	public List<Author> getAuthors() {
		return authorDao.getAuthors();
	}

	/**
	 * Update an author with new data if it already exists
	 * @param author object with the new data
	 * @return The updated object
	 * @throws CustomException when the object doesn't already exist
	 */
	@Override
	public Author update(Author author) throws CustomException {
		return authorDao.update(author);
	}
}
