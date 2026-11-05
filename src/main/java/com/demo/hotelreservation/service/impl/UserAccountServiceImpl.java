package com.demo.hotelreservation.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.demo.hotelreservation.dto.request.UserAccountRequestDTO;
import com.demo.hotelreservation.dto.response.UserAccountResponseDTO;
import com.demo.hotelreservation.entity.Customer;
import com.demo.hotelreservation.entity.UserAccount;
import com.demo.hotelreservation.exception.CustomerNotFoundException;
import com.demo.hotelreservation.exception.UserAccountNotFoundException;
import com.demo.hotelreservation.mapper.UserAccountMapper;
import com.demo.hotelreservation.repository.CustomerRepository;
import com.demo.hotelreservation.repository.UserAccountRepository;
import com.demo.hotelreservation.service.UserAccountService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class UserAccountServiceImpl implements UserAccountService {

    private final UserAccountRepository userAccountRepository;
    private final CustomerRepository customerRepository;
    private final UserAccountMapper userAccountMapper;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserAccountResponseDTO createUserAccount(UserAccountRequestDTO dto) {

        UserAccount userAccount = userAccountMapper.toEntity(dto);
        
        userAccount.setPassword(
                passwordEncoder.encode(dto.getPassword()));

        if (dto.getCustomerId() != null) {
            Customer customer = customerRepository.findById(dto.getCustomerId())
                    .orElseThrow(() -> new CustomerNotFoundException(
                            "Customer not found with ID: " + dto.getCustomerId()));

            userAccount.setCustomer(customer);
        }

        UserAccount savedAccount = userAccountRepository.save(userAccount);

        return userAccountMapper.toResponse(savedAccount);
    }

    @Override
    @Transactional(readOnly = true)
    public List<UserAccountResponseDTO> getAllUserAccounts() {

        return userAccountRepository.findAll()
                .stream()
                .map(userAccountMapper::toResponse)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public UserAccountResponseDTO getUserAccountById(Long accountId) {

        UserAccount userAccount = userAccountRepository.findById(accountId)
                .orElseThrow(() -> new UserAccountNotFoundException(
                        "User account not found with ID: " + accountId));

        return userAccountMapper.toResponse(userAccount);
    }

    @Override
    public UserAccountResponseDTO updateUserAccount(
            Long accountId, UserAccountRequestDTO dto) {

        UserAccount existingAccount = userAccountRepository.findById(accountId)
                .orElseThrow(() -> new UserAccountNotFoundException(
                        "User account not found with ID: " + accountId));

        userAccountMapper.toUpdate(dto, existingAccount);

        if (dto.getCustomerId() != null) {
            Customer customer = customerRepository.findById(dto.getCustomerId())
                    .orElseThrow(() -> new CustomerNotFoundException(
                            "Customer not found with ID: " + dto.getCustomerId()));

            existingAccount.setCustomer(customer);
        }

        UserAccount updatedAccount = userAccountRepository.save(existingAccount);

        return userAccountMapper.toResponse(updatedAccount);
    }

    @Override
    public void deleteUserAccount(Long accountId) {

        UserAccount userAccount = userAccountRepository.findById(accountId)
                .orElseThrow(() -> new UserAccountNotFoundException(
                        "User account not found with ID: " + accountId));

        userAccountRepository.delete(userAccount);
    }
}
