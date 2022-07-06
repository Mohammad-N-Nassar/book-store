package com.example.book_store.model;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import org.bson.types.ObjectId;

import java.util.List;

public class OrderDto {
	@JsonSerialize(using= ToStringSerializer.class)
	private ObjectId buyerId;
	@JsonSerialize(using= ToStringSerializer.class)
	private List<ObjectId> bookId;

	public ObjectId getBuyerId() {
		return buyerId;
	}

	public List<ObjectId> getBookId() {
		return bookId;
	}

	@Override
	public String toString() {
		return "OrderDto{" +
				"buyerID=" + buyerId +
				", bookId=" + bookId +
				'}';
	}
}
