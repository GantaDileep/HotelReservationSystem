package com.demo.hotelreservation.dto.response;

import java.math.BigDecimal;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RoomTypeResponseDTO {

    private Long roomTypeId;

    private String typeName;

    private String description;

    private BigDecimal pricePerNight;

    private Integer maximumOccupancy;
}
