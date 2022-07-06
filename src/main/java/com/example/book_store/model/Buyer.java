package com.example.book_store.model;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import dev.morphia.annotations.Entity;
import dev.morphia.annotations.Id;
import org.bson.types.ObjectId;

import java.util.List;

@Entity("buyer")
public class Buyer {

	@Id
	@JsonSerialize(using= ToStringSerializer.class)
	private ObjectId id;
	private String name;
	private String phone_number;
	@JsonSerialize(using= ToStringSerializer.class)
	List<ObjectId> books;
	private String email;
	private int age;

	public List<ObjectId> getBooks() {
		return books;
	}

	public void setBooks(List<ObjectId> books) {
		this.books = books;
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

	public String getPhone_number() {
		return phone_number;
	}

	public void setPhone_number(String phone_number) {
		this.phone_number = phone_number;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	@Override
	public String toString() {
		return "Buyer{" +
				"id=" + id +
				", name='" + name + '\'' +
				", phone_number='" + phone_number + '\'' +
				", books=" + books +
				", email=" + email +
				", age='" + age + '\'' +
				'}';
	}
}
