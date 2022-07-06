package com.example.book_store.dao;

import com.example.book_store.exception.CustomException;
import com.example.book_store.model.Order;

import org.bson.types.ObjectId;

import java.util.List;

public interface OrderDao {

	/**
	 * Returns an object with matching id from orders collection
	 * @param id id of the needed document
	 * @throws CustomException thrown when the document doesn't exist
	 * @return the requested Order object
	 */
	Order getById(ObjectId id) throws CustomException;

	/**
	 * Adds an order to the database if it doesn't exist
	 * @param order Object to be added
	 * @throws CustomException thrown when the order already exists
	 */
	void add(Order order) throws CustomException;

	/**
	 * Delete an order from the collection if it exists
	 * @param id id of the order to be deleted
	 * @throws CustomException thrown when the author doesn't exist
	 */
	void delete(ObjectId id) throws CustomException;

	/**
	 * Get a list of all the documents from the order collection
	 * @return List of type Order
	 */
	List<Order> getOrders();

	/**
	 * Update order with the same id only if it exists
	 * @param order order object with the new data
	 * @return updated order object
	 * @throws CustomException when the needed object doesn't already exist
	 */
	Order update(Order order) throws CustomException;

	boolean exists(ObjectId id);
}
