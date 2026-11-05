package com.demo.hotelreservation.service.impl;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.demo.hotelreservation.dto.request.PaymentRequestDTO;
import com.demo.hotelreservation.dto.response.PaymentResponseDTO;
import com.demo.hotelreservation.entity.Payment;
import com.demo.hotelreservation.entity.Reservation;
import com.demo.hotelreservation.enums.PaymentStatus;
import com.demo.hotelreservation.mapper.PaymentMapper;
import com.demo.hotelreservation.repository.PaymentRepository;
import com.demo.hotelreservation.repository.ReservationRepository;
import com.demo.hotelreservation.service.PaymentService;
import com.demo.hotelreservation.exception.PaymentNotFoundException;
import com.demo.hotelreservation.exception.ReservationNotFoundException;

@Service
@Transactional
public class PaymentServiceImpl implements PaymentService {

    private final PaymentRepository paymentRepository;
    private final ReservationRepository reservationRepository;
    private final PaymentMapper paymentMapper;

    public PaymentServiceImpl(
            PaymentRepository paymentRepository,
            ReservationRepository reservationRepository,
            PaymentMapper paymentMapper) {

        this.paymentRepository = paymentRepository;
        this.reservationRepository = reservationRepository;
        this.paymentMapper = paymentMapper;
    }

    // 1. Create Payment
    @Override
    public PaymentResponseDTO createPayment(PaymentRequestDTO dto) {

        Reservation reservation = reservationRepository
                .findById(dto.getReservationId())
                .orElseThrow(() ->
                new ReservationNotFoundException("Reservation not found"));

        Payment payment = paymentMapper.toEntity(dto);

        payment.setReservation(reservation);
        payment.setPaymentStatus(PaymentStatus.PENDING);

        Payment savedPayment = paymentRepository.save(payment);

        return paymentMapper.toResponse(savedPayment);
    }

    // 2. Get Payment By ID
    @Override
    @Transactional(readOnly = true)
    public PaymentResponseDTO getPaymentById(Long paymentId) {

        Payment payment = paymentRepository.findById(paymentId)
                .orElseThrow(() ->              
                        new PaymentNotFoundException("Payment not found"));
        return paymentMapper.toResponse(payment);
    }

    // 3. Get All Payments with Pagination
    @Override
    @Transactional(readOnly = true)
    public Page<PaymentResponseDTO> getAllPayments(Pageable pageable) {

        return paymentRepository.findAll(pageable)
                .map(paymentMapper::toResponse);
    }

    // 4. Get Payments By Status
    @Override
    @Transactional(readOnly = true)
    public List<PaymentResponseDTO> getPaymentsByStatus(
            PaymentStatus status) {

        return paymentRepository.findByPaymentStatus(status)
                .stream()
                .map(paymentMapper::toResponse)
                .toList();
    }

    // 5. Update Payment
    @Override
    public PaymentResponseDTO updatePayment(
            Long paymentId, PaymentRequestDTO dto) {

        Payment payment = paymentRepository.findById(paymentId)
                .orElseThrow(() ->
                new PaymentNotFoundException("Payment not found"));

        Reservation reservation = reservationRepository
                .findById(dto.getReservationId())
                .orElseThrow(() ->
                new ReservationNotFoundException("Reservation not found"));

        paymentMapper.toUpdate(dto, payment);

        payment.setReservation(reservation);

        Payment updatedPayment = paymentRepository.save(payment);

        return paymentMapper.toResponse(updatedPayment);
    }

    // 6. Delete Payment
    @Override
    public void deletePayment(Long paymentId) {

        Payment payment = paymentRepository.findById(paymentId)
                .orElseThrow(() ->
                new PaymentNotFoundException("Payment not found"));

        paymentRepository.delete(payment);
    }
}