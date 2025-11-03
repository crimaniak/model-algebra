package org.aljabr.bridge;

import org.aljabr.IModel;
import org.aljabr.InvalidArgumentException;

public interface IBridge <T>{
	IModel toModel(T input) throws InvalidArgumentException;
	T fromModel(IModel model) throws InvalidArgumentException;
}
