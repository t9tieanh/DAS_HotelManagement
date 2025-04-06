package com.dashotel.hotelmanagement.dto.request.reservation.initial;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class InitialReservationRequest {
    //thông tin phòng
    List<ReservationDetailRequest> reservationDetails;

    //checkIn checkOut
    LocalDate checkIn;
    LocalDate checkOut;
}
