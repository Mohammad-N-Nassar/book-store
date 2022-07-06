package com.example.book_store.unit.dao;

import com.example.book_store.dao.BuyerDaoImpl;
import com.example.book_store.exception.CustomException;
import com.example.book_store.model.Buyer;

import com.example.book_store.unit.dao.config.EmbeddedMongoDB;

import com.mongodb.MongoClient;
import dev.morphia.Morphia;

import org.bson.types.ObjectId;

import org.junit.jupiter.api.*;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import java.io.IOException;
import java.util.ArrayList;

@EnableConfigurationProperties
@DataMongoTest
@EnableMongoRepositories(basePackageClasses = {BuyerDaoImpl.class})
public class BuyerDaoImplUnitTest {

	static BuyerDaoImpl buyerDao;
	static Buyer buyer;

	static EmbeddedMongoDB embeddedMongoDB;

	@BeforeAll
	static void setUp() throws IOException {
		embeddedMongoDB = new EmbeddedMongoDB();
		MongoClient mongoClient = embeddedMongoDB.getClient();

		buyerDao = new BuyerDaoImpl(
				mongoClient,
				new Morphia(),
				"mydb"
		);

		buyer = new Buyer();
		buyer.setAge(12);
		buyer.setEmail("buyer22@email.com");
		buyer.setId(new ObjectId("62b332123456789052e98766"));
		buyer.setPhone_number("013268977");
		buyer.setBooks(new ArrayList<>());
		embeddedMongoDB.start();
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
	void add_NewIdAdded_ObjectExists() throws CustomException {
		buyer.setId(new ObjectId());
		buyerDao.add(buyer);
		Assertions.assertTrue(buyerDao.exists(buyer.getId()));
	}

	@Test
	void add_IdAlreadyExist_ExceptionThrown() throws CustomException {
		buyer.setId(new ObjectId());
		buyerDao.add(buyer);
		Assertions.assertThrows(CustomException.class, () -> buyerDao.add(buyer));
	}

	@Test
	void getBuyId_IdNotExist_ThrowsCustomException() {
		Assertions.assertThrows(CustomException.class, () -> buyerDao.getById(new ObjectId()));
	}

	@Test
	void delete_IdNotExist_ThrowsCustomException() {
		Assertions.assertThrows(CustomException.class, () -> buyerDao.delete(new ObjectId()));
	}

	@Test
	void getBuyers_getListOfTypeBuyer() throws CustomException {
		buyerDao.add(buyer);
		buyer.setId(new ObjectId("62b332123456789052e98765"));
		buyerDao.add(buyer);
		buyer.setId(new ObjectId("62b332123456789052e98764"));
		buyerDao.add(buyer);
		Assertions.assertEquals(buyerDao.getBuyers().size(), 3);
	}

	@Test
	void update_ObjectNotExist_ThrowsCustomException() {
		ObjectId id = new ObjectId();
		buyer.setId(id);
		Assertions.assertThrows(CustomException.class, () -> buyerDao.update(buyer));
	}

	@Test
	void update_ReturnsUpdatedObjectSuccessfully() throws CustomException {
		buyer.setId(new ObjectId());
		buyerDao.add(buyer);

		String newBuyerName = "new buyer name";
		buyer.setName(newBuyerName);
		buyerDao.update(buyer);

		Assertions.assertEquals(newBuyerName, buyerDao.get(buyer.getId()).getName());
	}

	@Test
	void addToBooks_IdNotExist_ThrowsCustomException() {
		Assertions.assertThrows(CustomException.class, () -> buyerDao.addToBooks(new ArrayList<>(), new ObjectId()));
	}
}
