package com.demo.hotelreservation.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import com.demo.hotelreservation.dto.request.PaymentRequestDTO;
import com.demo.hotelreservation.dto.response.PaymentResponseDTO;
import com.demo.hotelreservation.entity.Payment;

@Mapper(componentModel = "spring")
public interface PaymentMapper {

    @Mapping(target = "reservation", ignore = true)
    @Mapping(target = "paymentId", ignore = true)
    @Mapping(target = "paymentStatus", ignore = true)
    @Mapping(target = "paymentDate", ignore = true)
    Payment toEntity(PaymentRequestDTO dto);

    @Mapping(target = "reservationId",
             source = "reservation.reservationId")
    PaymentResponseDTO toResponse(Payment payment);

    @Mapping(target = "paymentId", ignore = true)
    @Mapping(target = "reservation", ignore = true)
    @Mapping(target = "paymentDate", ignore = true)
    @Mapping(target = "paymentStatus", ignore = true)
    void toUpdate(PaymentRequestDTO dto, @MappingTarget Payment payment);
}
