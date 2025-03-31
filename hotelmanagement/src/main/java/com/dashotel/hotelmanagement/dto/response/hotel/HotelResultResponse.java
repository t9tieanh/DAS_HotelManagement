package com.dashotel.hotelmanagement.dto.response.hotel;

import com.dashotel.hotelmanagement.dto.common.AddressDTO;
import com.dashotel.hotelmanagement.dto.request.room.RoomTypeRequest;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class HotelResultResponse {
    String id;
    String name;
    AddressDTO address;
    int rating;
    List<RoomTypeRequest> roomType;
    String avatar;
    double minRoomPrice;
}
