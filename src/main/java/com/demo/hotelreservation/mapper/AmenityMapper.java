package com.demo.hotelreservation.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import com.demo.hotelreservation.dto.request.AmenityRequestDTO;
import com.demo.hotelreservation.dto.response.AmenityResponseDTO;
import com.demo.hotelreservation.entity.Amenity;

@Mapper(componentModel = "spring")
public interface AmenityMapper {

    @Mapping(target = "amenityId", ignore = true)
    @Mapping(target = "rooms", ignore = true)
    Amenity toEntity(AmenityRequestDTO dto);

    AmenityResponseDTO toResponse(Amenity amenity);

    @Mapping(target = "amenityId", ignore = true)
    @Mapping(target = "rooms", ignore = true)
    void toUpdate(AmenityRequestDTO dto, @MappingTarget Amenity amenity);
}
