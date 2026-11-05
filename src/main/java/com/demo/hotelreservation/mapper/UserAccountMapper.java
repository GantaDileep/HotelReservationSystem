package com.demo.hotelreservation.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import com.demo.hotelreservation.dto.request.UserAccountRequestDTO;
import com.demo.hotelreservation.dto.response.UserAccountResponseDTO;
import com.demo.hotelreservation.entity.UserAccount;

@Mapper(componentModel = "spring")
public interface UserAccountMapper {

    @Mapping(target = "accountId", ignore = true)
    @Mapping(target = "customer", ignore = true)
    @Mapping(target = "password", ignore = true)
    UserAccount toEntity(UserAccountRequestDTO dto);

    @Mapping(target = "userAccountId", source = "accountId")
    @Mapping(target = "customerId", source = "customer.customerId")
    UserAccountResponseDTO toResponse(UserAccount userAccount);

    @Mapping(target = "accountId", ignore = true)
    @Mapping(target = "customer", ignore = true)
    @Mapping(target = "password", ignore = true)
    void toUpdate(
            UserAccountRequestDTO dto,
            @MappingTarget UserAccount userAccount);
}