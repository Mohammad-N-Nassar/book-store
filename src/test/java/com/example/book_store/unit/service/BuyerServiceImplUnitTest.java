package com.example.book_store.unit.service;


import com.example.book_store.dao.BuyerDao;
import com.example.book_store.exception.CustomException;
import com.example.book_store.model.Buyer;
import com.example.book_store.service.BuyerServiceImpl;

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
public class BuyerServiceImplUnitTest {


	@Mock
	BuyerDao buyerDao;

	BuyerServiceImpl buyerService;
	static Buyer buyer;

	@BeforeEach
	void setUp() {
		buyerService = new BuyerServiceImpl();
		this.buyerService.buyerDao = buyerDao;

		buyer = new Buyer();
		buyer.setId(new ObjectId("62b332737a62a37952e9509c"));
		buyer.setName("Buyer to test JUnit1");
		buyer.setEmail("buyerJunit@email.com");
		buyer.setAge(45);
		buyer.setPhone_number("012345898");
	}

	@Test
	void add_IdAlreadyExist_ThrowsCustomException() throws CustomException {
		doThrow(CustomException.class).when(buyerDao).add(any());
		Assertions.assertThrows(CustomException.class, () -> buyerService.add(buyer));
		verify(buyerDao).add(buyer);
	}

	@Test
	void getById_IdNotExist_ThrowsCustomException() throws CustomException {
		doThrow(CustomException.class).when(buyerDao).add(any());
		Assertions.assertThrows(CustomException.class, () -> buyerService.add(buyer));
		verify(buyerDao).add(buyer);
	}

	@Test
	void deleteById_IdNotExist_ThrowsCustomException() throws CustomException {
		doThrow(CustomException.class).when(buyerDao).delete(any());
		Assertions.assertThrows(CustomException.class , () -> buyerService.deleteById(buyer.getId()));
		verify(buyerDao).delete(buyer.getId());
	}

	@Test
	void getBuyers_GetListOfTypeBuyer() {
		when(buyerDao.getBuyers()).thenReturn(new ArrayList<>());
		Assertions.assertEquals(ArrayList.class, buyerService.getBuyers().getClass());
	}

	@Test
	void update_IdNotExist_ThrowsCustomException() throws CustomException {
		doThrow(CustomException.class).when(buyerDao).update(any());
		Assertions.assertThrows(CustomException.class, () -> buyerService.update(buyer));
		verify(buyerDao).update(buyer);
	}
}
