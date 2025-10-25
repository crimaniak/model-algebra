package org.aljabr;

import java.util.Map;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;

public class Model implements IModel
{
	protected JsonNode root;
	
	private Map<String, JsonNode> metadata = null;
	private Map<String, JsonNode> fields = null;

	public Model(JsonNode node) throws JsonProcessingException
	{
		this.root = node;
		this.fields = JsonUtils.node2map(root.get("fields"));
		this.metadata = JsonUtils.node2map(root.get("metadata"));
	}

	public Model(String json)
	{
		try {
			root = JsonUtils.string2node(json);
		} catch (JsonProcessingException e) {
			throw new IllegalArgumentException("Invalid JSON input", e);
		}
	}

	public static Model from(String json)
	{
		return new Model(json);
	}

	public static Model from(JsonNode json) throws JsonProcessingException
	{
		return new Model(json);
	}
	
	public static Model from(IModel source)
	{
		return new Model(source.toJson());
	}
	

	@Override
	public String toJson()
	{
		try
		{
			return JsonUtils.node2string(root);
		} catch (JsonProcessingException e)
		{
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public IModel add(IModel m)
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public IModel sub(IModel m)
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public IModel overrideFrom(IModel m)
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public IModel enrichFrom(IModel m)
	{
		// TODO Auto-generated method stub
		return null;
	}

}
