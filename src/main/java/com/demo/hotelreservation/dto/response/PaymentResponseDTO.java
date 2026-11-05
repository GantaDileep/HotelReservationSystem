package com.demo.hotelreservation.dto.response;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.demo.hotelreservation.enums.PaymentMethod;
import com.demo.hotelreservation.enums.PaymentStatus;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PaymentResponseDTO {

    private Long paymentId;

    private BigDecimal amount;

    private PaymentMethod paymentMethod;

    private PaymentStatus paymentStatus;

    private String transactionReference;

    private LocalDateTime paymentDate;

    private Long reservationId;
}
