package com.example.book_store.service;

import com.example.book_store.dao.BookDao;
import com.example.book_store.dao.BuyerDao;
import com.example.book_store.dao.OrderDao;
import com.example.book_store.exception.CustomException;
import com.example.book_store.model.BillDto;
import com.example.book_store.model.BillItem;
import com.example.book_store.model.Book;
import com.example.book_store.model.Order;

import org.bson.types.ObjectId;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ShoppingServiceImpl implements ShoppingService {

	@Autowired
	public BuyerDao buyerDao;
	@Autowired
	public BookDao bookDao;
	@Autowired
	public OrderDao orderDao;

	/**
	 * Buyer buys list of books, each remaining quantity will be decreased and increase sold copies based on their occurrence in the list.
	 * Then adds the order details to the database.
	 * @param buyerId id of the buyer
	 * @param booksId list of book ids
	 * @return BillDto bill contains info of the order
	 * @throws CustomException when the buyer doesn't exist, or when a book isn't available in required quantities
	 */
	@Override
	public BillDto buyBooks(ObjectId buyerId, List<ObjectId> booksId) throws CustomException {
		Map<ObjectId, Integer> bookQuantity = new HashMap<>();
		calculateBooksQuantity(booksId, bookQuantity);

		BillDto billDto = null;

		if (bookDao.booksAvailable(bookQuantity)) {
			List<BillItem> billItems = new ArrayList<>();

			double totalPrice=0;

			buyerDao.addToBooks(booksId, buyerId);

			for (Map.Entry<ObjectId, Integer> mapElement : bookQuantity.entrySet()) {
				ObjectId bookId = mapElement.getKey();
				int quantity = mapElement.getValue();

				bookDao.decreaseQuantity(bookId, quantity);
				bookDao.increaseSold(bookId, quantity);

				BillItem billItem = new BillItem();

				Book book = bookDao.getById(bookId);

				billItem.setPrice(book.getPrice());
				billItem.setBookId(bookId);
				billItem.setQuantity(bookQuantity.get(bookId));
				billItem.setBookName(book.getName());

				billItems.add(billItem);

				totalPrice += book.getPrice() * bookQuantity.get(bookId);
			}

			billDto = new BillDto();

			billDto.setBuyerId(buyerId);
			billDto.setItems(billItems);
			billDto.setTotalPrice(totalPrice);

			Order order = new Order();
			order.setOrderId(new ObjectId());
			order.setBuyerId(buyerId);
			order.setBillInfo(billDto);
			order.setTimeStamp(new Date(System.currentTimeMillis()));
			orderDao.add(order);
		}
		return billDto;
	}

	/**
	 * Calculates how many times each book was bought by calculating its occurrence in the list of ids
	 * @param books List of ObjectId contains book ids to buy
	 * @param bookQuantity Map of ObjectId for the ids, Integer for their quantity
	 */
	private void calculateBooksQuantity(List<ObjectId> books, Map<ObjectId, Integer> bookQuantity) {
		for (ObjectId book:books) {
			if (bookQuantity.containsKey(book)) {
				bookQuantity.computeIfPresent(book, (key, val) -> val + 1);
			} else {
				bookQuantity.put(book, 1);
			}
		}
	}

	/**
	 * Delete existing order by id
	 * @param id id of the order to be deleted
	 * @throws CustomException when the object doesn't exist already
	 */
	@Override
	public void deleteById(ObjectId id) throws CustomException {
		orderDao.delete(id);
	}

	/**
	 * Get a list of all the orders objects
	 * @return List of type Order
	 */
	@Override
	public List<Order> getOrders() {
		return orderDao.getOrders();
	}
}
