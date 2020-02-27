package com.myflight.booking.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.myflight.booking.entity.PassengerDetail;
import com.myflight.booking.entity.Ticket;

@Repository
public interface PassengerDetailRepository extends JpaRepository<PassengerDetail, Long> {

	Optional<PassengerDetail> findByTicketAndPassengerName(Ticket ticket, String passengerName);
}
