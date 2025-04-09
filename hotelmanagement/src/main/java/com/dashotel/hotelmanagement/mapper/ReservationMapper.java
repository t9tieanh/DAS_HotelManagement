package com.dashotel.hotelmanagement.mapper;

import com.dashotel.hotelmanagement.dto.request.reservation.initial.ReservationRequest;
import com.dashotel.hotelmanagement.dto.response.reservation.common.ReservationResponse;
import com.dashotel.hotelmanagement.entity.booking.ReservationEntity;
import org.mapstruct.Mapper;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface ReservationMapper {
    ReservationEntity toEntity(ReservationRequest request);
    ReservationResponse toResponse(ReservationEntity entity);
}
