package com.demo.hotelreservation.dto.response;

import java.util.List;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RoomResponseDTO {

    private Long roomId;

    private String roomNumber;

    private Integer floorNumber;

    private Integer capacity;

    private String status;

    private String description;

    private Long roomTypeId;

    private List<Long> amenityIds;
}
