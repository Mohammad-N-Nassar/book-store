package com.example.book_store.unit.service;


import com.example.book_store.dao.AuthorDao;
import com.example.book_store.exception.CustomException;
import com.example.book_store.model.Author;
import com.example.book_store.service.AuthorServiceImpl;

import org.bson.types.ObjectId;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;

import static org.mockito.Mockito.*;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class AuthorServiceImplUnitTest {


	@Mock
	AuthorDao authorDao;
	AuthorServiceImpl authorService;
	static Author author;

	@BeforeEach
	void setUp() {
		authorService = new AuthorServiceImpl();
		this.authorService.authorDao = authorDao;

		author = new Author();
		author.setId(new ObjectId("62b9c462fcb4e65a98b68afd"));
		author.setName("author to test JUnit1");
		author.setNationality("KSA");
		author.setEmail("authorjunit@email.com");
		author.setAge(45);
		author.setPhone_number("012345898");
	}

	@Test
	void add_IdAlreadyExist_ThrowsCustomException() throws CustomException {
		doThrow(CustomException.class).when(authorDao).add(any(Author.class));
		Assertions.assertThrows(CustomException.class, () -> authorService.add(author));
		verify(authorDao).add(author);
	}

	@Test
	void getById_IdNotExist_ThrowsCustomException() throws CustomException {
		doThrow(CustomException.class).when(authorDao).getById(any());
		Assertions.assertThrows(CustomException.class, () -> authorService.getById(new ObjectId("62b9c462fcb4e65a98b68afd")));
		verify(authorDao).getById(new ObjectId("62b9c462fcb4e65a98b68afd"));
	}

	@Test
	void deleteById_IdNotExist_ThrowsCustomException() throws CustomException {
		doThrow(CustomException.class).when(authorDao).delete(any());
		Assertions.assertThrows(CustomException.class , () -> authorService.deleteById(new ObjectId("62b9c462fcb4e65a98b68afd")));
		verify(authorDao).delete(new ObjectId("62b9c462fcb4e65a98b68afd"));
	}

	@Test
	void getAuthors_GetListOfTypeAuthor() {
		when(authorDao.getAuthors()).thenReturn(new ArrayList<>());
		Assertions.assertEquals(ArrayList.class, authorService.getAuthors().getClass());
	}

	@Test
	void update_IdNotExist_ThrowsCustomException() throws CustomException {
		doThrow(CustomException.class).when(authorDao).update(any(Author.class));
		Assertions.assertThrows(CustomException.class, () -> authorService.update(author));
		verify(authorDao).update(author);
	}
}
