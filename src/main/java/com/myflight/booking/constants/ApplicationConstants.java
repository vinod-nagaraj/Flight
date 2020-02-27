package com.myflight.booking.constants;

public class ApplicationConstants {
	private ApplicationConstants() {
		
	}
	public static final String CUSTOMER_NOTFOUND_MESSAGE = "Sorry!!! Please check your credentials";
	public static final String LOGIN_SUCCESSMESSAGE = "The credentials you entered is valid";
	public static final Integer SUCCESS_CODE = 200;
	public static final Integer NOTFOUND_CODE = 404;
	public static final Integer DATE_INVALID_CODE = 401;
	public static final Integer FORBIDDEN_CODE = 403;
	public static final String DATE_CANNOT_PARSE_MESSAGE = "Please enter a valid date";
	public static final String FLIGHTLIST_SUCCESS_MESSAGE = "List of flights according to your search is displayed";
	public static final String FLIGHTLIST_FAILURE_MESSAGE = "Sorry!!!No matches found according to your search";
	public static final String SAMEPLACE_MESSAGE = "Source and destination cannot be same";
	public static final String DATE_INVALID_MESSGAE = "Date cannot be before today's date";
	public static final String MOBILE_INVALID = "Please enter a valid mobile number";
	public static final String MOBILE_BLANK_MESSAGE = "Mobile number can't be left blank";
	public static final Integer MINIMUM_TRAVELLERS = 1;
	public static final String MINIMUM_TRAVELLERS_MESSAGE = "Please enter valid travellers to search for availability";
	public static final String DEPARTURELOCATION_INVALID = "Please enter a departure location";
	public static final String ARRIVALLOCATION_INVALID = "Please enter a arrival location";
	public static final String DEPARTUREDATE_INVALID = "Please enter a date";
	public static final String TRAVELLERS_INVALID = "Please enter no of travllers";
	public static final String CLASSTYPE_INVALID = "Please enter type of class you wish to travel";
	public static final String TICKETCANCELLED_MESSAGE = "Your ticket has been cancelled successfully";
	public static final String TICKETNUMBERINVALID_MESSAGE = "Please check the ticketnumber entered";
	public static final String PASSENGERDETAIL_INVALID_MESSAGE = "There is no such passenger found. Please enter a valid passenger";
	public static final String TICKETCANCELLED = "Cancelled";
	public static final String TICKETBOOKED="Booked";
	public static final Integer ZERO = 0;
	public static final String TICKET_ALREADYCANCELLED_MESSAGE = "Your ticket is already cancelled";
}
