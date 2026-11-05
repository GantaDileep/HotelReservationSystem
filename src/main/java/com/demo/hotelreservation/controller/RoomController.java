package com.demo.hotelreservation.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.demo.hotelreservation.dto.request.RoomRequestDTO;
import com.demo.hotelreservation.dto.response.RoomResponseDTO;
import com.demo.hotelreservation.service.RoomService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/rooms")
@RequiredArgsConstructor
public class RoomController {

    private final RoomService roomService;

    // Create Room
    @PostMapping
    public ResponseEntity<RoomResponseDTO> createRoom(
            @Valid @RequestBody RoomRequestDTO dto) {

        RoomResponseDTO response = roomService.createRoom(dto);

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    // Get Room By ID
    @GetMapping("/{roomId:\\d+}")
    public ResponseEntity<RoomResponseDTO> getRoomById(
            @PathVariable Long roomId) {

        return ResponseEntity.ok(
                roomService.getRoomById(roomId));
    }

    // Get All Rooms With Pagination
    @GetMapping
    public ResponseEntity<Page<RoomResponseDTO>> getAllRooms(
            Pageable pageable) {

        return ResponseEntity.ok(
                roomService.getAllRooms(pageable));
    }

    // Update Room
    @PutMapping("/{roomId:\\d+}")
    public ResponseEntity<RoomResponseDTO> updateRoom(
            @PathVariable Long roomId,
            @Valid @RequestBody RoomRequestDTO dto) {

        return ResponseEntity.ok(
                roomService.updateRoom(roomId, dto));
    }

    // Delete Room
    @DeleteMapping("/{roomId:\\d+}")
    public ResponseEntity<Void> deleteRoom(
            @PathVariable Long roomId) {

        roomService.deleteRoom(roomId);

        return ResponseEntity.noContent().build();
    }

    // Get Rooms By Status
    @GetMapping("/status/{status}")
    public ResponseEntity<List<RoomResponseDTO>> getRoomsByStatus(
            @PathVariable String status) {

        return ResponseEntity.ok(
                roomService.getRoomsByStatus(status));
    }

    // Get Available Rooms Between Dates
    @GetMapping("/availability")
    public ResponseEntity<List<RoomResponseDTO>> getAvailableRooms(
            @RequestParam
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
            LocalDate checkInDate,

            @RequestParam
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
            LocalDate checkOutDate) {

        return ResponseEntity.ok(
                roomService.getAvailableRooms(
                        checkInDate,
                        checkOutDate));
    }
}
