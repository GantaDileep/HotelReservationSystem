package com.demo.hotelreservation.dto.request;

import java.util.List;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RoomRequestDTO {

    @NotBlank(message = "Room number is required")
    @Size(max = 20, message = "Room number cannot exceed 20 characters")
    private String roomNumber;

    @NotNull(message = "Floor number is required")
    private Integer floorNumber;

    @NotNull(message = "Capacity is required")
    @Min(value = 1, message = "Capacity must be at least 1")
    private Integer capacity;

    @NotBlank(message = "Room status is required")
    @Size(max = 20, message = "Status cannot exceed 20 characters")
    private String status;

    @Size(max = 500, message = "Description cannot exceed 500 characters")
    private String description;

    @NotNull(message = "Room type ID is required")
    private Long roomTypeId;

    private List<Long> amenityIds;
}
