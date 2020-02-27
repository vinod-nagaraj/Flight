package com.myflight.booking.service;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import com.myflight.booking.dto.LoginRequestDto;
import com.myflight.booking.entity.Customer;
import com.myflight.booking.exception.CustomerNotFoundException;
import com.myflight.booking.repository.CustomerRepository;

@RunWith(MockitoJUnitRunner.Silent.class)
public class CustomerServiceTest {
	LoginRequestDto loginRequestDto = null;
	Customer customer = null;
	LoginRequestDto loginRequestDto1 = null;
	
	@InjectMocks
	CustomerServiceImpl customerServiceImpl;

	@Mock
	CustomerRepository customerRepository;

	@Before
	public void before() {
		loginRequestDto = new LoginRequestDto();
		loginRequestDto.setMobileNumber("9876543210");
		loginRequestDto.setPassword("muthu123");

		customer = new Customer();
		customer.setMobileNumber(9876543210L);
		customer.setPassword("muthu123");
		customer.setCustomerName("Muthu");
		
		loginRequestDto1 = new LoginRequestDto();
		loginRequestDto1.setMobileNumber("9876543211");
		loginRequestDto1.setPassword("muthu123");

	}

	@Test
	public void testCheckCustomerPositive() throws CustomerNotFoundException {
		Long mobileNumber = Long.parseLong(loginRequestDto.getMobileNumber());
		Mockito.when(customerRepository.findByMobileNumberAndPassword(mobileNumber, loginRequestDto.getPassword()))
				.thenReturn(customer);
		Customer response = customerServiceImpl.checkCustomer(loginRequestDto);
		assertEquals(customer.getCustomerName(), response.getCustomerName());
	}
	
	@Test(expected=CustomerNotFoundException.class)
	public void testCheckCustomerNegative() throws CustomerNotFoundException {
		Long mobileNumber = Long.parseLong(loginRequestDto.getMobileNumber());
		Mockito.when(customerRepository.findByMobileNumberAndPassword(mobileNumber, loginRequestDto.getPassword()))
				.thenReturn(customer);
		 customerServiceImpl.checkCustomer(loginRequestDto1);
	}
}
