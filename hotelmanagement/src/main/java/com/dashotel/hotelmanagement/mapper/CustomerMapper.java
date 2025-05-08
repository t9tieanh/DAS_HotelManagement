package com.dashotel.hotelmanagement.mapper;

import com.dashotel.hotelmanagement.dto.request.common.CustomerRequestDTO;
import com.dashotel.hotelmanagement.dto.response.common.CustomerResponseDTO;
import com.dashotel.hotelmanagement.entity.user.CustomerEntity;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface CustomerMapper {
    CustomerEntity toEntity(CustomerRequestDTO request);
    CustomerResponseDTO toDTO(CustomerEntity entity);
    void updateProfile(CustomerRequestDTO dto, @MappingTarget CustomerEntity entity);
}
