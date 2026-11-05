package com.demo.hotelreservation.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.demo.hotelreservation.dto.request.CustomerRequestDTO;
import com.demo.hotelreservation.dto.response.CustomerResponseDTO;

public interface CustomerService {

    CustomerResponseDTO createCustomer(CustomerRequestDTO dto);

    CustomerResponseDTO getCustomerById(Long customerId);

    Page<CustomerResponseDTO> getAllCustomers(Pageable pageable);

    CustomerResponseDTO updateCustomer(Long customerId, CustomerRequestDTO dto);

    void deleteCustomer(Long customerId);
}