package com.demo.hotelreservation.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AmenityRequestDTO {

    @NotBlank(message = "Amenity name is required")
    @Size(max = 100, message = "Amenity name cannot exceed 100 characters")
    private String amenityName;

    @Size(max = 255, message = "Description cannot exceed 255 characters")
    private String description;
}
