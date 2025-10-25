package org.aljabr.bridge;

import org.aljabr.IModel;
import org.aljabr.Model;

public class Json implements IBridge<String> {

	@Override
	public IModel toModel(String json) throws IllegalArgumentException {
		return new Model(json);
	}

	@Override
	public String fromModel(IModel model) {
		return model.toJson();
	}

}
