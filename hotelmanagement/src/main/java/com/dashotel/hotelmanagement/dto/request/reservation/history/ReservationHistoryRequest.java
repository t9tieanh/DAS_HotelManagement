package com.dashotel.hotelmanagement.dto.request.reservation.history;

import com.dashotel.hotelmanagement.dto.common.AddressDTO;
import com.dashotel.hotelmanagement.dto.request.service.HotelFacilityRequest;
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
public class ReservationHistoryRequest {
    String reservationId;
    String hotelName;
    AddressDTO address;
    List<HotelFacilityRequest> facility;
    String roomTypeName;
    double totalPrice;
    String reservationDate;
    String checkInDate;
    String checkOutDate;
}
