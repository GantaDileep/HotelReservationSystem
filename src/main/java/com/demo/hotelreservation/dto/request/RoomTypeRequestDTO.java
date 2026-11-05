package com.demo.hotelreservation.dto.request;

import java.math.BigDecimal;

import jakarta.validation.constraints.DecimalMin;
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
public class RoomTypeRequestDTO {

    @NotBlank(message = "Room type name is required")
    @Size(max = 50, message = "Room type name cannot exceed 50 characters")
    private String typeName;

    @Size(max = 500, message = "Description cannot exceed 500 characters")
    private String description;

    @NotNull(message = "Price per night is required")
    @DecimalMin(value = "0.01", message = "Price must be greater than zero")
    private BigDecimal pricePerNight;

    @NotNull(message = "Maximum occupancy is required")
    @Min(value = 1, message = "Maximum occupancy must be at least 1")
    private Integer maximumOccupancy;
}
