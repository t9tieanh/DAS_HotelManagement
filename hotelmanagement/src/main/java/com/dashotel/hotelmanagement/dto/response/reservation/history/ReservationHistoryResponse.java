package com.dashotel.hotelmanagement.dto.response.reservation.history;

import com.dashotel.hotelmanagement.dto.common.AddressDTO;
import com.dashotel.hotelmanagement.dto.request.service.HotelFacilityRequest;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ReservationHistoryResponse {
    String hotelName;
    AddressDTO address;
    List<HotelFacilityRequest> facility;
    String roomTypeName;
    double totalPrice;
    LocalDate reservationDate;
    LocalDate checkInDate;
    LocalDate checkOutDate;
    String img;
}
