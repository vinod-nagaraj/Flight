package com.myflight.booking.exception;

import java.time.format.DateTimeParseException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.myflight.booking.constants.ApplicationConstants;
import com.myflight.booking.dto.ResponseDto;

@ControllerAdvice
public class GlobalExceptionHandler {
	@ExceptionHandler(CustomerNotFoundException.class)
	public ResponseEntity<ResponseDto> customerNotFoundException() {
		ResponseDto responseDto = new ResponseDto();
		responseDto.setMessage(ApplicationConstants.CUSTOMER_NOTFOUND_MESSAGE);
		responseDto.setStatusCode(ApplicationConstants.NOTFOUND_CODE);
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseDto);
	}

	@ExceptionHandler(FlightNotFoundException.class)
	public ResponseEntity<ResponseDto> flightNotFoundException() {
		ResponseDto responseDto = new ResponseDto();
		responseDto.setMessage(ApplicationConstants.FLIGHTLIST_FAILURE_MESSAGE);
		responseDto.setStatusCode(ApplicationConstants.NOTFOUND_CODE);
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseDto);
	}

	@ExceptionHandler(DateTimeParseException.class)
	public ResponseEntity<ResponseDto> dateTimeParseException() {
		ResponseDto responseDto = new ResponseDto();
		responseDto.setMessage(ApplicationConstants.DATE_CANNOT_PARSE_MESSAGE);
		responseDto.setStatusCode(ApplicationConstants.DATE_INVALID_CODE);
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseDto);
	}

	@ExceptionHandler(SamePlaceException.class)
	public ResponseEntity<ResponseDto> samePlaceException() {
		ResponseDto responseDto = new ResponseDto();
		responseDto.setMessage(ApplicationConstants.SAMEPLACE_MESSAGE);
		responseDto.setStatusCode(ApplicationConstants.FORBIDDEN_CODE);
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseDto);
	}

	@ExceptionHandler(DateInvalidException.class)
	public ResponseEntity<ResponseDto> dateInvalidException() {
		ResponseDto responseDto = new ResponseDto();
		responseDto.setMessage(ApplicationConstants.DATE_INVALID_MESSGAE);
		responseDto.setStatusCode(ApplicationConstants.FORBIDDEN_CODE);
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseDto);
	}

	@ExceptionHandler(MinimumTravellersException.class)
	public ResponseEntity<ResponseDto> minimumTravellersException() {
		ResponseDto responseDto = new ResponseDto();
		responseDto.setMessage(ApplicationConstants.MINIMUM_TRAVELLERS_MESSAGE);
		responseDto.setStatusCode(ApplicationConstants.NOTFOUND_CODE);
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseDto);
	}

	@ExceptionHandler(TicketNotFoundException.class)
	public ResponseEntity<ResponseDto> ticketNotFoundException() {
		ResponseDto responseDto = new ResponseDto();
		responseDto.setMessage(ApplicationConstants.TICKETNUMBERINVALID_MESSAGE);
		responseDto.setStatusCode(ApplicationConstants.NOTFOUND_CODE);
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseDto);
	}
	
	@ExceptionHandler(PassengerDetailInvalidException.class)
	public ResponseEntity<ResponseDto> passengerDetailInvalidException() {
		ResponseDto responseDto = new ResponseDto();
		responseDto.setMessage(ApplicationConstants.PASSENGERDETAIL_INVALID_MESSAGE);
		responseDto.setStatusCode(ApplicationConstants.NOTFOUND_CODE);
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseDto);
	}
}