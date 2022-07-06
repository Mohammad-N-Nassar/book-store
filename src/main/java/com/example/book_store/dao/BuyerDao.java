package com.example.book_store.dao;

import com.example.book_store.exception.CustomException;
import com.example.book_store.model.Buyer;

import org.bson.types.ObjectId;

import java.util.List;

public interface BuyerDao {

	/**
	 * Returns an object with matching id from buyers collection
	 * @param id id of the needed document
	 * @throws CustomException thrown when the document doesn't exist
	 * @return the requested buyer object
	 */
	Buyer getById(ObjectId id) throws CustomException;

	/**
	 * Adds a buyer to the collection if it doesn't exist
	 * @param buyer Object to be added
	 * @throws CustomException thrown when the buyer already exists
	 */
	void add(Buyer buyer) throws CustomException;

	/**
	 * Delete a buyer from collection if it exists
	 * @param id id of the buyer to be deleted
	 * @throws CustomException thrown when the buyer doesn't exist
	 */
	void delete(ObjectId id) throws CustomException;

	/**
	 * Get all documents from the buyer collection
	 * @return list of type Buyer
	 */
	List<Buyer> getBuyers();

	/**
	 * Update buyer with the same id only if it exists
	 * @param buyer buyer object with the new data
	 * @return updated buyer object
	 * @throws CustomException thrown when the buyer to be added doesn't exist
	 */
	Buyer update(Buyer buyer) throws CustomException;

	/**
	 * Adds books' id to the books' field of the buyer
	 * @param bookId book id to be added
	 * @param buyerId buyer id to be affected
	 * @throws CustomException when no buyer with the given id exists
	 */
	void addToBooks(List<ObjectId> bookId, ObjectId buyerId) throws CustomException;

	/**
	 * Checks if a document with the given id exists
	 * @param id buyer id
	 * @return boolean true if document is present
	 */
	boolean exists(ObjectId id);
}
