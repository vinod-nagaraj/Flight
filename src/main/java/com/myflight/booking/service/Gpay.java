package com.myflight.booking.service;

import org.springframework.stereotype.Service;

import com.myflight.booking.dto.PaymentRequestDto;

@Service("Google Pay")
public class Gpay implements PaymentService  {

	@Override
	public String payment(PaymentRequestDto paymentRequestDto) {
		return "Payment done through Gpay";
	}
	

}
