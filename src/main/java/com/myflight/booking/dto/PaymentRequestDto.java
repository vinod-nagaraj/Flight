package com.myflight.booking.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PaymentRequestDto {
	private Long ticketId;
	private String paymentType;

}
