package com.dashotel.hotelmanagement.dto.response.reservation;

import com.dashotel.hotelmanagement.dto.common.DiscountDTO;
import com.dashotel.hotelmanagement.dto.response.reservation.common.ReservationDetailResponse;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ReservationStepResponse {
    int currentStep;
    LocalDateTime expireDateTime;
    String description;

    // ngày check in check out
    LocalDate checkIn;
    LocalDate checkOut;

    // thông tin phòng đặt
    List<ReservationDetailResponse> reservationDetail;

    //Thông tin discount được áp dụng
    Set<DiscountDTO> discounts;
}
