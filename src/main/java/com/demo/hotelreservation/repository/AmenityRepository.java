package com.demo.hotelreservation.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.demo.hotelreservation.entity.Amenity;

@Repository
public interface AmenityRepository extends JpaRepository<Amenity, Long> {

}