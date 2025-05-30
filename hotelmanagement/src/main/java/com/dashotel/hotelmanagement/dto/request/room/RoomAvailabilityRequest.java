package com.dashotel.hotelmanagement.dto.request.room;

import com.dashotel.hotelmanagement.dto.response.room.RoomTypeResponse;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class RoomAvailabilityRequest {
    String id;
    RoomTypeResponse roomType;
    Long totalRoom;
    Long bookedRoom;
    LocalDate availableDate;
    Boolean status;
}
