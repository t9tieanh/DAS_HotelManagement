package com.dashotel.hotelmanagement.dto.request.hotel;

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
public class HotelResultRequest {
    LocalDate checkIn;
    LocalDate checkOut;
    Long numAdults;
    Long numRooms;
}
