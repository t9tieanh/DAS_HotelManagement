package com.dashotel.hotelmanagement.mapper;

import com.dashotel.hotelmanagement.dto.common.AddressDTO;
import com.dashotel.hotelmanagement.entity.hotel.AddressEntity;
import org.mapstruct.Mapper;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface AddressMapper {
    AddressEntity toEntity (AddressDTO address);
    AddressDTO toDTO (AddressEntity entity);

    default AddressDTO fromRawAddress(String fullAddress) {
        if (fullAddress == null || fullAddress.trim().isEmpty()) {
            return null;
        }

        String[] parts = fullAddress.split(",\\s*");
        String district = parts.length > 0 ? parts[0].trim() : null;
        String city = parts.length > 1 ? parts[1].trim() : null;

        return AddressDTO.builder()
                .district(district)
                .city(city)
                .build();
    }
}
