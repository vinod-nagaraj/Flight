package com.myflight.booking.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DeletePassengerRequestDto {
	private Long ticketId;
	private String passengerName;
}
