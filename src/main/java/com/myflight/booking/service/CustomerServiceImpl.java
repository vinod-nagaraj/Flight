package com.myflight.booking.service;

import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.myflight.booking.constants.ApplicationConstants;
import com.myflight.booking.dto.LoginRequestDto;
import com.myflight.booking.entity.Customer;
import com.myflight.booking.exception.CustomerNotFoundException;
import com.myflight.booking.repository.CustomerRepository;

import lombok.extern.slf4j.Slf4j;

/*
 * Used for customer login before payment
 */
@Service
@Slf4j
public class CustomerServiceImpl implements CustomerService {
	@Autowired
	CustomerRepository customerRepository;

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

	@Override
	public Customer checkCustomer(LoginRequestDto loginRequestDto) throws CustomerNotFoundException {
		Long mobileNumber = Long.parseLong(loginRequestDto.getMobileNumber());
		Customer customer = customerRepository.findByMobileNumberAndPassword(mobileNumber,
				loginRequestDto.getPassword());
		if (Objects.isNull(customer)) {
			log.error(ApplicationConstants.CUSTOMER_NOTFOUND_MESSAGE);
			throw new CustomerNotFoundException(ApplicationConstants.CUSTOMER_NOTFOUND_MESSAGE);
		}
		return customer;
	}

}
