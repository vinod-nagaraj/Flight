package com.myflight.booking.exception;

public class CustomerNotFoundException  extends Exception{
	
	private static final long serialVersionUID = 1L;

	 public CustomerNotFoundException(String exception) {
		
		super(exception);
	}

}
