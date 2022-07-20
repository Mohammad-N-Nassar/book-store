package com.example.book_store.dao;

import com.example.book_store.exception.CustomException;
import com.example.book_store.model.Order;

import org.bson.types.ObjectId;

import java.util.List;

public interface OrderDao {

	Order getById(ObjectId id) throws CustomException;

	void add(Order order) throws CustomException;

	void delete(ObjectId id) throws CustomException;

	List<Order> getOrders();

	Order update(Order order) throws CustomException;

	boolean exists(ObjectId id);
}
