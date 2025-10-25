package org.aljabr;

import java.util.Map;

public class Field<T>
{
	final String name;
	final String type;
	final Map<String, T> attributes;
	
	public Field(String name, String type, Map<String, T> attributes)
	{
		this.name = name;
		this.type = type;
		this.attributes = attributes;
	}
	
	public String getName()
	{
		return name;
	}
	
	public String getType()
	{
		return type;
	}

	public Map<String, T> getAttributes()
	{
		return attributes;
	}

}
