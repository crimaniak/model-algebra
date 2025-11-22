package org.aljabr;

import static org.junit.jupiter.api.Assertions.assertEquals;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.TextNode;

import java.util.Map;

import org.junit.jupiter.api.Test;

class FieldTest
{
	
	private static final com.fasterxml.jackson.databind.ObjectMapper mapper = new com.fasterxml.jackson.databind.ObjectMapper();
	
	@Test
    void testFieldCreation()
    {
        Map<String, JsonNode> attributes = Map.of("key1", new TextNode("value1"), "key2", new TextNode("value2"));
        Field field = new Field("testField", "String", attributes);
        assertEquals("testField", field.getName());
        assertEquals("String", field.getType());
        assertEquals(attributes, field.getAttributes());
    }
}
