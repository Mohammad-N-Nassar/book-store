package com.example.book_store.model;

import com.example.book_store.serialize.ObjectIdDeserializer;
import com.example.book_store.serialize.ObjectIdSerializer;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import org.bson.types.ObjectId;

import java.util.List;

public class BillDto {

	@JsonSerialize(using = ObjectIdSerializer.class)
	@JsonDeserialize(using = ObjectIdDeserializer.class)
	private ObjectId buyerId;
	private List<BillItem> items;
	private double totalPrice;

	public ObjectId getBuyerId() {
		return buyerId;
	}

	public double getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(double totalPrice) {
		this.totalPrice = totalPrice;
	}

	public void setBuyerId(ObjectId buyerId) {
		this.buyerId = buyerId;
	}

	public List<BillItem> getItems() {
		return items;
	}

	public void setItems(List<BillItem> items) {
		this.items = items;
	}
}
