package com.example.book_store.dao;


import com.example.book_store.exception.CustomException;
import com.example.book_store.model.Buyer;

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
import java.util.NoSuchElementException;

@Repository
public class BuyerDaoImpl extends BasicDAO<Buyer, ObjectId> implements BuyerDao {

	public BuyerDaoImpl() {
		super(new MongoClient(new MongoClientURI("mongodb+srv://book-store:book-store@cluster0.zwqhidc.mongodb.net/?retryWrites=true&w=majority")), new Morphia(), "bookstore");
	}
	public BuyerDaoImpl(MongoClient mongoClient, Morphia morphia, String dbName) {
		super(mongoClient, morphia,dbName);
	}

	/**
	 * Returns an object with matching id from buyers collection
	 * @param id id of the needed document
	 * @throws CustomException thrown when the document doesn't exist
	 * @return the requested buyer object
	 */
	@Override
	public Buyer getById(ObjectId id) throws CustomException {
		try {
			Buyer buyer = createQuery().filter("_id", id).find().next();
			return buyer;
		} catch(NoSuchElementException e) {
			throw new CustomException("Buyer with id " + id + " not found!", HttpStatus.NOT_FOUND);
		}
	}

	/**
	 * Adds a buyer to the collection if it doesn't exist
	 * @param buyer Object to be added
	 * @throws CustomException thrown when the buyer already exists
	 */
	@Override
	public void add(Buyer buyer) throws CustomException {
		if (!exists("id", buyer.getId())) {
			save(buyer);
		}else {
			throw new CustomException("Buyer with id " + buyer.getId() + " already exists!", HttpStatus.BAD_REQUEST);
		}
	}

	/**
	 * Delete a buyer from collection if it exists
	 * @param id id of the buyer to be deleted
	 * @throws CustomException thrown when the buyer doesn't exist
	 */
	@Override
	public void delete(ObjectId id) throws CustomException {
		if (exists(id)) {
			deleteByQuery(find().filter("_id", id));
		} else {
			throw new CustomException("Buyer with id " + id + " not found!", HttpStatus.NOT_FOUND);
		}
	}

	/**
	 * Get all documents from the buyer collection
	 * @return list of type Buyer
	 */
	@Override
	public List<Buyer> getBuyers() {
		return createQuery().find().toList();
	}

	/**
	 * Update buyer with the same id only if it exists
	 * @param buyer buyer object with the new data
	 * @return updated buyer object
	 * @throws CustomException thrown when the buyer to be added doesn't exist
	 */
	@Override
	public Buyer update(Buyer buyer) throws CustomException {
		if (exists("id", buyer.getId())) {
			save(buyer);
			return buyer;
		} else {
			throw new CustomException("Buyer with id " + buyer.getId() + " doesn't exist!", HttpStatus.NOT_FOUND);
		}
	}

	/**
	 * Adds books' id to the books' field of the buyer
	 * @param bookId book id to be added
	 * @param buyerId buyer id to be affected
	 * @throws CustomException when no buyer with the given id exists
	 */
	@Override
	public void addToBooks(List<ObjectId> bookId, ObjectId buyerId) throws CustomException {
		if (exists(buyerId)) {
			Query<Buyer> query = createQuery().filter("_id", buyerId);
			UpdateOperations<Buyer> updates = createUpdateOperations().push("books", bookId);
			update(query, updates);
		} else {
			throw new CustomException("Buyer with id " + buyerId + " doesn't Exist!", HttpStatus.BAD_REQUEST);
		}
	}

	/**
	 * Checks if a document with the given id exists
	 * @param id buyer id
	 * @return boolean true if document is present
	 */
	@Override
	public boolean exists(ObjectId id) {
		return exists("_id", id);
	}
}
