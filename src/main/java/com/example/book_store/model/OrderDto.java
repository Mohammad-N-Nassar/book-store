package com.example.book_store.model;

import com.example.book_store.serialize.ObjectIdDeserializer;
import com.example.book_store.serialize.ObjectIdSerializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import org.bson.types.ObjectId;

import java.util.List;

public class OrderDto {
	@JsonSerialize(using = ObjectIdSerializer.class)
	@JsonDeserialize(using = ObjectIdDeserializer.class)
	private ObjectId buyerId;
	@JsonSerialize(using = ObjectIdSerializer.class)
	@JsonDeserialize(using = ObjectIdDeserializer.class)
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
