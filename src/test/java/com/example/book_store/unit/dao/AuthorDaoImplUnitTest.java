package com.example.book_store.unit.dao;

import com.example.book_store.dao.AuthorDaoImpl;
import com.example.book_store.exception.CustomException;
import com.example.book_store.model.Author;
import com.example.book_store.unit.dao.config.EmbeddedMongoDB;

import com.mongodb.MongoClient;

import dev.morphia.Morphia;

import org.bson.types.ObjectId;

import org.junit.jupiter.api.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;

@SpringBootTest
public class AuthorDaoImplUnitTest {

	@Autowired
	static AuthorDaoImpl authorDao;

	static Author author;
	static EmbeddedMongoDB embeddedMongoDB;
	@BeforeAll
	static void setUp() throws IOException {
		embeddedMongoDB = new EmbeddedMongoDB();
		MongoClient mongoClient = embeddedMongoDB.getClient();

		authorDao = new AuthorDaoImpl(
				mongoClient,
				new Morphia(),
				"mydb"
		);
		embeddedMongoDB.start();

		author= new Author();
		author.setAge(40);
		author.setEmail("author@email.com");
		author.setId(new ObjectId("62bad031b4cd4b1326879c0e"));
		author.setName("author unit");
		author.setNationality("JOR");
		author.setPhone_number("0123456798");
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
		author.setId(new ObjectId());
		authorDao.add(author);
		Assertions.assertThrows(CustomException.class, () -> authorDao.add(author));
	}

	@Test
	void getById_IdNotExist_ThrowsCustomException() {
		Assertions.assertThrows(CustomException.class, () -> authorDao.getById(new ObjectId()));
	}

	@Test
	void getById_IdExist_ReturnsNeededObject() throws CustomException {
		ObjectId id = new ObjectId();
		author.setId(id);
		authorDao.add(author);
		Assertions.assertEquals(author.getName(), authorDao.getById(id).getName());
	}

	@Test
	void delete_IdExist() throws CustomException{
		ObjectId id = new ObjectId();
		author.setId(id);
		authorDao.add(author);
		authorDao.delete(author);
		Assertions.assertFalse(authorDao.exists(author.getId()));
	}

	@Test
	void delete_IdNotExist() {
		Assertions.assertThrows(CustomException.class, () -> authorDao.delete(new ObjectId()));
	}

	@Test
	void add_AddedSuccessfully() throws CustomException{
		ObjectId id = new ObjectId();
		author.setId(id);
		authorDao.add(author);
		Assertions.assertEquals(authorDao.getById(id).getId(), id);
	}

	@Test
	void add_AlreadyExist_ThrowsCustomException() throws CustomException {
		author.setId(new ObjectId());
		authorDao.add(author);
		Assertions.assertThrows(CustomException.class, () -> authorDao.add(author));
	}

	@Test
	void update_ObjectNotExist() {
		ObjectId id = new ObjectId();
		author.setId(id);
		Assertions.assertThrows(CustomException.class, () -> authorDao.update(author));
	}

	@Test
	void update_ReturnsUpdatedObjectSuccessfully() throws CustomException {
		author.setId(new ObjectId());
		authorDao.add(author);
		author.setName("new name");
		authorDao.update(author);
		Assertions.assertEquals("new name", authorDao.get(author.getId()).getName());
	}

	@Test
	void getAuthors_ReturnListOfAuthors() throws CustomException {
		authorDao.add(author);
		author.setId(new ObjectId());
		authorDao.add(author);
		author.setId(new ObjectId());
		authorDao.add(author);
		Assertions.assertEquals(3, authorDao.getAuthors().size());
	}
}
