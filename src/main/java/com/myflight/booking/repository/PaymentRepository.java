package com.myflight.booking.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.myflight.booking.entity.Payment;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, Long>{

}
