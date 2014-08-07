package com.medialets.exception;

/**
 * Thrown upon receiving bad time-bits input from a CSV file
 * @author peterc
 *
 */
public class MalformedTimeBitsInputException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public MalformedTimeBitsInputException() {
		super();
	}

	public MalformedTimeBitsInputException(String message) {
		super(message); 
	}

	public MalformedTimeBitsInputException(Throwable cause) {
		super(cause); 
	}

	public MalformedTimeBitsInputException(String message, Throwable cause) {
		super(message, cause); 
	}

}
