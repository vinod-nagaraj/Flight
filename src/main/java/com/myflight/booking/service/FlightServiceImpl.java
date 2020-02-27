package com.myflight.booking.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.myflight.booking.constants.ApplicationConstants;
import com.myflight.booking.dto.FlightDetails;
import com.myflight.booking.dto.SearchRequestDto;
import com.myflight.booking.entity.FlightPrice;
import com.myflight.booking.entity.Travel;
import com.myflight.booking.exception.DateInvalidException;
import com.myflight.booking.exception.FlightNotFoundException;
import com.myflight.booking.exception.MinimumTravellersException;
import com.myflight.booking.exception.SamePlaceException;
import com.myflight.booking.repository.FlightPriceRepository;
import com.myflight.booking.repository.TravelRepository;

import lombok.extern.slf4j.Slf4j;

/*
 * Method is used to view the list of flights available based on the criteria suggested by the customer
 */
@Service
@Slf4j
public class FlightServiceImpl implements FlightService {
	@Autowired
	TravelRepository travelRepository;

	@Autowired
	FlightPriceRepository flightPriceRepository;

	/**
	 * @author Muthu
	 * 
	 *         Method is used to view the list of available flights based on the
	 *         arrival,departure,class and number of passengers
	 * 
	 * @param searchRequestDto
	 * @return SearchResponseDto Displays the flight details along with a status
	 *         code and message
	 * @throws FlightNotFoundException
	 * @throws SamePlaceException
	 * @throws DateInvalidException
	 * @throws MinimumTravellersException
	 */

	@Override
	public List<FlightDetails> getFlightDetails(SearchRequestDto searchRequestDto)
			throws FlightNotFoundException, SamePlaceException, DateInvalidException, MinimumTravellersException {
		String departureLocation = searchRequestDto.getDepartureLocation();
		String arrivalLocation = searchRequestDto.getArrivalLocation();
		if (arrivalLocation.equalsIgnoreCase(departureLocation)) {
			log.error(ApplicationConstants.SAMEPLACE_MESSAGE);
			throw new SamePlaceException(ApplicationConstants.SAMEPLACE_MESSAGE);
		}
		LocalDate departureDate = LocalDate.parse(searchRequestDto.getDepartureDate());
		if (departureDate.isBefore(LocalDate.now())) {
			log.error(ApplicationConstants.DATE_INVALID_MESSGAE);
			throw new DateInvalidException(ApplicationConstants.DATE_INVALID_MESSGAE);
		}

		if (searchRequestDto.getNoOfTravellers() < ApplicationConstants.MINIMUM_TRAVELLERS) {
			log.error(ApplicationConstants.MINIMUM_TRAVELLERS_MESSAGE);
			throw new MinimumTravellersException(ApplicationConstants.MINIMUM_TRAVELLERS_MESSAGE);
		}

		List<Travel> travels = travelRepository.findAllByDepartureLocationAndArrivalLocationAndDepartureDate(
				departureLocation, arrivalLocation, departureDate);
		if (travels.isEmpty()) {
			throw new FlightNotFoundException(ApplicationConstants.FLIGHTLIST_FAILURE_MESSAGE);
		}
		List<FlightDetails> flightDetailsList = new ArrayList<>();
		travels.forEach(travel -> {
			List<FlightPrice> flightPrice = flightPriceRepository
					.findByTravelAndSeatsAvailabilityGreaterThanEqualAndClassType(travel,
							searchRequestDto.getNoOfTravellers(), searchRequestDto.getClassType());
			flightPrice.forEach(flightPriceDetails -> {
				FlightDetails flightDetails = new FlightDetails();
				flightDetails.setFlightName(travel.getFlight().getFlightName());
				flightDetails.setFlightId(travel.getFlight().getFlightId());
				BeanUtils.copyProperties(travel, flightDetails);
				flightDetails.setPrice(flightPriceDetails.getPrice());
				flightDetailsList.add(flightDetails);
			});
		});
		return flightDetailsList;
	}

}
