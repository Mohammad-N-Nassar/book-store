package com.example.book_store.dao;

import com.example.book_store.exception.CustomException;
import com.example.book_store.model.Book;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;

import dev.morphia.Morphia;
import dev.morphia.dao.BasicDAO;
import dev.morphia.query.Query;
import dev.morphia.query.UpdateOperations;

import org.bson.types.ObjectId;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

@Repository
public class BookDaoImpl extends BasicDAO<Book, ObjectId> implements BookDao {

	public BookDaoImpl() {
		super(new MongoClient(new MongoClientURI("mongodb+srv://book-store:book-store@cluster0.zwqhidc.mongodb.net/?retryWrites=true&w=majority")),new Morphia(),"bookstore");
	}

	public BookDaoImpl(MongoClient mongoClient, Morphia morphia, String dbName) {
		super(mongoClient, morphia, dbName);
	}

	/**
	 * Returns an object with matching id from the books collection
	 * @param id id of the needed object
	 * @throws CustomException thrown when the book doesn't exist
	 * @return the requested book object
	 */
	@Override
	public Book getById(ObjectId id) throws CustomException {
		try {
			Book book = createQuery().filter("_id",id).find().next();
			return book;
		} catch(NoSuchElementException e) {
			throw new CustomException("Book with id " + id + " not found!", HttpStatus.NOT_FOUND);
		}
	}

	/**
	 * Adds a book to the database if it doesn't exist
	 * @param book Object to be added
	 * @throws CustomException thrown when the book already exists
	 */
	@Override
	public void add(Book book) throws CustomException {
		if (!exists("id", book.getId())) {
			save(book);
		} else {
			throw new CustomException("Book with id " + book.getId() + " already exists!", HttpStatus.BAD_REQUEST);
		}
	}

	/**
	 * Delete a book from the collection if it exists
	 * @param id id of the book to be deleted
	 * @throws CustomException thrown when the book doesn't exist
	 */
	@Override
	public void delete(ObjectId id) throws CustomException {
		try {
			Query<Book> query = createQuery();
			query.filter("_id",id).find().next();
			deleteByQuery(query);
		} catch (NoSuchElementException e) {
			throw new CustomException("Book with id " + id + " not found!", HttpStatus.NOT_FOUND);
		}
	}

	/**
	 * Get all books from the collection
	 * @return list of type Book
	 */
	@Override
	public List<Book> getBooks() {
		return createQuery().find().toList();
	}

	/**
	 * Update book with the same id only if it exists
	 * @param book book object with the new data
	 * @return updated book object
	 * @throws CustomException thrown when the book to be added doesn't exist
	 */
	public Book update(Book book) throws CustomException {
		if (exists("id", book.getId())) {
			save(book);
			return book;
		} else {
			throw new CustomException("Book with id " + book.getId() + " doesn't exist!", HttpStatus.NOT_FOUND);
		}
	}

	/**
	 * check if all books are available in a certain quantity or more in the collection
	 * @param bookQuantity map of book id matched with quantity needed
	 * @return boolean This return if all books available or not
	 * @throws CustomException when one of the books are not available in the needed quantity
	 */
	@Override
	public boolean booksAvailable(Map<ObjectId, Integer> bookQuantity) throws CustomException {

		if (bookQuantity.size() != 0) {
			for (Map.Entry<ObjectId, Integer> mapElement : bookQuantity.entrySet()) {
				ObjectId id = mapElement.getKey();
				int quantity = mapElement.getValue();

				Query<Book> query = createQuery().filter("_id", id).filter("quantity >=", quantity);
				if (!exists(query)) {
					throw new CustomException("Book with id " + id + " Not Available with the quantity " + quantity + "!", HttpStatus.BAD_REQUEST);
				}
			}
		} else {
			throw new CustomException("There are no books chosen!", HttpStatus.BAD_REQUEST);
		}
		return true;
	}

	/**
	 * decreases 1 from the quantity field for a book in the collection
	 * @param bookId book's id to be affected
	 */
	public void decreaseQuantity(ObjectId bookId, int quantity) {
		Query<Book> query = find().filter("_id",bookId);

		UpdateOperations<Book> updates = createUpdateOperations().inc("quantity", -quantity);

		update(query, updates);
	}

	/**
	 * increases 1 to the sold field for a book in the collection
	 * @param bookId book's id to be affected
	 */
	@Override
	public void increaseSold(ObjectId bookId, int quantity) {
		Query<Book> query = find().filter("_id",bookId);

		UpdateOperations<Book> updates = createUpdateOperations().inc("sold", quantity);

		update(query, updates);
	}

	/**
	 * Add more quantities to a certain book
	 * @param bookId id of the book to be affected
	 * @param quantity how many books added
	 * @throws CustomException when the needed book doesn't exist
	 */
	@Override
	public void increaseQuantity(ObjectId bookId, int quantity) throws CustomException {
		if (exists("_id", bookId)) {
			UpdateOperations<Book> updates = createUpdateOperations().inc("quantity", quantity);
			Query<Book> query = find();
			update(query, updates);
		} else {
			throw new CustomException("Book with id " + bookId + " doesn't exist", HttpStatus.BAD_REQUEST);
		}
	}

	/**
	 * Checks if a document with the given id exists
	 * @param id book id
	 * @return boolean true if document is present
	 */
	@Override
	public boolean exists(ObjectId id) {
		return exists("_id", id);
	}
}
