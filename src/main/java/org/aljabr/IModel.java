package org.aljabr;

import java.util.Map;
import java.util.stream.Stream;

import com.fasterxml.jackson.databind.JsonNode;

public interface IModel {

	String toString();
	String toJson() throws InvalidArgumentException;
	
	IModel add(IModel m);

	IModel sub(IModel m);

	IModel overrideFrom(IModel m);

	IModel enrichFrom(IModel m);

	Stream<Field> fieldsAsStream();
	Map<String, Field> fieldsAsMap();
	Map<String, JsonNode> getMetadata();

}