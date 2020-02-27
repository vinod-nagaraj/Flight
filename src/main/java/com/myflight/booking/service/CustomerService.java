package com.myflight.booking.service;

import com.myflight.booking.dto.LoginRequestDto;
import com.myflight.booking.entity.Customer;
import com.myflight.booking.exception.CustomerNotFoundException;

public interface CustomerService {

	Customer checkCustomer(LoginRequestDto loginRequestDto) throws CustomerNotFoundException;

}
