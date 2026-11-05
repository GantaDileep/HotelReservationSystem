package com.demo.hotelreservation.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import com.demo.hotelreservation.dto.request.RoomTypeRequestDTO;
import com.demo.hotelreservation.dto.response.RoomTypeResponseDTO;
import com.demo.hotelreservation.entity.RoomType;

@Mapper(componentModel = "spring")
public interface RoomTypeMapper {

    @Mapping(target = "roomTypeId", ignore = true)
    @Mapping(target = "rooms", ignore = true)
    RoomType toEntity(RoomTypeRequestDTO dto);

    RoomTypeResponseDTO toResponse(RoomType roomType);

    @Mapping(target = "roomTypeId", ignore = true)
    @Mapping(target = "rooms", ignore = true)
    void toUpdate(RoomTypeRequestDTO dto,
                  @MappingTarget RoomType roomType);
}
