package com.demo.hotelreservation.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.demo.hotelreservation.dto.request.RoomTypeRequestDTO;
import com.demo.hotelreservation.dto.response.RoomTypeResponseDTO;

public interface RoomTypeService {

    RoomTypeResponseDTO createRoomType(RoomTypeRequestDTO dto);

    RoomTypeResponseDTO getRoomTypeById(Long roomTypeId);

    Page<RoomTypeResponseDTO> getAllRoomTypes(Pageable pageable);

    RoomTypeResponseDTO updateRoomType(Long roomTypeId, RoomTypeRequestDTO dto);

    void deleteRoomType(Long roomTypeId);
}
