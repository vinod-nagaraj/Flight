package com.myflight.booking.controller;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

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
import com.myflight.booking.dto.FlightDetails;
import com.myflight.booking.dto.SearchRequestDto;
import com.myflight.booking.dto.SearchResponseDto;
import com.myflight.booking.exception.DateInvalidException;
import com.myflight.booking.exception.FlightNotFoundException;
import com.myflight.booking.exception.MinimumTravellersException;
import com.myflight.booking.exception.SamePlaceException;
import com.myflight.booking.service.FlightService;

@RunWith(MockitoJUnitRunner.Silent.class)
public class FlightControllerTest {
	List<FlightDetails> flightDetailsList = null;
	SearchResponseDto searchResponseDto = null;
	List<FlightDetails> flightDetailsList1 = null;
	SearchResponseDto searchResponseDto1 = null;
	SearchRequestDto searchRequestDto = null;
	SearchRequestDto searchRequestDto1 = null;
	FlightDetails flightDetails = null;

	@InjectMocks
	FlightController flightController;

	@Mock
	FlightService flightService;

	@Before
	public void before() {
		flightDetailsList = new ArrayList<>();
		flightDetails = new FlightDetails();
		flightDetails.setFlightId(1L);
		flightDetails.setArrivalLocation("Madurai");
		flightDetailsList.add(flightDetails);
		searchResponseDto = new SearchResponseDto();
		searchResponseDto.setMessage(ApplicationConstants.FLIGHTLIST_SUCCESS_MESSAGE);
		searchResponseDto.setStatusCode(ApplicationConstants.SUCCESS_CODE);
		searchResponseDto.setFlightDetails(flightDetailsList);

		searchResponseDto1 = new SearchResponseDto();
		searchResponseDto1.setMessage(ApplicationConstants.FLIGHTLIST_FAILURE_MESSAGE);
		searchResponseDto1.setStatusCode(ApplicationConstants.NOTFOUND_CODE);

		searchRequestDto = new SearchRequestDto();
		searchRequestDto.setClassType("Business");
		searchRequestDto.setDepartureDate("2020-02-04");
		searchRequestDto.setDepartureLocation("Madurai");
		searchRequestDto.setArrivalLocation("Bangalore");
		searchRequestDto.setNoOfTravellers(2);

		searchRequestDto1 = new SearchRequestDto();
		searchRequestDto1.setClassType("Business");
		searchRequestDto1.setDepartureDate("2020-02-04");
		searchRequestDto1.setDepartureLocation("Madurai");
		searchRequestDto1.setArrivalLocation("Chennai");
		searchRequestDto1.setNoOfTravellers(2);

	}

	@Test
	public void testGetFlightListPositive()
			throws MinimumTravellersException, FlightNotFoundException, SamePlaceException, DateInvalidException {
		Mockito.when(flightService.getFlightDetails(searchRequestDto)).thenReturn(flightDetailsList);
		ResponseEntity<SearchResponseDto> response = flightController.getFlightList(searchRequestDto);
		assertEquals(HttpStatus.OK, response.getStatusCode());
	}

	@Test
	public void testGetFlightListNegative()
			throws MinimumTravellersException, FlightNotFoundException, SamePlaceException, DateInvalidException {
		Mockito.when(flightService.getFlightDetails(searchRequestDto)).thenReturn(flightDetailsList);
		ResponseEntity<SearchResponseDto> response = flightController.getFlightList(searchRequestDto1);
		assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
	}
}
