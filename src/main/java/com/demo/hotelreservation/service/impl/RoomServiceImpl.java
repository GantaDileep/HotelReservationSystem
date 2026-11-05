package com.demo.hotelreservation.service.impl;

import java.util.List;
import java.time.LocalDate;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.CacheEvict;

import com.demo.hotelreservation.dto.request.RoomRequestDTO;
import com.demo.hotelreservation.dto.response.RoomResponseDTO;
import com.demo.hotelreservation.entity.Amenity;
import com.demo.hotelreservation.entity.Room;
import com.demo.hotelreservation.entity.RoomType;
import com.demo.hotelreservation.exception.AmenityNotFoundException;
import com.demo.hotelreservation.exception.RoomNotFoundException;
import com.demo.hotelreservation.exception.RoomTypeNotFoundException;
import com.demo.hotelreservation.mapper.RoomMapper;
import com.demo.hotelreservation.repository.AmenityRepository;
import com.demo.hotelreservation.repository.RoomRepository;
import com.demo.hotelreservation.repository.RoomTypeRepository;
import com.demo.hotelreservation.service.RoomService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class RoomServiceImpl implements RoomService {

    private final RoomRepository roomRepository;

    private final RoomTypeRepository roomTypeRepository;

    private final AmenityRepository amenityRepository;

    private final RoomMapper roomMapper;
    

    // Create Room
    @Override
    @CacheEvict(value = "rooms", allEntries = true)
    public RoomResponseDTO createRoom(RoomRequestDTO dto) {

        Room room = roomMapper.toEntity(dto);

        RoomType roomType = roomTypeRepository.findById(dto.getRoomTypeId())
                .orElseThrow(() -> new RoomTypeNotFoundException(
                        "Room type not found with ID: " + dto.getRoomTypeId()));

        room.setRoomType(roomType);

        if (dto.getAmenityIds() != null) {

            List<Amenity> amenities =
                    amenityRepository.findAllById(dto.getAmenityIds());

            if (amenities.size() != dto.getAmenityIds().stream().distinct().count()) {
                throw new AmenityNotFoundException(
                        "One or more amenities were not found");
            }

            room.setAmenities(amenities);
        }

        Room savedRoom = roomRepository.save(room);

        return roomMapper.toResponse(savedRoom);
    }

    // Get Room By ID
    @Override
    @Transactional(readOnly = true)
    @Cacheable(value = "rooms", key = "#roomId")
    public RoomResponseDTO getRoomById(Long roomId) {
    	
    	Room room = roomRepository.findById(roomId)
                .orElseThrow(() -> new RoomNotFoundException(
                        "Room not found with ID: " + roomId));
    	
    	return roomMapper.toResponse(room);
    	
    }	


    // Get All Rooms With Pagination
    @Override
    @Transactional(readOnly = true)
    public Page<RoomResponseDTO> getAllRooms(Pageable pageable) {

        Page<Room> rooms = roomRepository.findAll(pageable);

        return rooms.map(roomMapper::toResponse);
    }

    // Update Room
    @Override
    @CacheEvict(value = "rooms", allEntries = true)
    public RoomResponseDTO updateRoom(Long roomId, RoomRequestDTO dto) {

        Room room = roomRepository.findById(roomId)
                .orElseThrow(() -> new RoomNotFoundException(
                        "Room not found with ID: " + roomId));

        roomMapper.toUpdate(dto, room);

        RoomType roomType = roomTypeRepository.findById(dto.getRoomTypeId())
                .orElseThrow(() -> new RoomTypeNotFoundException(
                        "Room type not found with ID: " + dto.getRoomTypeId()));

        room.setRoomType(roomType);

        if (dto.getAmenityIds() != null) {

            List<Amenity> amenities =
                    amenityRepository.findAllById(dto.getAmenityIds());

            if (amenities.size() != dto.getAmenityIds().stream().distinct().count()) {
                throw new AmenityNotFoundException(
                        "One or more amenities were not found");
            }

            room.setAmenities(amenities);
        }

        Room updatedRoom = roomRepository.save(room);

        return roomMapper.toResponse(updatedRoom);
    }

    // Delete Room
    @Override
    @CacheEvict(value = "rooms", allEntries = true)
    public void deleteRoom(Long roomId) {

        Room room = roomRepository.findById(roomId)
                .orElseThrow(() -> new RoomNotFoundException(
                        "Room not found with ID: " + roomId));

        roomRepository.delete(room);
    }

    // Get Rooms By Status
    @Override
    @Transactional(readOnly = true)
    public List<RoomResponseDTO> getRoomsByStatus(String status) {

        List<Room> rooms = roomRepository.findRoomsByStatus(status);

        return rooms.stream()
                .map(roomMapper::toResponse)
                .toList();
    }
    

    // Get Available Rooms
    @Override
    @Transactional(readOnly = true)
    public List<RoomResponseDTO> getAvailableRooms(
            LocalDate checkInDate,
            LocalDate checkOutDate) {

        if (checkInDate == null || checkOutDate == null) {
            throw new IllegalArgumentException(
                    "Check-in and check-out dates are required");
        }

        if (!checkOutDate.isAfter(checkInDate)) {
            throw new IllegalArgumentException(
                    "Check-out date must be after check-in date");
        }

        List<Room> availableRooms =
                roomRepository.findAvailableRooms(
                        checkInDate,
                        checkOutDate);

        return availableRooms.stream()
                .map(roomMapper::toResponse)
                .toList();
    }

}