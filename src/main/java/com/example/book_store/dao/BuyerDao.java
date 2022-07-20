package com.example.book_store.dao;

import com.example.book_store.exception.CustomException;
import com.example.book_store.model.Buyer;

import org.bson.types.ObjectId;

import java.util.List;

public interface BuyerDao {

	Buyer getById(ObjectId id) throws CustomException;

	void add(Buyer buyer) throws CustomException;

	void delete(ObjectId id) throws CustomException;

	List<Buyer> getBuyers();

	Buyer update(Buyer buyer) throws CustomException;

	void addToBooks(List<ObjectId> bookId, ObjectId buyerId) throws CustomException;

	boolean exists(ObjectId id);
}
