package com.dashotel.hotelmanagement.mapper;

import com.dashotel.hotelmanagement.dto.request.room.RoomTypeRequest;
import com.dashotel.hotelmanagement.dto.response.room.RoomTypeResponse;
import com.dashotel.hotelmanagement.entity.room.RoomTypeEntity;
import org.mapstruct.Mapper;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface RoomTypeMapper {
    RoomTypeEntity toRoomTypeEntity(RoomTypeRequest request);
    RoomTypeResponse toRoomTypeDTO(RoomTypeEntity entity);
}
