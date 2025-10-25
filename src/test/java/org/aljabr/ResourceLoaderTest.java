package org.aljabr;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;

import org.junit.jupiter.api.Test;

public class ResourceLoaderTest
{
	@Test
	public void testLoadResourceAsString() throws IOException
	{
		String resourceContent = ResourceLoader.loadResourceAsString(ResourceUrl.user_core_model);
		assertNotNull(resourceContent);
		assertTrue(resourceContent.length() > 0);
	}
	
	@Test
	public void testNonExistingResource() throws IOException {
		assertThrows(IOException.class, () ->
		{
			ResourceLoader.loadResourceAsString("non_existing_resource.json");
		});
	}

}
