package com.dashotel.hotelmanagement.mapper;

import com.dashotel.hotelmanagement.dto.request.room.RoomAvailabilityRequest;
import com.dashotel.hotelmanagement.dto.request.room.RoomTypeRequest;
import com.dashotel.hotelmanagement.dto.response.room.RoomAvailabilityResponse;
import com.dashotel.hotelmanagement.entity.room.RoomAvailabilityEntity;
import com.dashotel.hotelmanagement.entity.room.RoomTypeEntity;
import org.mapstruct.Mapper;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface RoomAvailabilityMapper {
    RoomAvailabilityEntity toRoomAvailabilityEntity(RoomAvailabilityRequest request);
    RoomAvailabilityResponse toRoomAvailabilityResponse(RoomAvailabilityEntity entity);
}
