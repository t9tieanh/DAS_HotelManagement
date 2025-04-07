package com.dashotel.hotelmanagement.dto.response.reservation.common;

import com.dashotel.hotelmanagement.dto.common.AddressDTO;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ReservationDetailResponse {
    String hotelName;
    AddressDTO address;
    String hotelImgUrl;

    // thông tin phòng
    String name;
    String description;
    String imgUrl;

    Long quantity;
    Double price;
}
