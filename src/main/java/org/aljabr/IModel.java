package org.aljabr;

import java.util.Map;
import java.util.stream.Stream;

import com.fasterxml.jackson.databind.JsonNode;

public interface IModel {

	String toString();
	String toJson() throws InvalidArgumentException;
	
	IModel add(IModel m);

	IModel sub(IModel m);

	IModel intersect(IModel m);

	IModel xor(IModel m);

	Stream<Field> fieldsAsStream();
	Map<String, Field> fieldsAsMap();
	Map<String, JsonNode> getMetadata();

}