package com.demo.hotelreservation.dto.response;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

import com.demo.hotelreservation.enums.ReservationStatus;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ReservationResponseDTO {

    private Long reservationId;

    private String bookingReference;

    private Long customerId;

    private Long roomId;

    private LocalDate checkInDate;

    private LocalDate checkOutDate;

    private Integer numberOfGuests;

    private Integer numberOfNights;

    private BigDecimal pricePerNight;

    private BigDecimal totalAmount;

    private ReservationStatus reservationStatus;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;
}
