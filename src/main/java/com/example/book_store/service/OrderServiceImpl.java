package com.example.book_store.service;

import com.example.book_store.dao.OrderDao;

import com.example.book_store.exception.CustomException;

import org.bson.types.ObjectId;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
