package com.qa.opencart.exceptions;

public class FrameworkExceptions extends RuntimeException{
	
	public FrameworkExceptions(String mesg) {
		super(mesg);
		printStackTrace();
    }

}
