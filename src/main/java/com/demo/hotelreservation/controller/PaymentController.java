package com.demo.hotelreservation.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.demo.hotelreservation.dto.request.PaymentRequestDTO;
import com.demo.hotelreservation.dto.response.PaymentResponseDTO;
import com.demo.hotelreservation.enums.PaymentStatus;
import com.demo.hotelreservation.service.PaymentService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/payments")
public class PaymentController {

    private final PaymentService paymentService;

    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    // 1. Create Payment
    @PostMapping
    public ResponseEntity<PaymentResponseDTO> createPayment(
            @Valid @RequestBody PaymentRequestDTO dto) {

        return new ResponseEntity<>(
                paymentService.createPayment(dto),
                HttpStatus.CREATED);
    }

    // 2. Get Payment By ID
    @GetMapping("/{paymentId}")
    public ResponseEntity<PaymentResponseDTO> getPaymentById(
            @PathVariable Long paymentId) {

        return ResponseEntity.ok(
                paymentService.getPaymentById(paymentId));
    }

    // 3. Get All Payments with Pagination
    @GetMapping
    public ResponseEntity<Page<PaymentResponseDTO>> getAllPayments(
            Pageable pageable) {

        return ResponseEntity.ok(
                paymentService.getAllPayments(pageable));
    }

    // 4. Get Payments By Status
    @GetMapping("/status/{status}")
    public ResponseEntity<?> getPaymentsByStatus(
            @PathVariable PaymentStatus status) {

        return ResponseEntity.ok(
                paymentService.getPaymentsByStatus(status));
    }

    // 5. Update Payment
    @PutMapping("/{paymentId}")
    public ResponseEntity<PaymentResponseDTO> updatePayment(
            @PathVariable Long paymentId,
            @Valid @RequestBody PaymentRequestDTO dto) {

        return ResponseEntity.ok(
                paymentService.updatePayment(paymentId, dto));
    }

    // 6. Delete Payment
    @DeleteMapping("/{paymentId}")
    public ResponseEntity<Void> deletePayment(
            @PathVariable Long paymentId) {

        paymentService.deletePayment(paymentId);

        return ResponseEntity.noContent().build();
    }
}