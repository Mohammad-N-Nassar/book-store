package com.example.book_store.model;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import dev.morphia.annotations.Entity;
import dev.morphia.annotations.Id;
import org.bson.types.ObjectId;

import java.time.LocalDate;

@Entity("books")
public class Book {

	@Id
	@JsonSerialize(using= ToStringSerializer.class)
	private ObjectId id;
	private String name;
	@JsonSerialize(using= ToStringSerializer.class)
	private ObjectId author;
	private double price;
	private String category;
	private LocalDate publish_date;
	private int quantity;
	private int sold;

	public LocalDate getPublish_date() {
		return publish_date;
	}

	public void setPublish_date(LocalDate publish_date) {
		this.publish_date = publish_date;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public int getSold() {
		return sold;
	}

	public void setSold(int sold) {
		this.sold = sold;
	}

	public ObjectId getId() {
		return id;
	}

	public void setId(ObjectId id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public ObjectId getAuthor() {
		return author;
	}

	public void setAuthor(ObjectId author) {
		this.author = author;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	@Override
	public String toString() {
		return "Book{" +
				"id=" + id +
				", name='" + name + '\'' +
				", author='" + author + '\'' +
				", price=" + price +
				", category='" + category + '\'' +
				", publish_date=" + publish_date +
				", quantity=" + quantity +
				", sold=" + sold +
				'}';
	}
}
