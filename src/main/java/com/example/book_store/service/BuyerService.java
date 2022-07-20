package com.example.book_store.service;

import com.example.book_store.exception.CustomException;
import com.example.book_store.model.Buyer;

import org.bson.types.ObjectId;

import java.util.List;

public interface BuyerService {


	Buyer getById(ObjectId id) throws CustomException;

	void add(Buyer buyer) throws CustomException;

	void deleteById(ObjectId id) throws CustomException;

	List<Buyer> getBuyers();

	Buyer update(Buyer buyer) throws CustomException;
}
