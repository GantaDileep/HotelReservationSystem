package com.demo.hotelreservation.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import com.demo.hotelreservation.dto.request.RoomRequestDTO;
import com.demo.hotelreservation.dto.response.RoomResponseDTO;
import com.demo.hotelreservation.entity.Room;

@Mapper(componentModel = "spring")
public interface RoomMapper {

    @Mapping(target = "roomId", ignore = true)
    @Mapping(target = "roomType", ignore = true)
    @Mapping(target = "amenities", ignore = true)
    @Mapping(target = "reservations", ignore = true)
    Room toEntity(RoomRequestDTO dto);

    @Mapping(target = "roomTypeId",
             source = "roomType.roomTypeId")
    @Mapping(target = "amenityIds",
             expression = "java(room.getAmenities().stream().map(amenity -> amenity.getAmenityId()).toList())")
    RoomResponseDTO toResponse(Room room);

    @Mapping(target = "roomId", ignore = true)
    @Mapping(target = "roomType", ignore = true)
    @Mapping(target = "amenities", ignore = true)
    @Mapping(target = "reservations", ignore = true)
    void toUpdate(RoomRequestDTO dto, @MappingTarget Room room);
}
