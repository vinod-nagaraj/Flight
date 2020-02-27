package com.myflight.booking.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.myflight.booking.constants.ApplicationConstants;
import com.myflight.booking.dto.PaymentRequestDto;
import com.myflight.booking.dto.ResponseDto;
import com.myflight.booking.dto.TicketRequestDto;
import com.myflight.booking.dto.TicketResponseDto;
import com.myflight.booking.exception.PassengerDetailInvalidException;
import com.myflight.booking.exception.TicketNotFoundException;
import com.myflight.booking.service.PaymentRegistery;
import com.myflight.booking.service.TicketService;

import lombok.extern.slf4j.Slf4j;

/*
 * Performs the ticket operation that includes booking,cancellation and payment
 * 
 */
@CrossOrigin(allowedHeaders = { "*", "*/" }, origins = { "*", "*/" })
@RestController
@RequestMapping("/tickets")
@Slf4j
public class TicketController {
	@Autowired
	PaymentRegistery paymentRegistery;
	@Autowired
	private TicketService ticketService;
	/**
	 * @author Karthika
	 * 
	 *         Method is used to add the passengers
	 * @param travelId
	 * @param ticketRequestDto
	 * @return
	 */
	@PostMapping(value = "/passengers/{travelId}")
	public TicketResponseDto addPassengers(@PathVariable Long travelId,
			@RequestBody TicketRequestDto ticketRequestDto) {
		return ticketService.addPassengers(travelId, ticketRequestDto);
	}

	/**
	 * @author Vinod B N
	 * 
	 *         Method is used to view the payment option and allows different
	 *         payment option
	 * 
	 * @param paymentRequestDto
	 * @return ResponseDto Displays the status code and message
	 */

	@PostMapping("/pay")
	public ResponseDto payment(@RequestBody PaymentRequestDto paymentRequestDto) {
		paymentRegistery.getServiceBean(paymentRequestDto.getPaymentType()).payment(paymentRequestDto);
		ResponseDto responseDto = new ResponseDto();
		responseDto.setMessage("Payment done through " + paymentRequestDto.getPaymentType());
		responseDto.setStatusCode(HttpStatus.ACCEPTED.value());
		return responseDto;
	}

	/**
	 * @author Muthu
	 * 
	 *         Method is used to delete a particular passenger with the id and name
	 *         entered
	 * 
	 * @param ticketId
	 * @param passengerName
	 * @return SuccessMessage and status code
	 * @throws TicketNotFoundException
	 * @throws PassengerDetailInvalidException
	 */
	@DeleteMapping
	public ResponseEntity<ResponseDto> deletePassengers(@RequestParam Long ticketId, @RequestParam String passengerName)
			throws TicketNotFoundException, PassengerDetailInvalidException {
		log.info("For deleting the passenger details");
		String response = ticketService.deletePassengers(ticketId, passengerName);
		ResponseDto responseDto = new ResponseDto();
		responseDto.setStatusCode(ApplicationConstants.SUCCESS_CODE);
		responseDto.setMessage(response);
		log.info(ApplicationConstants.TICKETCANCELLED_MESSAGE);
		return new ResponseEntity<>(responseDto, HttpStatus.OK);
	}
}
