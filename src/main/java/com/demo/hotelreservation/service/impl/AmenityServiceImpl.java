package com.demo.hotelreservation.service.impl;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import lombok.RequiredArgsConstructor;

import com.demo.hotelreservation.dto.request.AmenityRequestDTO;
import com.demo.hotelreservation.dto.response.AmenityResponseDTO;
import com.demo.hotelreservation.entity.Amenity;
import com.demo.hotelreservation.mapper.AmenityMapper;
import com.demo.hotelreservation.repository.AmenityRepository;
import com.demo.hotelreservation.service.AmenityService;

@Service
@RequiredArgsConstructor
public class AmenityServiceImpl implements AmenityService {

    private final AmenityRepository amenityRepository;
    private final AmenityMapper amenityMapper;

    @Override
    public AmenityResponseDTO createAmenity(AmenityRequestDTO dto) {

        Amenity amenity = amenityMapper.toEntity(dto);

        Amenity savedAmenity = amenityRepository.save(amenity);

        return amenityMapper.toResponse(savedAmenity);
    }

    @Override
    public AmenityResponseDTO getAmenityById(Long amenityId) {

        Amenity amenity = amenityRepository.findById(amenityId)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Amenity not found with ID: " + amenityId));

        return amenityMapper.toResponse(amenity);
    }

    @Override
    public List<AmenityResponseDTO> getAllAmenities() {

        return amenityRepository.findAll()
                .stream()
                .map(amenityMapper::toResponse)
                .toList();
    }

    @Override
    public AmenityResponseDTO updateAmenity(
            Long amenityId, AmenityRequestDTO dto) {

        Amenity amenity = amenityRepository.findById(amenityId)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Amenity not found with ID: " + amenityId));

        amenityMapper.toUpdate(dto, amenity);

        Amenity updatedAmenity = amenityRepository.save(amenity);

        return amenityMapper.toResponse(updatedAmenity);
    }

    @Override
    public void deleteAmenity(Long amenityId) {

        Amenity amenity = amenityRepository.findById(amenityId)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Amenity not found with ID: " + amenityId));

        amenityRepository.delete(amenity);
    }
}
