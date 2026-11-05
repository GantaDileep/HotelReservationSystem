package com.demo.hotelreservation.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.demo.hotelreservation.dto.request.UserAccountRequestDTO;
import com.demo.hotelreservation.dto.response.UserAccountResponseDTO;
import com.demo.hotelreservation.service.UserAccountService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/user-accounts")
@RequiredArgsConstructor
public class UserAccountController {

    private final UserAccountService userAccountService;

    @PostMapping
    public ResponseEntity<UserAccountResponseDTO> createUserAccount(
            @Valid @RequestBody UserAccountRequestDTO dto) {

        UserAccountResponseDTO response =
                userAccountService.createUserAccount(dto);

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<UserAccountResponseDTO>> getAllUserAccounts() {

        return ResponseEntity.ok(
                userAccountService.getAllUserAccounts());
    }

    @GetMapping("/{accountId}")
    public ResponseEntity<UserAccountResponseDTO> getUserAccountById(
            @PathVariable Long accountId) {

        return ResponseEntity.ok(
                userAccountService.getUserAccountById(accountId));
    }

    @PutMapping("/{accountId}")
    public ResponseEntity<UserAccountResponseDTO> updateUserAccount(
            @PathVariable Long accountId,
            @Valid @RequestBody UserAccountRequestDTO dto) {

        return ResponseEntity.ok(
                userAccountService.updateUserAccount(accountId, dto));
    }

    @DeleteMapping("/{accountId}")
    public ResponseEntity<Void> deleteUserAccount(
            @PathVariable Long accountId) {

        userAccountService.deleteUserAccount(accountId);

        return ResponseEntity.noContent().build();
    }
}
