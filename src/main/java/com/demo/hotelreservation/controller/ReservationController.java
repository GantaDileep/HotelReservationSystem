package com.demo.hotelreservation.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.demo.hotelreservation.dto.request.ReservationRequestDTO;
import com.demo.hotelreservation.dto.response.ReservationResponseDTO;
import com.demo.hotelreservation.service.ReservationService;

import java.util.List;
import com.demo.hotelreservation.enums.ReservationStatus;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/reservations")
public class ReservationController {

    private final ReservationService reservationService;

    public ReservationController(ReservationService reservationService) {
        this.reservationService = reservationService;
    }
    
 // Get Reservation History By Customer ID
    @GetMapping("/customer/{customerId}")
    public ResponseEntity<List<ReservationResponseDTO>> getReservationsByCustomerId(
            @PathVariable Long customerId) {

        return ResponseEntity.ok(
                reservationService.getReservationsByCustomerId(customerId));
    }
    
 // Get Reservations By Status
    @GetMapping("/status/{status}")
    public ResponseEntity<List<ReservationResponseDTO>> getReservationsByStatus(
            @PathVariable ReservationStatus status) {

        return ResponseEntity.ok(
                reservationService.getReservationsByStatus(status));
    }

    // 1. Create Reservation
    @PostMapping
    public ResponseEntity<ReservationResponseDTO> createReservation(
            @Valid @RequestBody ReservationRequestDTO dto) {

        return new ResponseEntity<>(
                reservationService.createReservation(dto),
                HttpStatus.CREATED);
    }

    // 2. Get Reservation By ID
    @GetMapping("/{reservationId}")
    public ResponseEntity<ReservationResponseDTO> getReservationById(
            @PathVariable Long reservationId) {

        return ResponseEntity.ok(
                reservationService.getReservationById(reservationId));
    }

    // 3. Get All Reservations with Pagination
    @GetMapping
    public ResponseEntity<Page<ReservationResponseDTO>> getAllReservations(
            Pageable pageable) {

        return ResponseEntity.ok(
                reservationService.getAllReservations(pageable));
    }

    // 4. Update Reservation
    @PutMapping("/{reservationId}")
    public ResponseEntity<ReservationResponseDTO> updateReservation(
            @PathVariable Long reservationId,
            @Valid @RequestBody ReservationRequestDTO dto) {

        return ResponseEntity.ok(
                reservationService.updateReservation(reservationId, dto));
    }

    // 5. Cancel Reservation
    @PutMapping("/{reservationId}/cancel")
    public ResponseEntity<Void> cancelReservation(
            @PathVariable Long reservationId) {

        reservationService.cancelReservation(reservationId);

        return ResponseEntity.noContent().build();
    }
}
