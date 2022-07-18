package com.example.book_store.model;

import com.example.book_store.serialize.ObjectIdDeserializer;
import com.example.book_store.serialize.ObjectIdSerializer;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import dev.morphia.annotations.Entity;
import dev.morphia.annotations.Id;

import org.bson.types.ObjectId;

import java.util.Date;

@Entity("order")
public class Order {
	@Id
	@JsonSerialize(using = ObjectIdSerializer.class)
	@JsonDeserialize(using = ObjectIdDeserializer.class)
	private ObjectId orderId;
	@JsonSerialize(using = ObjectIdSerializer.class)
	@JsonDeserialize(using = ObjectIdDeserializer.class)
	private ObjectId buyerId;
	private Date timeStamp;
	private BillDto billInfo;

	public ObjectId getOrderId() {
		return orderId;
	}

	public void setOrderId(ObjectId orderId) {
		this.orderId = orderId;
	}

	public ObjectId getBuyerId() {
		return buyerId;
	}

	public void setBuyerId(ObjectId buyerId) {
		this.buyerId = buyerId;
	}

	public Date getTimeStamp() {
		return timeStamp;
	}

	public void setTimeStamp(Date timeStamp) {
		this.timeStamp = timeStamp;
	}

	public BillDto getBillInfo() {
		return billInfo;
	}

	public void setBillInfo(BillDto billInfo) {
		this.billInfo = billInfo;
	}
}
