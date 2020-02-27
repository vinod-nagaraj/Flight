package com.myflight.booking.repository;


import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.myflight.booking.entity.Ticket;
import com.myflight.booking.entity.Travel;

@Repository
public interface TicketRepository extends JpaRepository<Ticket, Long> {
	Ticket findByTravel(Travel travel);


	Optional<Ticket> findByTicketId(Long ticketId);
}
