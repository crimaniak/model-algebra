package org.aljabr;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;

import org.junit.jupiter.api.Test;

class ResourceLoaderTest
{
	@Test
	void testLoadResourceAsString() throws IOException
	{
		String resourceContent = ResourceLoader.loadResourceAsString(ResourceUrl.user_core_model);
		assertNotNull(resourceContent);
		assertTrue(!resourceContent.isEmpty());
	}
	
	@Test
	void testNonExistingResource() {
		assertThrows(IOException.class, () ->
		{
			ResourceLoader.loadResourceAsString("non_existing_resource.json");
		});
	}

}
