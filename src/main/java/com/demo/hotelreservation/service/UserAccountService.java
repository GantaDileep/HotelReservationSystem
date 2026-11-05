package com.demo.hotelreservation.service;

import java.util.List;

import com.demo.hotelreservation.dto.request.UserAccountRequestDTO;
import com.demo.hotelreservation.dto.response.UserAccountResponseDTO;

public interface UserAccountService {

    UserAccountResponseDTO createUserAccount(UserAccountRequestDTO dto);

    List<UserAccountResponseDTO> getAllUserAccounts();

    UserAccountResponseDTO getUserAccountById(Long accountId);

    UserAccountResponseDTO updateUserAccount(Long accountId, UserAccountRequestDTO dto);

    void deleteUserAccount(Long accountId);
}
