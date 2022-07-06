package com.example.book_store.unit.dao;

import com.example.book_store.dao.BookDaoImpl;
import com.example.book_store.exception.CustomException;
import com.example.book_store.model.Book;
import com.example.book_store.unit.dao.config.EmbeddedMongoDB;

import com.mongodb.MongoClient;

import dev.morphia.Morphia;

import org.bson.types.ObjectId;

import org.junit.jupiter.api.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.time.LocalDate;

@SpringBootTest
public class BookDaoImplUnitTest {

	@Autowired
	static BookDaoImpl bookDao;
	static Book book;
	static EmbeddedMongoDB embeddedMongoDB;

	@BeforeAll
	static void setUp() throws IOException {
		embeddedMongoDB = new EmbeddedMongoDB();
		MongoClient mongoClient = embeddedMongoDB.getClient();

		bookDao = new BookDaoImpl(
				mongoClient,
				new Morphia(),
				"mydb"
		);
		embeddedMongoDB.start();

		book = new Book();
		book.setId(new ObjectId("62b330ca7a64a37952e95098"));
		book.setAuthor(new ObjectId("62b318f5875e6945ac71d3a4"));
		book.setName("book to test JUnit");
		book.setCategory("Suspense");
		book.setPrice(1.23);
		book.setPublish_date(LocalDate.now());
		book.setQuantity(22);
		book.setSold(0);
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
		book.setId(new ObjectId());
		bookDao.add(book);
		Assertions.assertThrows(CustomException.class, () -> bookDao.add(book));
	}

	@Test
	void add_AddedSuccessfully() throws CustomException{
		ObjectId id = new ObjectId();
		book.setId(id);
		bookDao.add(book);
		Assertions.assertTrue(bookDao.exists(id));
	}

	@Test
	void getById_IdNotExist_ThrowsCustomException() {
		Assertions.assertThrows(CustomException.class, () -> bookDao.getById(new ObjectId()));
	}

	@Test
	void getById_IdExist_ReturnsNeededObject() throws CustomException {
		ObjectId id = new ObjectId();
		book.setId(id);
		bookDao.add(book);
		Assertions.assertEquals(book.getName(), bookDao.getById(id).getName());
	}

	@Test
	void delete_IdExist() throws CustomException{
		ObjectId id = new ObjectId();
		book.setId(id);
		bookDao.add(book);
		bookDao.delete(book);
		Assertions.assertFalse(bookDao.exists(book.getId()));
	}

	@Test
	void delete_IdNotExist() {
		Assertions.assertThrows(CustomException.class, () -> bookDao.delete(new ObjectId()));
	}

	@Test
	void update_ObjectNotExist() {
		ObjectId newId = new ObjectId();
		book.setId(newId);
		Assertions.assertThrows(CustomException.class, () -> bookDao.update(book));
	}

	@Test
	void update_ReturnsUpdatedObjectSuccessfully() throws CustomException {
		book.setId(new ObjectId());
		bookDao.add(book);
		book.setName("new book name");
		bookDao.update(book);
		Assertions.assertEquals("new book name", bookDao.get(book.getId()).getName());
	}

	@Test
	void getAuthors_ReturnListOfAuthors() throws CustomException {
		bookDao.add(book);
		book.setId(new ObjectId());
		bookDao.add(book);
		book.setId(new ObjectId());
		bookDao.add(book);
		Assertions.assertEquals(3, bookDao.getBooks().size());
	}

}
