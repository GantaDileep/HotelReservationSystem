package com.demo.hotelreservation.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.demo.hotelreservation.dto.request.RoomRequestDTO;
import com.demo.hotelreservation.dto.response.RoomResponseDTO;

public interface RoomService {

    RoomResponseDTO createRoom(RoomRequestDTO dto);

    RoomResponseDTO getRoomById(Long roomId);

    Page<RoomResponseDTO> getAllRooms(Pageable pageable);

    RoomResponseDTO updateRoom(Long roomId, RoomRequestDTO dto);

    void deleteRoom(Long roomId);

    List<RoomResponseDTO> getRoomsByStatus(String status);

    // Get Available Rooms
    List<RoomResponseDTO> getAvailableRooms(
            LocalDate checkInDate,
            LocalDate checkOutDate);
}
