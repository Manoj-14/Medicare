package com.project.exception;

public class EntityNotCreatedException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public EntityNotCreatedException(){
		super("Error In creating Entity");
	}
}
