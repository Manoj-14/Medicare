package com.project.exception;

public class UserNotFoundException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public UserNotFoundException(){
		super("UserNotFound");
	}
}
