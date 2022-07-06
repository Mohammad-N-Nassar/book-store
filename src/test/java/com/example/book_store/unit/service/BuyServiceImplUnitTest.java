package com.example.book_store.unit.service;

import com.example.book_store.dao.BookDao;
import com.example.book_store.dao.BuyerDao;
import com.example.book_store.dao.OrderDao;

import com.example.book_store.exception.CustomException;
import com.example.book_store.model.BillDto;
import com.example.book_store.model.Book;
import com.example.book_store.service.BuyServiceImpl;

import org.bson.types.ObjectId;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class BuyServiceImplUnitTest {

	@Mock
	static BuyerDao buyerDao;
	@Mock
	static BookDao bookDao;
	@Mock
	static OrderDao orderDao;
	static BuyServiceImpl buyService;
	Book book;

	@BeforeEach
	void setUp(){
		buyService = new BuyServiceImpl(buyerDao,bookDao,orderDao);
		book = new Book();
		book.setId(new ObjectId());
		book.setAuthor(new ObjectId("62b318f5875e6945ac71d3a4"));
		book.setName("book to test JUnit1");
		book.setCategory("Suspense");
		book.setPrice(1.23);
		book.setPublish_date(LocalDate.now());
		book.setQuantity(22);
		book.setSold(0);
	}

	@DisplayName("Test happy scenario, with an id and list of ids")
	@Test
	void buyBooksTest() throws CustomException {
		when(bookDao.booksAvailable(any())).thenReturn(true);
		when(bookDao.getById(any())).thenReturn(book);

		List<ObjectId> list = new ArrayList<>();
		list.add(new ObjectId());
		list.add(new ObjectId());
		list.add(new ObjectId());

		Assertions.assertNotNull(buyService.buyBooks(new ObjectId(),list));

		verify(buyerDao).addToBooks(any(),any());
		verify(bookDao, times(list.size())).decreaseQuantity(any(), anyInt());
		verify(bookDao, times(list.size())).increaseSold(any(), anyInt());
	}

	@Test
	void buyBooks_EmptyList_ThrowsException() throws CustomException {
		doThrow(CustomException.class).when(bookDao).booksAvailable(anyMap());
		Assertions.assertThrows(CustomException.class, () -> buyService.buyBooks(new ObjectId(),new ArrayList<>()));
		verify(bookDao).booksAvailable(anyMap());
	}

	@Test
	void buyBooks_NotEnoughQuantity_ThrowsCustomException() throws CustomException {
		doThrow(CustomException.class).when(bookDao).booksAvailable(any());
		Assertions.assertThrows(CustomException.class, () -> buyService.buyBooks(new ObjectId(), new ArrayList<>()));
	}

	@Test
	void buyBooks_BuyerNotExist_ThrowsCustomException() throws CustomException {
		when(bookDao.booksAvailable(any())).thenReturn(true);
		doThrow(CustomException.class).when(buyerDao).addToBooks(anyList(), any());
		Assertions.assertThrows(CustomException.class, () -> buyService.buyBooks(new ObjectId(), new ArrayList<>()));
	}

	@Test
	void buyBooks_OrderIdAlreadyExists_ThrowsException() throws CustomException {
		when(bookDao.booksAvailable(any())).thenReturn(true);
		doThrow(CustomException.class).when(orderDao).add(any());
		Assertions.assertThrows(CustomException.class, () -> buyService.buyBooks(new ObjectId(), new ArrayList<>()));
		verify(orderDao).add(any());
	}

	@Test
	void buyBooks_HappyScenario_BillDtoObjectReturned() throws CustomException {
		when(bookDao.booksAvailable(any())).thenReturn(true);
		Assertions.assertEquals(BillDto.class, buyService.buyBooks(new ObjectId(), new ArrayList<>()).getClass());
	}
}
