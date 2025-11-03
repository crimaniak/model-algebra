package org.aljabr.bridge;

import org.aljabr.IModel;
import org.aljabr.InvalidArgumentException;
import org.aljabr.Model;

public class Json implements IBridge<String> {

	@Override
	public IModel toModel(String json) throws InvalidArgumentException 
	{
		return new Model(json);
	}

	@Override
	public String fromModel(IModel model) throws InvalidArgumentException 
	{
		return model.toJson();
	}

}
