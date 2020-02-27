package com.myflight.booking.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginResponseDto {
	private String message;
	private Integer statusCode;
	private Long customerId;
	private String name;
}
