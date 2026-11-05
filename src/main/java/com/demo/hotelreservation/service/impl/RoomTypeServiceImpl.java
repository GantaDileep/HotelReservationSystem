package com.demo.hotelreservation.service.impl;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.demo.hotelreservation.dto.request.RoomTypeRequestDTO;
import com.demo.hotelreservation.dto.response.RoomTypeResponseDTO;
import com.demo.hotelreservation.entity.RoomType;
import com.demo.hotelreservation.exception.RoomTypeNotFoundException;
import com.demo.hotelreservation.mapper.RoomTypeMapper;
import com.demo.hotelreservation.repository.RoomTypeRepository;
import com.demo.hotelreservation.service.RoomTypeService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class RoomTypeServiceImpl implements RoomTypeService {

    private final RoomTypeRepository roomTypeRepository;

    private final RoomTypeMapper roomTypeMapper;

    // Create Room Type
    @Override
    public RoomTypeResponseDTO createRoomType(RoomTypeRequestDTO dto) {

        RoomType roomType = roomTypeMapper.toEntity(dto);

        RoomType savedRoomType = roomTypeRepository.save(roomType);

        return roomTypeMapper.toResponse(savedRoomType);
    }

    // Get Room Type By ID
    @Override
    @Transactional(readOnly = true)
    public RoomTypeResponseDTO getRoomTypeById(Long roomTypeId) {

        RoomType roomType = roomTypeRepository.findById(roomTypeId)
                .orElseThrow(() -> new RoomTypeNotFoundException(
                        "Room type not found with ID: " + roomTypeId));

        return roomTypeMapper.toResponse(roomType);
    }

    // Get All Room Types With Pagination
    @Override
    @Transactional(readOnly = true)
    public Page<RoomTypeResponseDTO> getAllRoomTypes(Pageable pageable) {

        Page<RoomType> roomTypes = roomTypeRepository.findAll(pageable);

        return roomTypes.map(roomTypeMapper::toResponse);
    }

    // Update Room Type
    @Override
    public RoomTypeResponseDTO updateRoomType(
            Long roomTypeId, RoomTypeRequestDTO dto) {

        RoomType roomType = roomTypeRepository.findById(roomTypeId)
                .orElseThrow(() -> new RoomTypeNotFoundException(
                        "Room type not found with ID: " + roomTypeId));

        roomTypeMapper.toUpdate(dto, roomType);

        RoomType updatedRoomType = roomTypeRepository.save(roomType);

        return roomTypeMapper.toResponse(updatedRoomType);
    }

    // Delete Room Type
    @Override
    public void deleteRoomType(Long roomTypeId) {

        RoomType roomType = roomTypeRepository.findById(roomTypeId)
                .orElseThrow(() -> new RoomTypeNotFoundException(
                        "Room type not found with ID: " + roomTypeId));

        roomTypeRepository.delete(roomType);
    }
}
