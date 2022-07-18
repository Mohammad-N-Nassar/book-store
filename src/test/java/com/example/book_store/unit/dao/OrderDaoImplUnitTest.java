package com.example.book_store.unit.dao;

import com.example.book_store.dao.OrderDaoImpl;
import com.example.book_store.exception.CustomException;
import com.example.book_store.model.BillDto;
import com.example.book_store.model.Order;
import com.example.book_store.unit.dao.config.EmbeddedMongoDB;

import com.mongodb.MongoClient;

import dev.morphia.Morphia;

import org.bson.types.ObjectId;

import org.junit.jupiter.api.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.util.Date;

@SpringBootTest
public class OrderDaoImplUnitTest {

	@Autowired
	static OrderDaoImpl orderDao;

	static Order order;
	static EmbeddedMongoDB embeddedMongoDB;
	@BeforeAll
	static void setUp() throws IOException {
		embeddedMongoDB = new EmbeddedMongoDB();
		MongoClient mongoClient = embeddedMongoDB.getClient();

		orderDao = new OrderDaoImpl(
				mongoClient,
				new Morphia(),
				"mydb"
		);
		embeddedMongoDB.start();

		order= new Order();
		order.setTimeStamp(new Date(System.currentTimeMillis()));
		order.setBillInfo(new BillDto());
		order.setOrderId(new ObjectId("62b48cca7d3c7071cadc569d"));
		order.setBuyerId(new ObjectId("62b332737a62a37952e9509c"));
	}

	@AfterEach
	void drop() {
		embeddedMongoDB.getClient().dropDatabase("mydb");
	}

	@AfterAll
	static void close() {
		embeddedMongoDB.getClient().dropDatabase("mydb");
		embeddedMongoDB.stop();
	}

	@Test
	void add_IdAlreadyExist_ThrowsCustomException() throws CustomException {
		order.setOrderId(new ObjectId());
		orderDao.add(order);
		Assertions.assertThrows(CustomException.class, () -> orderDao.add(order));
	}

	@Test
	void add_AddedSuccessfully() throws CustomException{
		ObjectId newId = new ObjectId();
		order.setOrderId(newId);
		orderDao.add(order);
		Assertions.assertTrue(orderDao.exists(newId));
	}

	@Test
	void getById_IdNotExist_ThrowsCustomException() {
		Assertions.assertThrows(CustomException.class, () -> orderDao.getById(new ObjectId()));
	}

	@Test
	void getById_IdExist_ReturnsNeededObject() throws CustomException {
		ObjectId id = new ObjectId();
		order.setOrderId(id);
		orderDao.add(order);
		Assertions.assertEquals(order.getBuyerId(), orderDao.getById(id).getBuyerId());
	}

	@Test
	void delete_IdExist() throws CustomException{
		ObjectId id = new ObjectId();
		order.setOrderId(id);
		orderDao.add(order);
		orderDao.delete(order);
		Assertions.assertFalse(orderDao.exists(order.getOrderId()));
	}

	@Test
	void delete_IdNotExist_ThrowsCustomException() {
		Assertions.assertThrows(CustomException.class, () -> orderDao.delete(new ObjectId()));
	}

	@Test
	void update_ObjectNotExist_ThrowsCustomException() {
		ObjectId id = new ObjectId();
		order.setOrderId(id);
		Assertions.assertThrows(CustomException.class, () -> orderDao.update(order));
	}

	@Test
	void update_ReturnsUpdatedObjectSuccessfully() throws CustomException {
		order.setOrderId(new ObjectId());
		orderDao.add(order);

		ObjectId newBuyerId = new ObjectId();
		order.setBuyerId(newBuyerId);
		orderDao.update(order);

		Assertions.assertEquals(newBuyerId, orderDao.get(order.getOrderId()).getBuyerId());
	}

	@Test
	void getAuthors_ReturnListOfAuthors() throws CustomException {
		orderDao.add(order);
		order.setOrderId(new ObjectId());
		orderDao.add(order);
		order.setOrderId(new ObjectId());
		orderDao.add(order);
		Assertions.assertEquals(3, orderDao.getOrders().size());
	}
}
