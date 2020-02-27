package com.myflight.booking.service;

import com.myflight.booking.dto.PaymentRequestDto;

public interface PaymentService {
	/**
	 * @author Vinod B N 
	 * @param paymentRequestDto
	 */         
	public String payment(PaymentRequestDto paymentRequestDto);

}
