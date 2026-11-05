package com.demo.hotelreservation.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.demo.hotelreservation.entity.Payment;
import com.demo.hotelreservation.enums.PaymentStatus;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, Long> {

    // Named Query
    List<Payment> findByPaymentStatus(
        @Param("status") PaymentStatus status
    );

}