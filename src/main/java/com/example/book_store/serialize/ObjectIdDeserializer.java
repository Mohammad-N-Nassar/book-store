package com.example.book_store.serialize;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

import org.bson.types.ObjectId;

import java.io.IOException;

public class ObjectIdDeserializer extends JsonDeserializer<ObjectId> {

	@Override
	public ObjectId deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
		return new ObjectId(p.getValueAsString());
	}
}
