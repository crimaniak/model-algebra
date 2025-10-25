package org.aljabr;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Map;

import org.junit.jupiter.api.Test;

public class FieldTest
{
	@Test
    public void testFieldCreation()
    {
        Map<String, String> attributes = Map.of("key1", "value1", "key2", "value2");
        Field<String> field = new Field<>("testField", "String", attributes);
        assertEquals("testField", field.getName());
        assertEquals("String", field.getType());
        assertEquals(attributes, field.getAttributes());
    }
}
