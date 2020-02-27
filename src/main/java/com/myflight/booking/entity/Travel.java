package com.myflight.booking.entity;

import java.time.LocalDate;
import java.time.LocalTime;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Table
@Entity
@Getter
@Setter
public class Travel {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long travelId;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "flightId", nullable = false)
	private Flight flight;
	private String departureLocation;
	private String arrivalLocation;
	private LocalTime arrivalTime;
	private LocalTime departureTime;
	private LocalDate departureDate;
	private LocalTime duration;
}
