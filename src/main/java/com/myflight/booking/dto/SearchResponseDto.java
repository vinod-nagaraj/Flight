package com.myflight.booking.dto;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SearchResponseDto {
	private List<FlightDetails> flightDetails;
	private Integer statusCode;
	private String message;
}
