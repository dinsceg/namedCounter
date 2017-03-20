package com.apsis.exception;

/**
 * Created by dinesh on 19/03/17.
 */

public class CounterNotFoundException extends Exception{

	private static final long serialVersionUID = 1L;

	public CounterNotFoundException(String message){
		super(message);
	}
}
