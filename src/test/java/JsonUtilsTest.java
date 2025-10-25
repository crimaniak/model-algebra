import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.aljabr.JsonUtils;
import org.junit.jupiter.api.Test;

import com.fasterxml.jackson.databind.JsonNode;

public class JsonUtilsTest
{
	@Test
	public void testStringToNodeAndBack() throws Exception
	{
		String jsonString = "{\"name\":\"John\",\"age\":30,\"city\":\"New York\"}";

		// Convert string to JsonNode
		JsonNode node = JsonUtils.string2node(jsonString);
		assertNotNull(node);
		assertEquals("John", node.get("name").asText());
		assertEquals(30, node.get("age").asInt());
		assertEquals("New York", node.get("city").asText());

		// Convert JsonNode back to string
		String convertedString = JsonUtils.node2string(node);
		assertNotNull(convertedString);

		// Convert back to JsonNode to compare
		JsonNode convertedNode = JsonUtils.string2node(convertedString);
		assertEquals(node, convertedNode);
	}
	@Test
	public void testInvalidJsonString()
	{
		String invalidJsonString = "{\"name\":\"John\",\"age\":30,\"city\":\"New York\""; // Missing closing brace
		
		assertThrows(Exception.class, () ->
		{
			JsonUtils.string2node(invalidJsonString);
		});
	}
	
	
}
