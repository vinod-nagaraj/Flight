package com.myflight.booking.exception;

public class TicketNotFoundException  extends Exception{
	
	private static final long serialVersionUID = 1L;

	 public TicketNotFoundException(String exception) {
		
		super(exception);
	}

}
