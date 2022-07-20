package com.example.book_store.dao;

import com.example.book_store.exception.CustomException;
import com.example.book_store.model.Author;

import org.bson.types.ObjectId;

import java.util.List;

public interface AuthorDao {

	Author getById(ObjectId id) throws CustomException;

	void add(Author author) throws CustomException;

	void delete(ObjectId id) throws CustomException;

	List<Author> getAuthors();

	Author update(Author author) throws CustomException;

	boolean exists(ObjectId id);
}
