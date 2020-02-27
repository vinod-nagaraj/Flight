package com.myflight.booking.exception;

public class FlightNotFoundException  extends Exception{
	
	private static final long serialVersionUID = 1L;

	 public FlightNotFoundException(String exception) {
		
		super(exception);
	}

}
