package com.example.book_store.service;

import com.example.book_store.exception.CustomException;
import com.example.book_store.model.Buyer;

import org.bson.types.ObjectId;

import java.util.List;

public interface BuyerService {

	/**
	 * Get Buyer object by an id
	 * @param id id of the object to get
	 * @return Buyer object
	 * @throws CustomException When there is no object with the given id
	 */
	Buyer getById(ObjectId id) throws CustomException;

	/**
	 * Insert new Buyer to the database
	 * @param buyer Object to be inserted
	 * @throws CustomException When there is an object with same id exists
	 */
	void add(Buyer buyer) throws CustomException;

	/**
	 * Delete a buyer with a specific id
	 * @param id id of the buyer to be deleted
	 * @throws CustomException when there is no buyer with the given id
	 */
	void deleteById(ObjectId id) throws CustomException;

	/**
	 * Retrieve a list of all buyers present
	 * @return List of type Buyer
	 */
	List<Buyer> getBuyers();

	/**
	 * Update a buyer with new data if it already exists
	 * @param buyer object with the new data
	 * @return The updated object
	 * @throws CustomException when the object doesn't already exist
	 */
	Buyer update(Buyer buyer) throws CustomException;
}
