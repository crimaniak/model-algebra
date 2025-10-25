package org.aljabr;

import java.util.Map;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;

abstract public class JsonUtils
{
	protected static ObjectMapper mapperTo = new ObjectMapper();
	protected static ObjectMapper mapperFrom = JsonMapper.builder().build();
	
	private JsonUtils()
	{
		// prevent instantiation
	}

	public static JsonNode string2node(String json) throws JsonMappingException, JsonProcessingException
	{
		return mapperFrom.readTree(json);
	}

	public static String node2string(JsonNode node) throws JsonProcessingException
	{
		return mapperTo.writeValueAsString(node);
	}
	
	public static Map<String, JsonNode> node2map(JsonNode node) throws JsonProcessingException
	{
		return mapperFrom.convertValue(node, Map.class);
	}
}
