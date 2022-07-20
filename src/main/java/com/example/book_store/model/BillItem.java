package com.example.book_store.model;


import com.example.book_store.serialize.ObjectIdDeserializer;
import com.example.book_store.serialize.ObjectIdSerializer;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import org.bson.types.ObjectId;

public class BillItem {
	@JsonSerialize(using = ObjectIdSerializer.class)
	@JsonDeserialize(using = ObjectIdDeserializer.class)
	private ObjectId bookId;
	private String bookName;
	private int quantity;
	private double price;

	public String getBookName() {
		return bookName;
	}

	public void setBookName(String bookName) {
		this.bookName = bookName;
	}

	public ObjectId getBookId() {
		return bookId;
	}

	public void setBookId(ObjectId bookId) {
		this.bookId = bookId;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}
}
