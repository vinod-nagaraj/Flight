package com.myflight.booking.service;

import static org.junit.Assert.assertEquals;

import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import com.myflight.booking.constants.ApplicationConstants;
import com.myflight.booking.entity.FlightPrice;
import com.myflight.booking.entity.PassengerDetail;
import com.myflight.booking.entity.Ticket;
import com.myflight.booking.entity.Travel;
import com.myflight.booking.exception.PassengerDetailInvalidException;
import com.myflight.booking.exception.TicketNotFoundException;
import com.myflight.booking.repository.FlightPriceRepository;
import com.myflight.booking.repository.PassengerDetailRepository;
import com.myflight.booking.repository.TicketRepository;

@RunWith(MockitoJUnitRunner.Silent.class)
public class TicketServiceTest {
	@InjectMocks
	TicketServiceImpl ticketServiceImpl;

	@Mock
	TicketRepository ticketRepository;

	@Mock
	PassengerDetailRepository passengerDetailRepository;

	@Mock
	FlightPriceRepository flightPriceRepository;

	Ticket ticket = null;
	PassengerDetail passengerDetail = null;
	Ticket ticket1 = null;
	PassengerDetail passengerDetail1 = null;
	FlightPrice flightPrice = null;
	Travel travel = null;

	FlightPrice flightPrice1 = null;

	@Before()
	public void before() {
		ticket = new Ticket();
		ticket.setTicketId(1L);
		ticket.setNoOfTravellers(0);
		passengerDetail = new PassengerDetail();
		passengerDetail.setPassengerId(1L);
		passengerDetail.setPassengerName("Muthu");
		passengerDetail.setTicket(ticket);

		ticket1 = new Ticket();
		travel = new Travel();
		flightPrice = new FlightPrice();
		travel.setTravelId(1L);
		ticket1.setTicketId(1L);
		ticket1.setNoOfTravellers(1);
		ticket1.setClassType("Business");
		passengerDetail1 = new PassengerDetail();
		passengerDetail1.setPassengerId(1L);
		passengerDetail1.setPassengerName("Muthu");
		passengerDetail1.setTicket(ticket1);
		flightPrice.setTravel(travel);
		flightPrice.setClassType("Business");
		flightPrice.setSeatsAvailability(10);
	}

	@Test(expected = TicketNotFoundException.class)
	public void testDeletePassengersTicketException() throws TicketNotFoundException, PassengerDetailInvalidException {
		Long ticketId = 1L;
		String passengerName = "Muthu";
		Mockito.when(ticketRepository.findByTicketId(2L)).thenReturn(Optional.ofNullable(null));
		ticketServiceImpl.deletePassengers(ticketId, passengerName);
	}

	@Test(expected = PassengerDetailInvalidException.class)
	public void testDeletePassengersInvalidException() throws TicketNotFoundException, PassengerDetailInvalidException {
		Long ticketId = 1L;
		String passengerName = "Muthu";
		Mockito.when(ticketRepository.findByTicketId(1L)).thenReturn(Optional.of(ticket));
		Mockito.when(passengerDetailRepository.findByTicketAndPassengerName(ticket, "Palani"))
				.thenReturn(Optional.ofNullable(null));
		ticketServiceImpl.deletePassengers(ticketId, passengerName);
	}

	@Test
	public void testDeletePassengersAlreadyCancelled() throws TicketNotFoundException, PassengerDetailInvalidException {
		Long ticketId = 1L;
		String passengerName = "Muthu";
		Mockito.when(ticketRepository.findByTicketId(1L)).thenReturn(Optional.of(ticket));
		Mockito.when(passengerDetailRepository.findByTicketAndPassengerName(ticket, passengerName))
				.thenReturn(Optional.of(passengerDetail));
		String response = ticketServiceImpl.deletePassengers(ticketId, passengerName);
		assertEquals(ApplicationConstants.TICKET_ALREADYCANCELLED_MESSAGE, response);
	}

	@Test
	public void testDeletePassengersOneTicketCancelled()
			throws TicketNotFoundException, PassengerDetailInvalidException {
		Long ticketId = 1L;
		String passengerName = "Muthu";
		Mockito.when(ticketRepository.findByTicketId(1L)).thenReturn(Optional.of(ticket1));
		Mockito.when(passengerDetailRepository.findByTicketAndPassengerName(ticket1, passengerName))
				.thenReturn(Optional.of(passengerDetail1));
		Mockito.when(flightPriceRepository.findByTravelAndClassType(travel, "Business")).thenReturn(flightPrice);
		String response = ticketServiceImpl.deletePassengers(ticketId, passengerName);
		assertEquals(ApplicationConstants.TICKETCANCELLED_MESSAGE, response);
	}
}
