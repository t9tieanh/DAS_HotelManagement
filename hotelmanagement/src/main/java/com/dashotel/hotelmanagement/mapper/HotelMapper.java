package com.dashotel.hotelmanagement.mapper;

import com.dashotel.hotelmanagement.dto.request.hotel.HotelCreationRequest;
import com.dashotel.hotelmanagement.dto.response.hotel.HotelDestailResponse;
import com.dashotel.hotelmanagement.entity.hotel.HotelEntity;
import org.mapstruct.Mapper;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface HotelMapper {
    HotelEntity toEntity(HotelCreationRequest hotel);
    HotelDestailResponse toResponse(HotelEntity hotel);
}
