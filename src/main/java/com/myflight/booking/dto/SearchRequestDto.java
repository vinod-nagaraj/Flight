package com.myflight.booking.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.myflight.booking.constants.ApplicationConstants;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SearchRequestDto {
	@NotBlank(message = ApplicationConstants.DEPARTURELOCATION_INVALID)
	private String departureLocation;
	@NotBlank(message = ApplicationConstants.ARRIVALLOCATION_INVALID)
	private String arrivalLocation;
	@NotBlank(message = ApplicationConstants.DEPARTUREDATE_INVALID)
	private String departureDate;
	@NotNull(message = ApplicationConstants.TRAVELLERS_INVALID)
	private Integer noOfTravellers;
	@NotBlank(message = ApplicationConstants.CLASSTYPE_INVALID)
	private String classType;
}
