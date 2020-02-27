package com.myflight.booking.service;

import com.myflight.booking.dto.TicketRequestDto;
import com.myflight.booking.dto.TicketResponseDto;
import com.myflight.booking.exception.PassengerDetailInvalidException;
import com.myflight.booking.exception.TicketNotFoundException;

public interface TicketService {
	TicketResponseDto addPassengers(Long travelId, TicketRequestDto ticketRequestDto);

	String deletePassengers(Long ticketId, String passengerName)
			throws TicketNotFoundException, PassengerDetailInvalidException;
}
