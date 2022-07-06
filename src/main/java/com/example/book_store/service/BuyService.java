package com.example.book_store.service;

import com.example.book_store.exception.CustomException;
import com.example.book_store.model.BillDto;

import org.bson.types.ObjectId;

import java.util.List;

public interface BuyService {

	/**
	 * Buyer buys list of books, each will decrease remaining quantity and increase sold copies based on their occurrence.
	 * Then adds the order details to the database.
	 * @param buyerId id of the buyer
	 * @param booksId list of book ids
	 * @return BillDto bill contains info of the order
	 * @throws CustomException when the buyer doesn't exist, or when a book isn't available in required quantities
	 */
	BillDto buyBooks(ObjectId buyerId, List<ObjectId> booksId) throws CustomException;
}
