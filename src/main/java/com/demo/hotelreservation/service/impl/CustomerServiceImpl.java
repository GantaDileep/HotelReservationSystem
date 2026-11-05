package com.demo.hotelreservation.service.impl;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.demo.hotelreservation.dto.request.CustomerRequestDTO;
import com.demo.hotelreservation.dto.response.CustomerResponseDTO;
import com.demo.hotelreservation.entity.Customer;
import com.demo.hotelreservation.mapper.CustomerMapper;
import com.demo.hotelreservation.repository.CustomerRepository;
import com.demo.hotelreservation.service.CustomerService;

@Service
@Transactional
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;
    private final CustomerMapper customerMapper;

    public CustomerServiceImpl(
            CustomerRepository customerRepository,
            CustomerMapper customerMapper) {

        this.customerRepository = customerRepository;
        this.customerMapper = customerMapper;
    }

    // 1. Create Customer
    @Override
    public CustomerResponseDTO createCustomer(CustomerRequestDTO dto) {

        customerRepository.findByEmail(dto.getEmail())
                .ifPresent(customer -> {
                    throw new RuntimeException("Customer email already exists");
                });

        Customer customer = customerMapper.toEntity(dto);

        Customer savedCustomer = customerRepository.save(customer);

        return customerMapper.toResponse(savedCustomer);
    }

    // 2. Get Customer By ID
    @Override
    @Transactional(readOnly = true)
    public CustomerResponseDTO getCustomerById(Long customerId) {

        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() ->
                        new RuntimeException("Customer not found"));

        return customerMapper.toResponse(customer);
    }

    // 3. Get All Customers with Pagination
    @Override
    @Transactional(readOnly = true)
    public Page<CustomerResponseDTO> getAllCustomers(Pageable pageable) {

        return customerRepository.findAll(pageable)
                .map(customerMapper::toResponse);
    }

    // 4. Update Customer
    @Override
    public CustomerResponseDTO updateCustomer(
            Long customerId, CustomerRequestDTO dto) {

        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() ->
                        new RuntimeException("Customer not found"));

        customerRepository.findByEmail(dto.getEmail())
                .ifPresent(existingCustomer -> {
                    if (!existingCustomer.getCustomerId().equals(customerId)) {
                        throw new RuntimeException("Email already belongs to another customer");
                    }
                });

        customerMapper.toUpdate(dto, customer);

        Customer updatedCustomer = customerRepository.save(customer);

        return customerMapper.toResponse(updatedCustomer);
    }

    // 5. Delete Customer
    @Override
    public void deleteCustomer(Long customerId) {

        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() ->
                        new RuntimeException("Customer not found"));

        if (!customer.getReservations().isEmpty()
                || customer.getUserAccount() != null) {

            throw new RuntimeException(
                    "Cannot delete customer with existing reservations or user account");
        }

        customerRepository.delete(customer);
    }
}