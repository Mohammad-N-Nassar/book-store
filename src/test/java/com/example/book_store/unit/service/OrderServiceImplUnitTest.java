package com.example.book_store.unit.service;

import com.example.book_store.dao.OrderDao;
import com.example.book_store.exception.CustomException;
import com.example.book_store.service.OrderServiceImpl;

import org.bson.types.ObjectId;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import org.springframework.boot.test.context.SpringBootTest;

import static org.mockito.Mockito.*;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class OrderServiceImplUnitTest {


	@Mock
	OrderDao orderDao;
	OrderServiceImpl orderService;

	@BeforeEach
	void setUp() {
		orderService = new OrderServiceImpl();
		this.orderService.orderDao = orderDao;
	}

	@Test
	void deleteById_IdNotExist_ThrowsCustomException() throws CustomException {
		doThrow(CustomException.class).when(orderDao).delete(any(ObjectId.class));
		Assertions.assertThrows(CustomException.class, () -> orderService.deleteById(new ObjectId("62b332737a61234562e9509c")));
		verify(orderDao).delete(new ObjectId("62b332737a61234562e9509c"));
	}
}
