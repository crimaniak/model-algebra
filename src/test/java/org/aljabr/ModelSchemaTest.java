package org.aljabr;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;
import java.util.List;
import com.networknt.schema.Error;
import com.networknt.schema.InputFormat;
import com.networknt.schema.Schema;
import com.networknt.schema.SchemaLocation;
import com.networknt.schema.SchemaRegistry;
import com.networknt.schema.dialect.Dialects;

import org.junit.jupiter.api.Test;

class ModelSchemaTest
{
	@Test
    void testModelSchema() throws IOException
    {
		// Load resource model-schema.json from src/main/resources/json_schema.json
		String schemaContent = ResourceLoader.loadResourceAsString(ResourceUrl.model_schema);
		assertNotNull(schemaContent);
		
		SchemaRegistry schemaRegistry = SchemaRegistry.withDialect(Dialects.getDraft202012());
		Schema schema = schemaRegistry.getSchema(SchemaLocation.of(Dialects.getDraft202012().getId()));
		
		List<Error> errors = schema.validate(schemaContent, InputFormat.JSON, executionContext -> {
		    /*
		     * By default since Draft 2019-09 the format keyword only generates annotations
		     * and not assertions.
		     */
		    executionContext.executionConfig(executionConfig -> executionConfig.formatAssertionsEnabled(true));
		});
		
		assertTrue(errors.isEmpty(), "Expected no validation errors in model schema, but found: " + errors);
    }

}
