package com.myflight.booking.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

import com.myflight.booking.constants.ApplicationConstants;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginRequestDto {
	@NotBlank(message = ApplicationConstants.MOBILE_BLANK_MESSAGE)
	@Pattern(regexp = "(^$|[0-9]{10})", message = ApplicationConstants.MOBILE_INVALID)
	private String mobileNumber;
	private String password;
}
