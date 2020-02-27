package com.myflight.booking.entity;

import java.time.LocalTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;
@Table
@Entity
@Getter
@Setter
public class Ticket {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long ticketId;
	@OneToOne
	@JoinColumn(name = "travelId")
	private Travel travel;
	@OneToOne
	@JoinColumn(name = "customerId")
	private Customer customer;
	private String departureLocation;
	private String arrivalLocation;
	private Integer noOfTravellers;
	private Double amount;
	private LocalTime depatureTime;
	private String status;
	private String classType;
}
