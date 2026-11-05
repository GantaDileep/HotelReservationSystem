package com.demo.hotelreservation.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import com.demo.hotelreservation.dto.request.AmenityRequestDTO;
import com.demo.hotelreservation.dto.response.AmenityResponseDTO;
import com.demo.hotelreservation.service.AmenityService;

@RestController
@RequestMapping("/api/amenities")
@RequiredArgsConstructor
public class AmenityController {

    private final AmenityService amenityService;

    @PostMapping
    public ResponseEntity<AmenityResponseDTO> createAmenity(
            @Valid @RequestBody AmenityRequestDTO dto) {

        AmenityResponseDTO response =
                amenityService.createAmenity(dto);

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping("/{amenityId}")
    public ResponseEntity<AmenityResponseDTO> getAmenityById(
            @PathVariable Long amenityId) {

        AmenityResponseDTO response =
                amenityService.getAmenityById(amenityId);

        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<List<AmenityResponseDTO>> getAllAmenities() {

        List<AmenityResponseDTO> response =
                amenityService.getAllAmenities();

        return ResponseEntity.ok(response);
    }

    @PutMapping("/{amenityId}")
    public ResponseEntity<AmenityResponseDTO> updateAmenity(
            @PathVariable Long amenityId,
            @Valid @RequestBody AmenityRequestDTO dto) {

        AmenityResponseDTO response =
                amenityService.updateAmenity(amenityId, dto);

        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{amenityId}")
    public ResponseEntity<Void> deleteAmenity(
            @PathVariable Long amenityId) {

        amenityService.deleteAmenity(amenityId);

        return ResponseEntity.noContent().build();
    }
}
