package com.myflight.booking.service;

import static org.junit.Assert.assertEquals;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import com.myflight.booking.dto.FlightDetails;
import com.myflight.booking.dto.SearchRequestDto;
import com.myflight.booking.entity.Flight;
import com.myflight.booking.entity.FlightPrice;
import com.myflight.booking.entity.Travel;
import com.myflight.booking.exception.DateInvalidException;
import com.myflight.booking.exception.FlightNotFoundException;
import com.myflight.booking.exception.MinimumTravellersException;
import com.myflight.booking.exception.SamePlaceException;
import com.myflight.booking.repository.FlightPriceRepository;
import com.myflight.booking.repository.TravelRepository;

@RunWith(MockitoJUnitRunner.Silent.class)
public class FlightServiceTest {
	List<FlightDetails> flightDetailsList = null;
	List<Travel> travelList = null;
	List<FlightPrice> flightPriceList = null;
	List<FlightPrice> flightPriceList1 = null;
	FlightDetails flightDetails = null;
	Travel travel = null;
	Flight flight = null;
	FlightPrice flightPrice = null;
	SearchRequestDto searchRequestDto = null;
	SearchRequestDto searchRequestDto1 = null;
	SearchRequestDto searchRequestDto2 = null;
	SearchRequestDto searchRequestDto3 = null;
	SearchRequestDto searchRequestDto4 = null;

	@InjectMocks
	FlightServiceImpl flightServiceImpl;

	@Mock
	FlightPriceRepository flightPriceRepository;

	@Mock
	TravelRepository travelRepository;

	@Before
	public void before() {
		flightDetailsList = new ArrayList<>();
		travelList = new ArrayList<>();
		flightPriceList = new ArrayList<>();
		flightDetails = new FlightDetails();
		travel = new Travel();
		flightPrice = new FlightPrice();
		flight = new Flight();
		flight.setFlightId(100L);
		travel.setArrivalLocation("Madurai");
		travel.setTravelId(1L);
		travel.setFlight(flight);
		travel.setDepartureLocation("Bangalore");
		travelList.add(travel);
		flightPrice.setFlight(flight);
		flightPrice.setFlightPriceId(1L);
		flightPrice.setSeatsAvailability(10);
		flightPrice.setClassType("Business");
		flightPrice.setTravel(travel);
		flightPriceList.add(flightPrice);
		searchRequestDto = new SearchRequestDto();
		searchRequestDto.setClassType("Business");
		searchRequestDto.setDepartureDate("2020-02-04");
		searchRequestDto.setDepartureLocation("Bangalore");
		searchRequestDto.setArrivalLocation("Madurai");
		searchRequestDto.setNoOfTravellers(2);
		flightDetails.setFlightId(1L);
		flightDetails.setArrivalLocation("Madurai");
		flightDetailsList.add(flightDetails);

		searchRequestDto1 = new SearchRequestDto();
		searchRequestDto1.setDepartureDate("2020-02-04");
		searchRequestDto1.setDepartureLocation("Madurai");
		searchRequestDto1.setArrivalLocation("Madurai");

		searchRequestDto2 = new SearchRequestDto();
		searchRequestDto2.setDepartureDate("2020-02-03");
		searchRequestDto2.setDepartureLocation("Bangalore");
		searchRequestDto2.setArrivalLocation("Madurai");

		searchRequestDto3 = new SearchRequestDto();
		searchRequestDto3.setDepartureDate("2020-02-04");
		searchRequestDto3.setDepartureLocation("Chennai");
		searchRequestDto3.setArrivalLocation("Madurai");
		searchRequestDto3.setNoOfTravellers(1);
		
		searchRequestDto4 = new SearchRequestDto();
		searchRequestDto4.setDepartureDate("2020-02-04");
		searchRequestDto4.setDepartureLocation("Bangalore");
		searchRequestDto4.setArrivalLocation("Madurai");
		searchRequestDto4.setNoOfTravellers(0);
	}

	@Test
	public void testGetFlightListPositive()
			throws MinimumTravellersException, FlightNotFoundException, SamePlaceException, DateInvalidException {
		LocalDate departureDate = LocalDate.parse(searchRequestDto.getDepartureDate());
		Mockito.when(travelRepository.findAllByDepartureLocationAndArrivalLocationAndDepartureDate("Bangalore",
				"Madurai", departureDate)).thenReturn(travelList);
		Mockito.when(flightPriceRepository.findByTravelAndSeatsAvailabilityGreaterThanEqualAndClassType(travel, 2,
				"Business")).thenReturn(flightPriceList);
		List<FlightDetails> response = flightServiceImpl.getFlightDetails(searchRequestDto);
		assertEquals(flightDetailsList.size(), response.size());
	}

	@Test(expected = SamePlaceException.class)
	public void testGetFlightListSamePlaceNegative()
			throws FlightNotFoundException, SamePlaceException, DateInvalidException, MinimumTravellersException {
		LocalDate departureDate = LocalDate.parse(searchRequestDto.getDepartureDate());
		Mockito.when(travelRepository.findAllByDepartureLocationAndArrivalLocationAndDepartureDate("Bangalore",
				"Madurai", departureDate)).thenReturn(travelList);
		flightServiceImpl.getFlightDetails(searchRequestDto1);
	}

	@Test(expected = DateInvalidException.class)
	public void testGetFlightListDateInvalid()
			throws FlightNotFoundException, SamePlaceException, DateInvalidException, MinimumTravellersException {
		LocalDate departureDate = LocalDate.parse(searchRequestDto.getDepartureDate());
		Mockito.when(travelRepository.findAllByDepartureLocationAndArrivalLocationAndDepartureDate("Bangalore",
				"Madurai", departureDate)).thenReturn(travelList);
		flightServiceImpl.getFlightDetails(searchRequestDto2);
	}

	@Test(expected = MinimumTravellersException.class)
	public void testGetFlightListTravelersInvalid()
			throws FlightNotFoundException, SamePlaceException, DateInvalidException, MinimumTravellersException {
		LocalDate departureDate = LocalDate.parse(searchRequestDto.getDepartureDate());
		Mockito.when(travelRepository.findAllByDepartureLocationAndArrivalLocationAndDepartureDate("Bangalore",
				"Madurai", departureDate)).thenReturn(travelList);
		flightServiceImpl.getFlightDetails(searchRequestDto4);
	}
	
	@Test(expected = FlightNotFoundException.class)
	public void testGetFlightListFlightListEmptyException()
			throws FlightNotFoundException, SamePlaceException, DateInvalidException, MinimumTravellersException {
		LocalDate departureDate = LocalDate.parse(searchRequestDto.getDepartureDate());
		Mockito.when(travelRepository.findAllByDepartureLocationAndArrivalLocationAndDepartureDate("Bangalore",
				"Madurai", departureDate)).thenReturn(travelList);
		Mockito.when(flightPriceRepository.findByTravelAndSeatsAvailabilityGreaterThanEqualAndClassType(travel, 2,
				"Business")).thenReturn(flightPriceList1);
		flightServiceImpl.getFlightDetails(searchRequestDto3);
	}
}
