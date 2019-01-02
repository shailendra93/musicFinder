package com.shail.musicfinder.musicFinder.exceptions;

public class NullInputException extends InvalidInputException {

	public NullInputException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}
	public NullInputException() {
		
		super("Null pointer exception");
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

}
