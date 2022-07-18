package com.example.book_store.service;

import com.example.book_store.exception.CustomException;
import com.example.book_store.model.BillDto;
import com.example.book_store.model.Order;
import org.bson.types.ObjectId;

import java.util.List;

public interface ShoppingService {
	void deleteById(ObjectId id) throws CustomException;

	List<Order> getOrders();

	BillDto buyBooks(ObjectId buyerId, List<ObjectId> booksId) throws CustomException;
}
