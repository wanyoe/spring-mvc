package com.glp.exception;

import java.io.Serializable;


public class MailException extends RuntimeException implements Serializable {

	private static final long serialVersionUID = 1L;
	
	public MailException(String msg){
		super(msg);
	}
	
	public MailException(Throwable cause){
		super(cause);
	}
	
	public MailException(String message, Throwable cause){
		super(message, cause);
	}
}
