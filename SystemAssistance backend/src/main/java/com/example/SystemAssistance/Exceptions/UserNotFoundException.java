package com.example.SystemAssistance.Exceptions;

@SuppressWarnings("serial")
public class UserNotFoundException extends ResourceNotFoundException {

	public UserNotFoundException(String msg) {
		super(msg);
	}

}
