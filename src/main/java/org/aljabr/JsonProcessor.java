package org.aljabr;

import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;

public class JsonProcessor
{
	public static enum Operation 
	{
		UNION
		{
			@Override
	        public Set<String> combine(Set<String> target, Set<String> source) 
			{
	            Set<String> result = new HashSet<>(target);
	            result.addAll(source);
	            return result;
	        }
	    },
		
	    INTERSECTION 
	    {
	        @Override
	        public Set<String> combine(Set<String> target, Set<String> source) 
	        {
	            Set<String> result = new HashSet<>(target);
	            result.retainAll(source);
	            return result;
	        }
	    },
	    
	    SUBTRACTION 
	    {
	        @Override
	        public Set<String> combine(Set<String> target, Set<String> source) {
	            Set<String> result = new HashSet<>(target);
	            result.removeAll(source);
	            return result;
	        }
	    },
		XOR 
		{
            @Override
            public Set<String> combine(Set<String> target, Set<String> source) {
                Set<String> result = new HashSet<>(target);
                for (String item : source) {
                    if (!result.add(item)) {
                        result.remove(item);
                    }
                }
                return result;
            }
        };
        
		public abstract Set<String> combine(Set<String> target, Set<String> source);
	}
	
	private Set<String> getFieldNames(JsonNode node) 
	{
		Set<String> names = new HashSet<>();
		node.fieldNames().forEachRemaining(names::add);
		return names;
	}
	
	public JsonNode processLevel(JsonNode target, JsonNode source, Operation operation) throws InvalidArgumentException
	{
		// Get fields list of target object
		Set<String> targetFields = getFieldNames(target);
		Set<String> sourceFields = getFieldNames(source);
		Set<String> resultFields = operation.combine(targetFields, sourceFields);
		
		JsonNode result = new ObjectNode(JsonNodeFactory.instance);
		for (String field : resultFields)
		{
			JsonNode targetValue = target.get(field);
			JsonNode sourceValue = source.get(field);

			if (targetValue != null && sourceValue != null)
			{
				if (targetValue.isObject() && sourceValue.isObject())
				{
					((ObjectNode) result).set(field, processLevel(targetValue, sourceValue, operation));
				} else
				{
					((ObjectNode) result).set(field, targetValue);
				}
			} else if (targetValue != null)
			{
				((ObjectNode) result).set(field, targetValue);
			} else if (sourceValue != null)
			{
				((ObjectNode) result).set(field, sourceValue);
			}
		}
		return result;
	}
}

