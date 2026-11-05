package com.demo.hotelreservation.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.demo.hotelreservation.dto.request.RoomTypeRequestDTO;
import com.demo.hotelreservation.dto.response.RoomTypeResponseDTO;
import com.demo.hotelreservation.service.RoomTypeService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/room-types")
@RequiredArgsConstructor
public class RoomTypeController {

    private final RoomTypeService roomTypeService;

    // Create Room Type
    @PostMapping
    public ResponseEntity<RoomTypeResponseDTO> createRoomType(
            @Valid @RequestBody RoomTypeRequestDTO dto) {

        RoomTypeResponseDTO response = roomTypeService.createRoomType(dto);

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    // Get Room Type By ID
    @GetMapping("/{roomTypeId}")
    public ResponseEntity<RoomTypeResponseDTO> getRoomTypeById(
            @PathVariable Long roomTypeId) {

        return ResponseEntity.ok(
                roomTypeService.getRoomTypeById(roomTypeId));
    }

    // Get All Room Types With Pagination
    @GetMapping
    public ResponseEntity<Page<RoomTypeResponseDTO>> getAllRoomTypes(
            Pageable pageable) {

        return ResponseEntity.ok(
                roomTypeService.getAllRoomTypes(pageable));
    }

    // Update Room Type
    @PutMapping("/{roomTypeId}")
    public ResponseEntity<RoomTypeResponseDTO> updateRoomType(
            @PathVariable Long roomTypeId,
            @Valid @RequestBody RoomTypeRequestDTO dto) {

        return ResponseEntity.ok(
                roomTypeService.updateRoomType(roomTypeId, dto));
    }

    // Delete Room Type
    @DeleteMapping("/{roomTypeId}")
    public ResponseEntity<Void> deleteRoomType(
            @PathVariable Long roomTypeId) {

        roomTypeService.deleteRoomType(roomTypeId);

        return ResponseEntity.noContent().build();
    }
}
