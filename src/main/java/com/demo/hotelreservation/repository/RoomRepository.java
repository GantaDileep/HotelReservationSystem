package com.demo.hotelreservation.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.demo.hotelreservation.entity.Room;

@Repository
public interface RoomRepository extends JpaRepository<Room, Long> {

    // Native SQL Query
    @Query(value = "SELECT * FROM rooms WHERE status = :status",
           nativeQuery = true)
    List<Room> findRoomsByStatus(@Param("status") String status);

    // Find Rooms Available Between Check-In and Check-Out Dates
    @Query("""
        SELECT room
        FROM Room room
        WHERE room.status = 'AVAILABLE'
        AND NOT EXISTS (
            SELECT reservation
            FROM Reservation reservation
            WHERE reservation.room = room
            AND reservation.reservationStatus <> 
                com.demo.hotelreservation.enums.ReservationStatus.CANCELLED
            AND reservation.checkInDate < :checkOutDate
            AND reservation.checkOutDate > :checkInDate
        )
        """)
    List<Room> findAvailableRooms(
            @Param("checkInDate") LocalDate checkInDate,
            @Param("checkOutDate") LocalDate checkOutDate);
}
