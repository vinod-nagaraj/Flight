package com.myflight.booking.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;


@Entity
@Table
@Getter
@Setter
public class PassengerDetail {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long passengerId;
	@ManyToOne
	@JoinColumn(name = "ticketId")
	private Ticket ticket;
	private String passengerName;
	private Integer passengerAge;
	private String ticketStatus;
}
