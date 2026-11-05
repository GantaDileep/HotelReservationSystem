package com.demo.hotelreservation.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.demo.hotelreservation.entity.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {

    // Derived Query
    Optional<Customer> findByEmail(String email);

}
