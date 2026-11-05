package com.demo.hotelreservation.service.impl;

import java.math.BigDecimal;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.demo.hotelreservation.dto.request.ReservationRequestDTO;
import com.demo.hotelreservation.dto.response.ReservationResponseDTO;
import com.demo.hotelreservation.entity.Customer;
import com.demo.hotelreservation.entity.Reservation;
import com.demo.hotelreservation.entity.Room;
import com.demo.hotelreservation.enums.ReservationStatus;
import com.demo.hotelreservation.mapper.ReservationMapper;
import com.demo.hotelreservation.repository.CustomerRepository;
import com.demo.hotelreservation.repository.ReservationRepository;
import com.demo.hotelreservation.repository.RoomRepository;
import com.demo.hotelreservation.service.ReservationService;
import com.demo.hotelreservation.exception.ReservationNotFoundException;

@Service
@Transactional
public class ReservationServiceImpl implements ReservationService {

    private final ReservationRepository reservationRepository;
    private final CustomerRepository customerRepository;
    private final RoomRepository roomRepository;
    private final ReservationMapper reservationMapper;

    public ReservationServiceImpl(
            ReservationRepository reservationRepository,
            CustomerRepository customerRepository,
            RoomRepository roomRepository,
            ReservationMapper reservationMapper) {

        this.reservationRepository = reservationRepository;
        this.customerRepository = customerRepository;
        this.roomRepository = roomRepository;
        this.reservationMapper = reservationMapper;
    }
    
 // Get Reservation History By Customer ID
    @Override
    @Transactional(readOnly = true)
    public List<ReservationResponseDTO> getReservationsByCustomerId(
            Long customerId) {

        return reservationRepository
                .findReservationsByCustomerId(customerId)
                .stream()
                .map(reservationMapper::toResponse)
                .toList();
    }

    // Get Reservations By Status
    @Override
    @Transactional(readOnly = true)
    public List<ReservationResponseDTO> getReservationsByStatus(
            ReservationStatus status) {

        return reservationRepository.findByReservationStatus(status)
                .stream()
                .map(reservationMapper::toResponse)
                .toList();
    }

    // 1. Create Reservation
    @Override
    public ReservationResponseDTO createReservation(
            ReservationRequestDTO dto) {

        Customer customer = customerRepository.findById(dto.getCustomerId())
                .orElseThrow(() ->
                        new RuntimeException("Customer not found"));

        Room room = roomRepository.findById(dto.getRoomId())
                .orElseThrow(() ->
                        new RuntimeException("Room not found"));

        // Validate check-in and check-out dates
        if (!dto.getCheckOutDate().isAfter(dto.getCheckInDate())) {
            throw new IllegalArgumentException(
                    "Check-out date must be after check-in date");
        }

        // Validate room capacity
        if (dto.getNumberOfGuests() > room.getRoomType().getMaximumOccupancy()) {
            throw new IllegalArgumentException(
                    "Guest count exceeds room capacity");
        }

        // Check whether another reservation overlaps these dates
        // New reservations do not have an existing reservation ID
        long overlappingReservations =
                reservationRepository.countOverlappingReservations(
                        room.getRoomId(),
                        dto.getCheckInDate(),
                        dto.getCheckOutDate(),
                        ReservationStatus.CANCELLED);

        if (overlappingReservations > 0) {
            throw new IllegalArgumentException(
                    "Room is already reserved for the selected dates");
        }

        // Calculate number of nights and total amount
        long nights = ChronoUnit.DAYS.between(
                dto.getCheckInDate(),
                dto.getCheckOutDate());

        BigDecimal pricePerNight =
                room.getRoomType().getPricePerNight();

        BigDecimal totalAmount =
                pricePerNight.multiply(BigDecimal.valueOf(nights));

        // Convert DTO to entity
        Reservation reservation = reservationMapper.toEntity(dto);

        reservation.setBookingReference(
                UUID.randomUUID().toString());

        reservation.setCustomer(customer);
        reservation.setRoom(room);
        reservation.setNumberOfNights((int) nights);
        reservation.setPricePerNight(pricePerNight);
        reservation.setTotalAmount(totalAmount);
        reservation.setReservationStatus(ReservationStatus.PENDING);

        Reservation savedReservation =
                reservationRepository.save(reservation);

        return reservationMapper.toResponse(savedReservation);
    }

    // 2. Get Reservation By ID
    @Override
    @Transactional(readOnly = true)
    public ReservationResponseDTO getReservationById(
            Long reservationId) {

        Reservation reservation =
                reservationRepository.findById(reservationId)
                        .orElseThrow(() ->
                        new ReservationNotFoundException("Reservation not found"));

        return reservationMapper.toResponse(reservation);
    }

    // 3. Get All Reservations with Pagination
    @Override
    @Transactional(readOnly = true)
    public Page<ReservationResponseDTO> getAllReservations(
            Pageable pageable) {

        return reservationRepository.findAll(pageable)
                .map(reservationMapper::toResponse);
    }

    // 4. Update Reservation
    @Override
    public ReservationResponseDTO updateReservation(
            Long reservationId,
            ReservationRequestDTO dto) {

        Reservation reservation =
                reservationRepository.findById(reservationId)
                        .orElseThrow(() ->
                        new ReservationNotFoundException("Reservation not found"));

        Customer customer = customerRepository.findById(dto.getCustomerId())
                .orElseThrow(() ->
                        new RuntimeException("Customer not found"));

        Room room = roomRepository.findById(dto.getRoomId())
                .orElseThrow(() ->
                        new RuntimeException("Room not found"));

        // Validate check-in and check-out dates
        if (!dto.getCheckOutDate().isAfter(dto.getCheckInDate())) {
            throw new IllegalArgumentException(
                    "Check-out date must be after check-in date");
        }

        // Validate room capacity
        if (dto.getNumberOfGuests() > room.getRoomType().getMaximumOccupancy()) {
            throw new IllegalArgumentException(
                    "Guest count exceeds room capacity");
        }

        // Check overlapping reservations, excluding the current reservation
        long overlappingReservations =
                reservationRepository.countOverlappingReservationsExcludingSelf(
                        room.getRoomId(),
                        dto.getCheckInDate(),
                        dto.getCheckOutDate(),
                        ReservationStatus.CANCELLED,
                        reservationId);

        if (overlappingReservations > 0) {
            throw new IllegalArgumentException(
                    "Room is already reserved for the selected dates");
        }

        // Calculate number of nights and total amount
        long nights = ChronoUnit.DAYS.between(
                dto.getCheckInDate(),
                dto.getCheckOutDate());

        BigDecimal pricePerNight =
                room.getRoomType().getPricePerNight();

        BigDecimal totalAmount =
                pricePerNight.multiply(BigDecimal.valueOf(nights));

        // Update existing reservation
        reservationMapper.toUpdate(dto, reservation);

        reservation.setCustomer(customer);
        reservation.setRoom(room);
        reservation.setNumberOfNights((int) nights);
        reservation.setPricePerNight(pricePerNight);
        reservation.setTotalAmount(totalAmount);

        Reservation updatedReservation =
                reservationRepository.save(reservation);

        return reservationMapper.toResponse(updatedReservation);
    }

    // 5. Cancel Reservation
    @Override
    public void cancelReservation(Long reservationId) {

        Reservation reservation =
                reservationRepository.findById(reservationId)
                        .orElseThrow(() ->
                        new ReservationNotFoundException("Reservation not found"));

        reservation.setReservationStatus(ReservationStatus.CANCELLED);

        reservationRepository.save(reservation);
    }
}
