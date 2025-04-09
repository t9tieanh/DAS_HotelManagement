package com.dashotel.hotelmanagement.dto.response.room;

import com.dashotel.hotelmanagement.dto.request.room.RoomTypeRequest;
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
public class RoomAvailabilityResponse {
    RoomTypeRequest roomType;
    Long totalRoom;
    Long bookedRoom;
    LocalDate availableDate;
    Boolean status;
}
