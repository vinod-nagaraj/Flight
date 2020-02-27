package com.myflight.booking.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.myflight.booking.entity.FlightPrice;
import com.myflight.booking.entity.Travel;

@Repository
public interface FlightPriceRepository extends JpaRepository<FlightPrice, Long> {

	FlightPrice findByTravelAndClassType(Travel travel, String classType);

	List<FlightPrice> findByTravelAndSeatsAvailabilityGreaterThanEqualAndClassType(Travel travel,
			Integer noOfTravellers, String classType);

}
