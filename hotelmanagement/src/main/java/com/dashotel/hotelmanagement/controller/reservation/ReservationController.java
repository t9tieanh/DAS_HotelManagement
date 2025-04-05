package com.dashotel.hotelmanagement.controller.reservation;

import com.dashotel.hotelmanagement.dto.common.DiscountDTO;
import com.dashotel.hotelmanagement.dto.request.reservation.initial.InitialReservationRequest;
import com.dashotel.hotelmanagement.dto.request.reservation.updateinfo.UpdateReservationInfoRequest;
import com.dashotel.hotelmanagement.dto.response.ApiResponse;
import com.dashotel.hotelmanagement.dto.response.CreationResponse;
import com.dashotel.hotelmanagement.service.reservation.ReservationService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;

@RestController
@RequestMapping("/reservation")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ReservationController {
    ReservationService reservationService;

    @PostMapping
    ApiResponse<CreationResponse> createReservation (@RequestBody InitialReservationRequest request) throws ParseException {
        CreationResponse result = reservationService.createReservation(request);

        return ApiResponse.<CreationResponse>builder()
                .code(200)
                .result(result)
                .build();
    }

    @PreAuthorize("@reservationService.isOwnerOfReservation(#request.reservationId, authentication.name)")
    @PostMapping(value = "/update-info")
    ApiResponse<CreationResponse> updateReservationInfo (@RequestBody UpdateReservationInfoRequest request) throws ParseException {
        CreationResponse result = reservationService.updateInfoReservation(request);

        return ApiResponse.<CreationResponse>builder()
                .code(200)
                .build();
    }

}
