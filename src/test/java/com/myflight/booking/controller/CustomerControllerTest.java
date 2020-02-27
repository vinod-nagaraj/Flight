package com.myflight.booking.controller;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.myflight.booking.dto.LoginRequestDto;
import com.myflight.booking.dto.LoginResponseDto;
import com.myflight.booking.entity.Customer;
import com.myflight.booking.exception.CustomerNotFoundException;
import com.myflight.booking.service.CustomerService;

@RunWith(MockitoJUnitRunner.Silent.class)
public class CustomerControllerTest {
	LoginRequestDto loginRequestDto = null;
	Customer customer = null;

	@InjectMocks
	CustomerController customerController;

	@Mock
	CustomerService customerService;

	@Before
	public void before() {
		loginRequestDto = new LoginRequestDto();
		loginRequestDto.setMobileNumber("9876543210");
		loginRequestDto.setPassword("muthu123");

		customer = new Customer();
		customer.setMobileNumber(9876543210L);
		customer.setPassword("muthu123");
	}

	@Test
	public void testCheckCustomerPositive() throws CustomerNotFoundException {
		Mockito.when(customerService.checkCustomer(loginRequestDto)).thenReturn(customer);
		ResponseEntity<LoginResponseDto> response = customerController.checkCustomer(loginRequestDto);
		assertEquals(HttpStatus.OK, response.getStatusCode());
	}
}
