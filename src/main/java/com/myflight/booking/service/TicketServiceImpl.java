package com.myflight.booking.service;

import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.myflight.booking.constants.ApplicationConstants;
import com.myflight.booking.dto.PassengerDetails;
import com.myflight.booking.dto.TicketRequestDto;
import com.myflight.booking.dto.TicketResponseDto;
import com.myflight.booking.entity.FlightPrice;
import com.myflight.booking.entity.PassengerDetail;
import com.myflight.booking.entity.Ticket;
import com.myflight.booking.entity.Travel;
import com.myflight.booking.exception.PassengerDetailInvalidException;
import com.myflight.booking.exception.TicketNotFoundException;
import com.myflight.booking.repository.FlightPriceRepository;
import com.myflight.booking.repository.PassengerDetailRepository;
import com.myflight.booking.repository.TicketRepository;
import com.myflight.booking.repository.TravelRepository;

import lombok.extern.slf4j.Slf4j;

/*
 * Performs the ticket operation that includes booking,cancellation and payment
 * 
 */
@Service
@Slf4j
public class TicketServiceImpl implements TicketService {
	@Autowired
	TicketRepository ticketRepository;

	@Autowired
	PassengerDetailRepository passengerDetailRepository;

	@Autowired
	FlightPriceRepository flightPriceRepository;

	@Autowired
	TravelRepository travelRepository;

	/**
	 * @author Karthika
	 * 
	 *         Method is used to add the passengers
	 * @param travelId
	 * @param ticketRequestDto
	 * @return
	 */
	@Override
	public TicketResponseDto addPassengers(Long travelId, TicketRequestDto ticketRequestDto) {
		TicketResponseDto ticketResponseDto = new TicketResponseDto();
		Travel travel = travelRepository.findById(travelId).get();
		Ticket ticket = ticketRepository.findByTravel(travel);
		for (PassengerDetails passenger : ticketRequestDto.getPassengerDetails()) {
			PassengerDetail passengerDetail = new PassengerDetail();
			passengerDetail.setPassengerName(passenger.getPassengerName());
			Integer passengerAge = Integer.parseInt(passenger.getPassengersAge());
			passengerDetail.setPassengerAge(passengerAge);
			passengerDetail.setTicket(ticket);
			
			passengerDetail.setTicketStatus(ApplicationConstants.TICKETBOOKED);
			passengerDetailRepository.save(passengerDetail);
		}
		ticketResponseDto.setStatusCode(200);
		ticketResponseDto.setStatusMessage("Passenger Added successfully");
		return ticketResponseDto;

	}

	/**
	 * @author Muthu
	 * 
	 *         Method is used to delete a particular passenger with the id and name
	 *         entered
	 * 
	 * @param ticketId
	 * @param passengerName
	 * @return SuccessMessage and status code
	 * @throws TicketNotFoundException
	 * @throws PassengerDetailInvalidException
	 */
	@Override
	public String deletePassengers(Long ticketId, String passengerName)
			throws TicketNotFoundException, PassengerDetailInvalidException {
		log.info("Inside service for deleting the passenger details");
		Optional<Ticket> ticketDetail = ticketRepository.findByTicketId(ticketId);
		if (!(ticketDetail.isPresent())) {
			throw new TicketNotFoundException(ApplicationConstants.TICKETNUMBERINVALID_MESSAGE);
		}
		Optional<PassengerDetail> passengerDetail = passengerDetailRepository
				.findByTicketAndPassengerName(ticketDetail.get(), passengerName);
		if (!(passengerDetail.isPresent())) {
			log.error(ApplicationConstants.PASSENGERDETAIL_INVALID_MESSAGE);
			throw new PassengerDetailInvalidException(ApplicationConstants.PASSENGERDETAIL_INVALID_MESSAGE);
		}
		passengerDetail.get().setTicketStatus(ApplicationConstants.TICKETCANCELLED);
		passengerDetailRepository.save(passengerDetail.get());
		Integer noOfTravellers = ticketDetail.get().getNoOfTravellers();
		if (noOfTravellers.equals(ApplicationConstants.ZERO)) {
			return ApplicationConstants.TICKET_ALREADYCANCELLED_MESSAGE;
		} else if (noOfTravellers.equals(ApplicationConstants.MINIMUM_TRAVELLERS)) {
			return updatePassengerDetail(ticketDetail.get(), ApplicationConstants.TICKETCANCELLED);
		} else {
			return updatePassengerDetail(ticketDetail.get(), ApplicationConstants.TICKETBOOKED);
		}
	}

	/**
	 * @author Muthu
	 * 
	 *         Method is used for updating the number of travelers details
	 */
	private String updatePassengerDetail(Ticket ticketDetail, String status) {
		Integer ticketTravellers = ticketDetail.getNoOfTravellers() - ApplicationConstants.MINIMUM_TRAVELLERS;
		ticketDetail.setStatus(status);
		ticketDetail.setNoOfTravellers(ticketTravellers);
		ticketRepository.save(ticketDetail);
		Travel travel = ticketDetail.getTravel();
		FlightPrice flightTicketUpdate = flightPriceRepository.findByTravelAndClassType(travel,
				ticketDetail.getClassType());
		if (!(Objects.isNull(flightTicketUpdate))) {
			Integer flightTicketsAvailable = flightTicketUpdate.getSeatsAvailability() + 1;
			flightTicketUpdate.setSeatsAvailability(flightTicketsAvailable);
			flightPriceRepository.save(flightTicketUpdate);
		}
		return ApplicationConstants.TICKETCANCELLED_MESSAGE;
	}

}

