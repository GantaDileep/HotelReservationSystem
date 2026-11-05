package com.demo.hotelreservation.dto.response;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserAccountResponseDTO {

    private Long userAccountId;

    private String username;

    private String role;

    private Long customerId;
}
