package com.dashotel.hotelmanagement.dto.request.reservation.updateinfo;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UpdateReservationInfoRequest {
    String reservationId;

    // thông tin khách hàng
    String name;
    String phone;
    String email;
}
