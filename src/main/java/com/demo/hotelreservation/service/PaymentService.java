package com.demo.hotelreservation.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.demo.hotelreservation.dto.request.PaymentRequestDTO;
import com.demo.hotelreservation.dto.response.PaymentResponseDTO;
import com.demo.hotelreservation.enums.PaymentStatus;

public interface PaymentService {

    PaymentResponseDTO createPayment(PaymentRequestDTO dto);

    PaymentResponseDTO getPaymentById(Long paymentId);

    Page<PaymentResponseDTO> getAllPayments(Pageable pageable);

    List<PaymentResponseDTO> getPaymentsByStatus(PaymentStatus status);

    PaymentResponseDTO updatePayment(Long paymentId, PaymentRequestDTO dto);

    void deletePayment(Long paymentId);
}