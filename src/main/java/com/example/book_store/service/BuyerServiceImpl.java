package com.example.book_store.service;

import com.example.book_store.dao.BuyerDao;
import com.example.book_store.exception.CustomException;
import com.example.book_store.model.Buyer;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BuyerServiceImpl implements BuyerService {

	@Autowired
	public BuyerDao buyerDao;

	/**
	 * Get Buyer object by an id
	 * @param id id of the object to get
	 * @return Buyer object
	 * @throws CustomException When there is no object with the given id
	 */
	@Override
	public Buyer getById(ObjectId id) throws CustomException {
		return buyerDao.getById(id);
	}

	/**
	 * Insert new Buyer to the database
	 * @param buyer Object to be inserted
	 * @throws CustomException When there is an object with same id exists
	 */
	@Override
	public void add(Buyer buyer) throws CustomException {
		buyerDao.add(buyer);
	}

	/**
	 * Delete a buyer with a specific id
	 * @param id id of the buyer to be deleted
	 * @throws CustomException when there is no buyer with the given id
	 */
	@Override
	public void deleteById(ObjectId id) throws CustomException {
		buyerDao.delete(id);
	}

	/**
	 * Retrieve a list of all buyers present
	 * @return List of type Buyer
	 */
	@Override
	public List<Buyer> getBuyers() {
		return buyerDao.getBuyers();
	}

	/**
	 * Update a buyer with new data if it already exists
	 * @param buyer object with the new data
	 * @return The updated object
	 * @throws CustomException when the object doesn't already exist
	 */
	@Override
	public Buyer update(Buyer buyer) throws CustomException {
		return buyerDao.update(buyer);
	}
}
