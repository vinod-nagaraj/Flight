package com.myflight.booking.dto;

import java.util.List;

import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
public class TicketRequestDto {

	private List<PassengerDetails> passengerDetails;
}
