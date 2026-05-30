package org.aljabr;

import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import com.fasterxml.jackson.databind.JsonNode;

public class Model implements IModel
{
	protected final JsonNode root;
	
	private final Map<String, JsonNode> metadata;
	private final List<JsonNode> fields;

	public Model(JsonNode node) throws InvalidArgumentException
	{
		this.root = node;
		this.fields = JsonUtils.node2list(root.get("fields"));
		this.metadata = JsonUtils.node2map(root.get("metadata"));
	}

	public Model(String json) throws InvalidArgumentException
	{
		this(JsonUtils.string2node(json));
	}

	public static Model from(String json) throws InvalidArgumentException
	{
		return new Model(json);
	}

	public static Model from(JsonNode json) throws InvalidArgumentException
	{
		return new Model(json);
	}
	
	public static Model from(IModel source) throws InvalidArgumentException
	{
		return new Model(source.toJson());
	}

	@Override
	public String toJson() throws InvalidArgumentException
	{
		return JsonUtils.node2string(root);
	}

	@Override
	public Stream<Field> fieldsAsStream()
    {
		return this.fields.stream().map(entry -> new Field(entry.get("name").asText(), entry));
	}

	@Override
	public Map<String, Field> fieldsAsMap()
    {
		return fieldsAsStream().collect(java.util.stream.Collectors.toMap(Field::getName, field -> field));
    }

	@Override
	public Map<String, JsonNode> getMetadata()
    {
        return this.metadata;
    }
	
	private IModel applyOperation(IModel m, JsonProcessor.Operation operation)
	{
		try
		{
			JsonNode otherRoot = JsonUtils.string2node(m.toJson());
			JsonNode resultNode = new JsonProcessor().processLevel(this.root, otherRoot, operation);
			return new Model(resultNode);
		} catch (InvalidArgumentException e)
		{
			throw new RuntimeException(e);
		}
	}

	@Override
	public IModel add(IModel m)
	{
		return applyOperation(m, JsonProcessor.Operation.UNION);
	}

	@Override
	public IModel sub(IModel m)
	{
		return applyOperation(m, JsonProcessor.Operation.SUBTRACTION);
	}

	@Override
	public IModel intersect(IModel m)
	{
		return applyOperation(m, JsonProcessor.Operation.INTERSECTION);
	}

	@Override
	public IModel xor(IModel m)
	{
		return applyOperation(m, JsonProcessor.Operation.XOR);
	}

}
