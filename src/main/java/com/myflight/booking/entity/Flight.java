package com.myflight.booking.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table
@Getter
@Setter
public class Flight {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long flightId;
	private String flightName;

}
