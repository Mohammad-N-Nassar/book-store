package com.example.book_store.model;

import com.example.book_store.serialize.ObjectIdDeserializer;
import com.example.book_store.serialize.ObjectIdSerializer;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import dev.morphia.annotations.Entity;
import dev.morphia.annotations.Id;

import org.bson.types.ObjectId;

@Entity("author")
public class Author {

	@Id
	@JsonSerialize(using = ObjectIdSerializer.class)
	@JsonDeserialize(using = ObjectIdDeserializer.class)
	private ObjectId id;
	private String name;
	private String phone_number;
	private String email;
	private int age;
	private String nationality;

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

	public String getNationality() {
		return nationality;
	}

	public void setNationality(String nationality) {
		this.nationality = nationality;
	}

	@Override
	public String toString() {
		return "Author{" +
				"id=" + id +
				", name='" + name + '\'' +
				", phone_number='" + phone_number + '\'' +
				", email=" + email +
				", age='" + age + '\'' +
				", nationality='" + nationality + '\'' +
				'}';
	}
}
