package com.demo.hotelreservation.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import com.demo.hotelreservation.dto.request.ReservationRequestDTO;
import com.demo.hotelreservation.dto.response.ReservationResponseDTO;
import com.demo.hotelreservation.entity.Reservation;

@Mapper(componentModel = "spring")
public interface ReservationMapper {

    @Mapping(target = "reservationId", ignore = true)
    @Mapping(target = "bookingReference", ignore = true)
    @Mapping(target = "customer", ignore = true)
    @Mapping(target = "room", ignore = true)
    @Mapping(target = "numberOfNights", ignore = true)
    @Mapping(target = "pricePerNight", ignore = true)
    @Mapping(target = "totalAmount", ignore = true)
    @Mapping(target = "reservationStatus", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "payments", ignore = true)
    Reservation toEntity(ReservationRequestDTO dto);

    @Mapping(target = "customerId", source = "customer.customerId")
    @Mapping(target = "roomId", source = "room.roomId")
    ReservationResponseDTO toResponse(Reservation reservation);

    @Mapping(target = "reservationId", ignore = true)
    @Mapping(target = "bookingReference", ignore = true)
    @Mapping(target = "customer", ignore = true)
    @Mapping(target = "room", ignore = true)
    @Mapping(target = "numberOfNights", ignore = true)
    @Mapping(target = "pricePerNight", ignore = true)
    @Mapping(target = "totalAmount", ignore = true)
    @Mapping(target = "reservationStatus", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "payments", ignore = true)
    void toUpdate(ReservationRequestDTO dto,
                  @MappingTarget Reservation reservation);
}
