package org.aljabr.bridge;

import org.aljabr.IModel;

public interface IBridge <T>{
	IModel toModel(T input) throws IllegalArgumentException;
	T fromModel(IModel model);
}
