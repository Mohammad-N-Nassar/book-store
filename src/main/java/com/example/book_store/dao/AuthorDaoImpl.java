package com.example.book_store.dao;

import com.example.book_store.exception.CustomException;
import com.example.book_store.model.Author;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;

import dev.morphia.Morphia;
import dev.morphia.dao.BasicDAO;

import org.bson.types.ObjectId;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.NoSuchElementException;

@Repository
public class AuthorDaoImpl extends BasicDAO<Author, ObjectId> implements AuthorDao {


	public AuthorDaoImpl() {
		super( new MongoClient(new MongoClientURI("mongodb+srv://book-store:book-store@cluster0.zwqhidc.mongodb.net/?retryWrites=true&w=majority")), new Morphia(), "bookstore" );
	}
	public AuthorDaoImpl(MongoClient mongoClient, Morphia morphia, String dbName) {
		super(mongoClient, morphia, dbName);
	}

	/**
	 * Returns an object with matching id from authors collection
	 * @param id id of the needed document
	 * @throws CustomException thrown when the author doesn't exist
	 * @return the requested author object
	 */
	@Override
	public Author getById(ObjectId id) throws CustomException {
		try {
			Author author = createQuery().filter("_id", id).find().next();
			return author;
		} catch(NoSuchElementException e) {
			throw new CustomException("Author with id " + id + " not found!", HttpStatus.BAD_REQUEST);
		}
	}

	/**
	 * Adds an author to the collection if it doesn't exist
	 * @param author Object to be added
	 * @throws CustomException thrown when the author already exists
	 */
	@Override
	public void add(Author author) throws CustomException {
		if (!exists("id", author.getId())) {
			save(author);
		} else {
			throw new CustomException("Author with id " + author.getId() + " already exists!", HttpStatus.BAD_REQUEST);
		}
	}

	/**
	 * Delete an author from collection if it exists
	 * @param id id of the Author to be deleted
	 * @throws CustomException thrown when the buyer doesn't exist
	 */
	@Override
	public void delete(ObjectId id) throws CustomException {
		if(exists(id)) {
			deleteByQuery( find().filter("_id", id) );
		} else {
			throw new CustomException("Author with id " + id + " not found!", HttpStatus.NOT_FOUND);
		}
	}

	/**
	 * Get all documents from the author collection
	 * @return list of type Author
	 */
	@Override
	public List<Author> getAuthors() {
		return createQuery().find().toList();
	}

	/**
	 * Update author with the same id only if it exists
	 * @param author author object with the new data
	 * @return updated author object
	 * @throws CustomException thrown when the author to be added doesn't exist
	 */
	@Override
	public Author update(Author author) throws CustomException {
		if (exists("id", author.getId())) {
			save(author);
			return author;
		} else {
			throw new CustomException("Author with id " + author.getId() + " doesn't exist!", HttpStatus.NOT_FOUND);
		}
	}

	/**
	 * Checks if a document with the given id exists
	 * @param id author id
	 * @return boolean true if document is present
	 */
	@Override
	public boolean exists(ObjectId id) {
		return exists("_id", id);
	}
}
