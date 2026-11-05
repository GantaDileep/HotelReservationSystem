package com.demo.hotelreservation.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.demo.hotelreservation.dto.request.ReservationRequestDTO;
import com.demo.hotelreservation.dto.response.ReservationResponseDTO;
import com.demo.hotelreservation.enums.ReservationStatus;

public interface ReservationService {
	
	// Get Reservation History By Customer ID
	List<ReservationResponseDTO> getReservationsByCustomerId(
	        Long customerId);
	
	List<ReservationResponseDTO> getReservationsByStatus(
	        ReservationStatus status);

    ReservationResponseDTO createReservation(ReservationRequestDTO dto);

    ReservationResponseDTO getReservationById(Long reservationId);

    Page<ReservationResponseDTO> getAllReservations(Pageable pageable);

    ReservationResponseDTO updateReservation(Long reservationId, ReservationRequestDTO dto);

    void cancelReservation(Long reservationId);
}
