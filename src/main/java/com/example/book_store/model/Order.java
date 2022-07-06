package com.example.book_store.model;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import dev.morphia.annotations.Entity;
import dev.morphia.annotations.Id;
import org.bson.types.ObjectId;

import java.time.LocalDateTime;

@Entity("order")
public class Order {
	@Id
	@JsonSerialize(using= ToStringSerializer.class)
	private ObjectId orderId;
	@JsonSerialize(using= ToStringSerializer.class)
	private ObjectId buyerId;
	private LocalDateTime timeStamp;
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

	public LocalDateTime getTimeStamp() {
		return timeStamp;
	}

	public void setTimeStamp(LocalDateTime timeStamp) {
		this.timeStamp = timeStamp;
	}

	public BillDto getBillInfo() {
		return billInfo;
	}

	public void setBillInfo(BillDto billInfo) {
		this.billInfo = billInfo;
	}
}
