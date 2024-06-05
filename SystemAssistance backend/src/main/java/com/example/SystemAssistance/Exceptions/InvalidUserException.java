package com.example.SystemAssistance.Exceptions;

@SuppressWarnings("serial")
public class InvalidUserException extends UnauthorizedException {
	public InvalidUserException(String msg) {
		super(msg);
	}
}

