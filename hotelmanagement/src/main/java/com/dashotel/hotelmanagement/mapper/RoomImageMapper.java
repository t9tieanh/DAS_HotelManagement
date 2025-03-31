package com.dashotel.hotelmanagement.mapper;

import com.dashotel.hotelmanagement.dto.request.hotel.HotelImageRequest;
import com.dashotel.hotelmanagement.dto.request.room.RoomTypeImageRequest;
import com.dashotel.hotelmanagement.entity.hotel.HotelImageEntity;
import com.dashotel.hotelmanagement.entity.room.RoomImageEntity;
import org.mapstruct.Mapper;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface RoomImageMapper {
    RoomImageEntity toEntity(RoomTypeImageRequest request);
}
