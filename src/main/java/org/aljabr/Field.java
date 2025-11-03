package org.aljabr;

import java.util.Map;

import com.fasterxml.jackson.databind.JsonNode;

public class Field
{
	final String name;
	final String type;
	final Map<String, JsonNode> attributes;
	
	public Field(String name, String type, Map<String, JsonNode> attributes)
	{
		this.name = name;
		this.type = type;
		this.attributes = attributes;
	}
	
	public Field(String name, JsonNode value)
	{
		this.name = name;
		this.type = value.get("type").asText();
		this.attributes = value.deepCopy();
		this.attributes.remove("type");
	}

	public String getName()
	{
		return name;
	}
	
	public String getType()
	{
		return type;
	}

	public Map<String, JsonNode> getAttributes()
	{
		return attributes;
	}

}
