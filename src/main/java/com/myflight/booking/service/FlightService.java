package com.myflight.booking.service;

import java.util.List;

import com.myflight.booking.dto.FlightDetails;
import com.myflight.booking.dto.SearchRequestDto;
import com.myflight.booking.exception.DateInvalidException;
import com.myflight.booking.exception.FlightNotFoundException;
import com.myflight.booking.exception.MinimumTravellersException;
import com.myflight.booking.exception.SamePlaceException;

public interface FlightService {

	List<FlightDetails> getFlightDetails(SearchRequestDto searchRequestDto)
			throws FlightNotFoundException, SamePlaceException, DateInvalidException, MinimumTravellersException;

}
