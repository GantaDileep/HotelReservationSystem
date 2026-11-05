package com.demo.hotelreservation.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.demo.hotelreservation.dto.request.CustomerRequestDTO;
import com.demo.hotelreservation.dto.response.CustomerResponseDTO;
import com.demo.hotelreservation.service.CustomerService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/customers")
public class CustomerController {

    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    // 1. Create Customer
    @PostMapping
    public ResponseEntity<CustomerResponseDTO> createCustomer(
            @Valid @RequestBody CustomerRequestDTO dto) {

        return new ResponseEntity<>(
                customerService.createCustomer(dto),
                HttpStatus.CREATED);
    }

    // 2. Get Customer By ID
    @GetMapping("/{customerId}")
    public ResponseEntity<CustomerResponseDTO> getCustomerById(
            @PathVariable Long customerId) {

        return ResponseEntity.ok(
                customerService.getCustomerById(customerId));
    }

    // 3. Get All Customers with Pagination
    @GetMapping
    public ResponseEntity<Page<CustomerResponseDTO>> getAllCustomers(
            Pageable pageable) {

        return ResponseEntity.ok(
                customerService.getAllCustomers(pageable));
    }

    // 4. Update Customer
    @PutMapping("/{customerId}")
    public ResponseEntity<CustomerResponseDTO> updateCustomer(
            @PathVariable Long customerId,
            @Valid @RequestBody CustomerRequestDTO dto) {

        return ResponseEntity.ok(
                customerService.updateCustomer(customerId, dto));
    }

    // 5. Delete Customer
    @DeleteMapping("/{customerId}")
    public ResponseEntity<Void> deleteCustomer(
            @PathVariable Long customerId) {

        customerService.deleteCustomer(customerId);

        return ResponseEntity.noContent().build();
    }
}
