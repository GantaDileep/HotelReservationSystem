package com.demo.hotelreservation.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import com.demo.hotelreservation.dto.request.CustomerRequestDTO;
import com.demo.hotelreservation.dto.response.CustomerResponseDTO;
import com.demo.hotelreservation.entity.Customer;

@Mapper(componentModel = "spring")
public interface CustomerMapper {

    @Mapping(target = "customerId", ignore = true)
    @Mapping(target = "reservations", ignore = true)
    @Mapping(target = "userAccount", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    Customer toEntity(CustomerRequestDTO dto);

    CustomerResponseDTO toResponse(Customer customer);

    @Mapping(target = "customerId", ignore = true)
    @Mapping(target = "reservations", ignore = true)
    @Mapping(target = "userAccount", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    void toUpdate(CustomerRequestDTO dto, @MappingTarget Customer customer);
}
