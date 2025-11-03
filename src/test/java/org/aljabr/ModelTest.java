package org.aljabr;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;

import org.junit.jupiter.api.Test;

import com.fasterxml.jackson.databind.node.ObjectNode;

public class ModelTest
{
	public static final String TEST_NAME = "SmokeTest";
	public static String user_core_string;
	public static String user_extended_string;
	public static String user_db_string;
	
	public ModelTest() throws IOException
	{
		user_core_string = ResourceLoader.loadResourceAsString(ResourceUrl.user_core_model);
		user_extended_string = ResourceLoader.loadResourceAsString(ResourceUrl.user_extended_model);
		user_db_string = ResourceLoader.loadResourceAsString(ResourceUrl.user_db_model);
	}
	
	
	@Test
	public void smokeTest() throws IOException, InvalidArgumentException
	{
		// Load string from resource file src/test/resources/json_to_read.json
		String json = ResourceLoader.loadResourceAsString(ResourceUrl.user_core_model);
		assertNotNull(json);
		assertTrue(json.length() > 0);
		
		// Get source Json as JsonNode
		var sourceJson = JsonUtils.string2node(json);
		
		// Get Json from model as JsonNode
		Model model = new Model(json);
		var targetJson = JsonUtils.string2node(model.toJson());
		
		// Ensure both JsonNodes are equal
		assertEquals(sourceJson, targetJson);
		
		// Add to JsonNode one more property with name 'test' and value 'testString'
		if (targetJson instanceof ObjectNode) {
            ObjectNode objectNode = (ObjectNode) targetJson;
            objectNode.put("test", "testString");
        }
		
		// Ensure both JsonNodes are not equal
		assertNotEquals(sourceJson, targetJson);
	}
	
	@Test
	public void testFromIModel() throws InvalidArgumentException
    {
        IModel coreModel = Model.from(user_core_string);
        IModel modelFromIModel = Model.from(coreModel);
        assertEquals(coreModel.toJson(), modelFromIModel.toJson());
    }
	
	@Test
	public void testFromJsonNode() throws InvalidArgumentException
	{
		IModel coreModel = Model.from(user_core_string);
		var coreJsonNode = JsonUtils.string2node(user_core_string);
		IModel modelFromJsonNode = Model.from(coreJsonNode);
		assertEquals(coreModel.toJson(), modelFromJsonNode.toJson());
	}
	
	@Test
	public void testFromInvalidJson()
	{
		String invalidJson = "{ invalid json }";
		assertThrows(InvalidArgumentException.class, () ->
		{
			Model.from(invalidJson);
		});
	}
	
	@Test
	public void testToJson() throws InvalidArgumentException
	{
		IModel coreModel = Model.from(user_core_string);
		String json = coreModel.toJson();
		assertEquals(user_core_string.replaceAll("\\s+", ""), json.replaceAll("\\s+", ""));
	}
	
	
	// not ready yet @Test
	public void testAdd() throws InvalidArgumentException
	{
		IModel sum = Model.from(user_core_string).add(Model.from(user_extended_string));
		sum.fieldsAsStream().map(field -> field.getName()).forEach(name -> {
            System.out.println("Field: " + name);
        });
	}
	
}
