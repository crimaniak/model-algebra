package org.aljabr;

public interface IModel {

	String toString();
	String toJson();
	
	IModel add(IModel m);

	IModel sub(IModel m);

	IModel overrideFrom(IModel m);

	IModel enrichFrom(IModel m);

}