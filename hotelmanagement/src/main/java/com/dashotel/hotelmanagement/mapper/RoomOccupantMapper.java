package com.dashotel.hotelmanagement.mapper;
import com.dashotel.hotelmanagement.dto.request.reservation.updateinfo.UpdateReservationInfoRequest;
import com.dashotel.hotelmanagement.entity.booking.RoomOccupantEntity;
import org.mapstruct.Mapper;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface RoomOccupantMapper {
    RoomOccupantEntity toEntity(UpdateReservationInfoRequest request);
}
