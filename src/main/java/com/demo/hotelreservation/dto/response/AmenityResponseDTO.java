package com.demo.hotelreservation.dto.response;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AmenityResponseDTO {

    private Long amenityId;

    private String amenityName;

    private String description;
}
