package com.dashotel.hotelmanagement.dto.request.reservation.initial;

import com.dashotel.hotelmanagement.enums.BookingStatusEnum;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ReservationRequest {
    String id;
    LocalDate checkIn;
    LocalDate checkOut;
    LocalDate reservationDate;
    BookingStatusEnum status;
    LocalDateTime expireDateTime;
}
