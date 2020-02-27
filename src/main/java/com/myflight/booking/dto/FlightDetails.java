package com.myflight.booking.dto;

import java.time.LocalDate;
import java.time.LocalTime;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FlightDetails {
	private String flightName;
	private String departureLocation;
	private String arrivalLocation;
	private LocalDate departureDate;
	private LocalTime departureTime;
	private LocalTime arrivalTime;
	private LocalTime duration;
	private Double price;
	private Long travelId;
	private Long flightId;
}
