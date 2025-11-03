package org.aljabr;

public class InvalidArgumentException extends Exception
{
	private static final long serialVersionUID = 1L;
	
	public InvalidArgumentException(String message, Throwable cause)
	{
		super(message, cause);
	}

}
