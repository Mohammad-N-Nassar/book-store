package com.example.book_store.service;

import com.example.book_store.dao.OrderDao;

import com.example.book_store.exception.CustomException;

import com.example.book_store.model.Order;
import org.bson.types.ObjectId;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

	@Autowired
	public OrderDao orderDao;

	/**
	 * Delete existing order by id
	 * @param id id of the order to be deleted
	 * @throws CustomException when the object doesn't exist already
	 */
	@Override
	public void deleteById(ObjectId id) throws CustomException {
		orderDao.delete(id);
	}

	/**
	 * Get a list of all the orders objects
	 * @return List of type Order
	 */
	@Override
	public List<Order> getOrders() {
		return orderDao.getOrders();
	}
}
