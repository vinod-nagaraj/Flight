package com.myflight.booking.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.myflight.booking.constants.ApplicationConstants;
import com.myflight.booking.dto.FlightDetails;
import com.myflight.booking.dto.SearchRequestDto;
import com.myflight.booking.dto.SearchResponseDto;
import com.myflight.booking.exception.DateInvalidException;
import com.myflight.booking.exception.FlightNotFoundException;
import com.myflight.booking.exception.MinimumTravellersException;
import com.myflight.booking.exception.SamePlaceException;
import com.myflight.booking.service.FlightService;

import lombok.extern.slf4j.Slf4j;

/*
 * Method is used to view the list of flights available based on the criteria suggested by the customer
 */
@CrossOrigin(allowedHeaders = { "*", "*/" }, origins = { "*", "*/" })
@RestController
@RequestMapping("/flights")
@Slf4j
public class FlightController {
	@Autowired
	FlightService flightService;

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

	@PostMapping
	public ResponseEntity<SearchResponseDto> getFlightList(@Valid @RequestBody SearchRequestDto searchRequestDto)
			throws FlightNotFoundException, SamePlaceException, DateInvalidException, MinimumTravellersException {
		log.info("For getting the list of flights");
		List<FlightDetails> flightDetails = flightService.getFlightDetails(searchRequestDto);
		if (!(flightDetails.isEmpty())) {
			SearchResponseDto searchResponseDto = new SearchResponseDto();
			searchResponseDto.setFlightDetails(flightDetails);
			searchResponseDto.setStatusCode(ApplicationConstants.SUCCESS_CODE);
			searchResponseDto.setMessage(ApplicationConstants.FLIGHTLIST_SUCCESS_MESSAGE);
			return new ResponseEntity<>(searchResponseDto, HttpStatus.OK);
		}
		SearchResponseDto searchResponseDto = new SearchResponseDto();
		searchResponseDto.setStatusCode(ApplicationConstants.NOTFOUND_CODE);
		searchResponseDto.setMessage(ApplicationConstants.FLIGHTLIST_FAILURE_MESSAGE);
		return new ResponseEntity<>(searchResponseDto, HttpStatus.NOT_FOUND);
	}
}
