package com.myflight.booking.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.myflight.booking.constants.ApplicationConstants;
import com.myflight.booking.dto.LoginRequestDto;
import com.myflight.booking.dto.LoginResponseDto;
import com.myflight.booking.entity.Customer;
import com.myflight.booking.exception.CustomerNotFoundException;
import com.myflight.booking.service.CustomerService;

import lombok.extern.slf4j.Slf4j;

/*
 * Used for customer login before payment
 */

@RequestMapping("/customers")
@RestController
@Slf4j
@CrossOrigin(allowedHeaders = { "*", "*/" }, origins = { "*", "*/" })
public class CustomerController {
	@Autowired
	CustomerService customerService;

	/**
	 * @author Muthu
	 * 
	 *         Method is used for Customer login using his/her credentials i.e
	 *         mobileNumber and password
	 * 
	 * @param loginRequestDto
	 * @return ResponseDto that includes a message and status code
	 * @throws ManagerNotFoundException
	 * @throws ManagerInvalidException
	 */
	@PostMapping("/login")
	public ResponseEntity<LoginResponseDto> checkCustomer(@Valid @RequestBody LoginRequestDto loginRequestDto)
			throws CustomerNotFoundException {
		log.info("For checking whether the person is a manager or not");
		Customer customerResponse = customerService.checkCustomer(loginRequestDto);
		LoginResponseDto loginResponseDto = new LoginResponseDto();
		log.info("The entered credentials belongs to a admin");
		loginResponseDto.setStatusCode(ApplicationConstants.SUCCESS_CODE);
		loginResponseDto.setMessage(ApplicationConstants.LOGIN_SUCCESSMESSAGE);
		loginResponseDto.setCustomerId(customerResponse.getCustomerId());
		loginResponseDto.setName(customerResponse.getCustomerName());
		return new ResponseEntity<>(loginResponseDto, HttpStatus.OK);
	}
}
