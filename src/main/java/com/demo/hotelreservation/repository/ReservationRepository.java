package com.demo.hotelreservation.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.demo.hotelreservation.entity.Reservation;
import com.demo.hotelreservation.enums.ReservationStatus;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Long> {
	
	// Get Reservation History By Customer ID
	@Query("""
	    SELECT r
	    FROM Reservation r
	    WHERE r.customer.customerId = :customerId
	    ORDER BY r.createdAt DESC
	    """)
	List<Reservation> findReservationsByCustomerId(
	        @Param("customerId") Long customerId);

    // JPQL Query: Get reservations by status
    @Query("SELECT r FROM Reservation r WHERE r.reservationStatus = :status")
    List<Reservation> findByReservationStatus(
            @Param("status") ReservationStatus status);

    // Check overlapping reservations when creating a reservation
    @Query("""
        SELECT COUNT(r)
        FROM Reservation r
        WHERE r.room.roomId = :roomId
          AND r.reservationStatus <> :cancelledStatus
          AND r.checkInDate < :checkOutDate
          AND r.checkOutDate > :checkInDate
        """)
    long countOverlappingReservations(
            @Param("roomId") Long roomId,
            @Param("checkInDate") LocalDate checkInDate,
            @Param("checkOutDate") LocalDate checkOutDate,
            @Param("cancelledStatus") ReservationStatus cancelledStatus);

    // Check overlapping reservations when updating,
    // excluding the current reservation
    @Query("""
        SELECT COUNT(r)
        FROM Reservation r
        WHERE r.room.roomId = :roomId
          AND r.reservationStatus <> :cancelledStatus
          AND r.checkInDate < :checkOutDate
          AND r.checkOutDate > :checkInDate
          AND r.reservationId <> :reservationId
        """)
    long countOverlappingReservationsExcludingSelf(
            @Param("roomId") Long roomId,
            @Param("checkInDate") LocalDate checkInDate,
            @Param("checkOutDate") LocalDate checkOutDate,
            @Param("cancelledStatus") ReservationStatus cancelledStatus,
            @Param("reservationId") Long reservationId);
}
 
 