package com.example.book_store.service;

import com.example.book_store.exception.CustomException;
import com.example.book_store.model.Order;

import org.bson.types.ObjectId;

import java.util.List;

public interface OrderService {
	/**
	 * Delete existing order by id
	 * @param id id of the order to be deleted
	 * @throws CustomException when the object doesn't exist already
	 */
	void deleteById(ObjectId id) throws CustomException;

	List<Order> getOrders();
}
