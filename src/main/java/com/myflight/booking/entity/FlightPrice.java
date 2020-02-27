package com.myflight.booking.entity;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table
@Getter
@Setter
public class FlightPrice {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long flightPriceId;
	@OneToOne
	@JoinColumn(name = "travelId")
	private Travel travel;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "flightId")
	private Flight flight;
	private String classType;
	private Integer seatsAvailability;
	private Double price;
}
