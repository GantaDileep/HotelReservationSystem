package com.demo.hotelreservation.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.demo.hotelreservation.entity.RoomType;

@Repository
public interface RoomTypeRepository extends JpaRepository<RoomType, Long> {

}