package com.dashotel.hotelmanagement.mapper;

import com.dashotel.hotelmanagement.dto.request.service.HotelFacilityRequest;
import com.dashotel.hotelmanagement.dto.response.hotel.FacilitiesResponse;
import com.dashotel.hotelmanagement.dto.response.hotel.HotelDestailResponse;
import com.dashotel.hotelmanagement.entity.service.HotelFacilityEntity;
import org.mapstruct.Mapper;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface HotelFacilityMapper {
    HotelFacilityEntity toEntity (HotelFacilityRequest request);
    FacilitiesResponse toResponse (HotelFacilityEntity entity);
}
