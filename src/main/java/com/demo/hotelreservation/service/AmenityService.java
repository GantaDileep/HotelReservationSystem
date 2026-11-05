package com.demo.hotelreservation.service;

import java.util.List;

import com.demo.hotelreservation.dto.request.AmenityRequestDTO;
import com.demo.hotelreservation.dto.response.AmenityResponseDTO;

public interface AmenityService {

    AmenityResponseDTO createAmenity(AmenityRequestDTO dto);

    AmenityResponseDTO getAmenityById(Long amenityId);

    List<AmenityResponseDTO> getAllAmenities();

    AmenityResponseDTO updateAmenity(Long amenityId, AmenityRequestDTO dto);

    void deleteAmenity(Long amenityId);
}
