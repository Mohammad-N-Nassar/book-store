package com.example.book_store.unit.service;

import com.example.book_store.dao.BookDao;
import com.example.book_store.exception.CustomException;
import com.example.book_store.model.Book;
import com.example.book_store.service.BookServiceImpl;

import org.bson.types.ObjectId;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.ArrayList;

import static org.mockito.Mockito.*;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class BookServiceImplUnitTest {


	@Mock
	BookDao bookDao;
	BookServiceImpl bookService;
	static Book book;

	@BeforeEach
	void setUp() {
		bookService = new BookServiceImpl();
		this.bookService.bookDao = bookDao;

		book = new Book();
		book.setId(new ObjectId("62b9c462fcb4e65a98b68afd"));
		book.setAuthor(new ObjectId("62b318f5875e6945ac71d3a4"));
		book.setName("book to test JUnit1");
		book.setCategory("Suspense");
		book.setPrice(1.23);
		book.setPublish_date(LocalDate.now());
		book.setQuantity(22);
		book.setSold(0);
	}

	@Test
	void add_ReceiveNewBookObject_DoNotThrow() throws CustomException {
		Assertions.assertDoesNotThrow(() -> bookService.add(book));
		verify(bookDao).add(book);
	}

	@Test
	void add_AddObjectWithExistingId_ThrowsCustomException() throws CustomException {
		doThrow(CustomException.class).when(bookDao).add(book);
		Assertions.assertThrows(CustomException.class, () -> bookService.add(book));
		verify(bookDao).add(book);
	}

	@Test
	void getBooks_ReturnsListOfTypeBooks() {
		when(bookDao.getBooks()).thenReturn(new ArrayList<>());
		Assertions.assertEquals(bookService.getBooks(), new ArrayList<Book>());
		verify(bookDao).getBooks();
	}

	@Test
	void increaseQuantity_IdNotExist_ThrowsCustomException() throws CustomException {
		doThrow(CustomException.class).when(bookDao).increaseQuantity(any(), anyInt());
		Assertions.assertThrows(CustomException.class, () -> bookService.increaseQuantity(new ObjectId("62b9c462fcb4e65a98b68afd"), 4));
		verify(bookDao).increaseQuantity(new ObjectId("62b9c462fcb4e65a98b68afd"), 4);
	}

	@Test
	void update_IdNotExist_ThrowsException() throws CustomException {
		doThrow(CustomException.class).when(bookDao).update(book);
		Assertions.assertThrows(CustomException.class, () -> bookService.update(book));
		verify(bookDao).update(book);
	}

	@Test
	void deleteById_IdNotExist_ThrowsCustomException() throws CustomException {
		doThrow(CustomException.class).when(bookDao).delete(book.getId());
		Assertions.assertThrows(CustomException.class, () -> bookService.deleteById(book.getId()));
		verify(bookDao).delete(book.getId());
	}

	@Test
	void getById_ExistingId_GetExpectedObject() throws CustomException {
		when(bookDao.getById(new ObjectId("62b9c462fcb4e65a98b68afd"))).thenReturn(book);
		Assertions.assertEquals(bookService.getById(new ObjectId("62b9c462fcb4e65a98b68afd")), book);
		verify(bookDao).getById(new ObjectId("62b9c462fcb4e65a98b68afd"));
	}
}
