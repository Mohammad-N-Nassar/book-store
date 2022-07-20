package com.example.book_store.dao;

import com.example.book_store.exception.CustomException;
import com.example.book_store.model.Order;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;

import dev.morphia.Morphia;
import dev.morphia.dao.BasicDAO;
import dev.morphia.query.Query;

import org.bson.types.ObjectId;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.NoSuchElementException;

@Repository
public class OrderDaoImpl extends BasicDAO<Order, ObjectId> implements OrderDao {

	public OrderDaoImpl() {
		super(new MongoClient(new MongoClientURI("mongodb+srv://book-store:book-store@cluster0.zwqhidc.mongodb.net/?retryWrites=true&w=majority")),new Morphia(),"bookstore");
	}

	public OrderDaoImpl(MongoClient mongoClient, Morphia morphia, String dbName) {
		super(mongoClient, morphia, dbName);
	}

	/**
	 * Returns an object with matching id from orders collection
	 * @param id id of the needed document
	 * @throws CustomException thrown when the document doesn't exist
	 * @return the requested Order object
	 */
	@Override
	public Order getById(ObjectId id) throws CustomException {
		try{
			Order order = createQuery().filter("_id",id).find().next();
			return order;
		}catch(NoSuchElementException e){
			throw new CustomException("Order with id " + id + " not found!", HttpStatus.NOT_FOUND);
		}
	}

	/**
	 * Adds an order to the database if it doesn't exist
	 * @param order Object to be added
	 * @throws CustomException thrown when the order already exists
	 */
	@Override
	public void add(Order order) throws CustomException {
		if (!exists("_id", order.getOrderId())) {
			save(order);
		} else {
			throw new CustomException("Order with id " + order.getOrderId() + " already exists!", HttpStatus.BAD_REQUEST);
		}
	}

	/**
	 * Delete an order from the collection if it exists
	 * @param id id of the order to be deleted
	 * @throws CustomException thrown when the author doesn't exist
	 */
	@Override
	public void delete(ObjectId id) throws CustomException {
		try {
			Query<Order> query = createQuery();
			query.filter("_id", id).find().next();
			deleteByQuery(query);
		} catch (NoSuchElementException e) {
			throw new CustomException("Order with id " + id + " not found!", HttpStatus.NOT_FOUND);
		}
	}

	/**
	 * Get a list of all the documents from the order collection
	 * @return List of type Order
	 */
	@Override
	public List<Order> getOrders() {
		return createQuery().find().toList();
	}

	/**
	 * Update order with the same id only if it exists
	 * @param order order object with the new data
	 * @return updated order object
	 * @throws CustomException when the needed object doesn't already exist
	 */
	@Override
	public Order update(Order order) throws CustomException {
		if (exists(order.getOrderId())) {
			save(order);
			return order;
		} else {
			throw new CustomException("Order with id " + order.getOrderId() + " doesn't exist!", HttpStatus.NOT_FOUND);
		}
	}

	/**
	 * Checks if a document with the given id exists
	 * @param id order id
	 * @return boolean true if document is present
	 */
	@Override
	public boolean exists(ObjectId id) {
		return exists("_id", id);
	}
}
