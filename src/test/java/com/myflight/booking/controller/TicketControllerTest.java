package com.myflight.booking.controller;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.myflight.booking.constants.ApplicationConstants;
import com.myflight.booking.dto.ResponseDto;
import com.myflight.booking.exception.PassengerDetailInvalidException;
import com.myflight.booking.exception.TicketNotFoundException;
import com.myflight.booking.service.TicketService;

@RunWith(MockitoJUnitRunner.Silent.class)
public class TicketControllerTest {
	@InjectMocks
	TicketController ticketController;

	@Mock
	TicketService ticketService;

	ResponseDto responseDto = null;

	@Before()
	public void before() {
		responseDto = new ResponseDto();
		responseDto.setStatusCode(ApplicationConstants.SUCCESS_CODE);
		responseDto.setMessage(ApplicationConstants.TICKETCANCELLED_MESSAGE);
	}

	@Test
	public void testDeletePassengersPositive() throws TicketNotFoundException, PassengerDetailInvalidException {
		Long ticketId = 1L;
		String passengerName = "Muthu";
		Mockito.when(ticketService.deletePassengers(ticketId, passengerName))
				.thenReturn(ApplicationConstants.TICKETCANCELLED_MESSAGE);
		ResponseEntity<ResponseDto> response = ticketController.deletePassengers(ticketId, passengerName);
		assertEquals(HttpStatus.OK, response.getStatusCode());
	}

}
