package com.dashotel.hotelmanagement.controller.reservation;

import com.dashotel.hotelmanagement.dto.common.DiscountDTO;
import com.dashotel.hotelmanagement.dto.common.ResponseDTO;
import com.dashotel.hotelmanagement.dto.request.reservation.initial.ApplyDiscountRequest;
import com.dashotel.hotelmanagement.dto.request.reservation.initial.InitialReservationRequest;
import com.dashotel.hotelmanagement.dto.request.reservation.updateinfo.UpdateReservationInfoRequest;
import com.dashotel.hotelmanagement.dto.response.ApiResponse;
import com.dashotel.hotelmanagement.dto.response.CreationResponse;
import com.dashotel.hotelmanagement.dto.response.reservation.InitialReservationResponse;
import com.dashotel.hotelmanagement.dto.response.reservation.ReservationStepResponse;
import com.dashotel.hotelmanagement.service.reservation.ReservationService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;

@RestController
@RequestMapping("/reservation")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ReservationController {
    ReservationService reservationService;

    @PostMapping
    ApiResponse<InitialReservationResponse> createReservation (@RequestBody InitialReservationRequest request) throws ParseException {
        InitialReservationResponse result = reservationService.createReservation(request);

        return ApiResponse.<InitialReservationResponse>builder()
                .code(200)
                .result(result)
                .build();
    }

    @PreAuthorize("@reservationService.isOwnerOfReservation(#request.reservationId, authentication.name)")
    @PostMapping(value = "/update-info")
    ApiResponse<CreationResponse> updateReservationInfo (@RequestBody UpdateReservationInfoRequest request) throws ParseException {
        CreationResponse result = reservationService.updateInfoReservation(request);
        String message = "Xác nhận thông tin cư trú thành công !";

        return ApiResponse.<CreationResponse>builder()
                .code(200)
                .message(message)
                .result(result)
                .build();
    }

    @PreAuthorize("@reservationService.isOwnerOfReservation(#id, authentication.name)")
    @DeleteMapping("/{id}")
    public ApiResponse<ResponseDTO> cancelReservation(@PathVariable String id) {
        // Gọi service để xóa reservation theo id
        ResponseDTO deleted = reservationService.cancelReservation(id);

        return ApiResponse.<ResponseDTO>builder()
                .code(200)
                .result(deleted)
                .build();
    }


//    @PreAuthorize("@reservationService.isOwnerOfReservation(#id, authentication.name)")
//    @GetMapping("/current-step")
//    ApiResponse<ReservationStepResponse> getCurrentStep (@RequestParam("id") String id)  {
//        ReservationStepResponse result = reservationService.getCurrentStep(id);
//
//        return ApiResponse.<ReservationStepResponse>builder()
//                .code(200)
//                .result(result)
//                .build();
//    }

    @PreAuthorize("@reservationService.isOwnerOfReservation(#id, authentication.name)")
    @GetMapping("/total-price/{id}")
    ApiResponse<Double> getTotalPrice (@PathVariable("id") String id)  {
        Double result = reservationService.getTotalPrice(id);

        return ApiResponse.<Double>builder()
                .code(200)
                .result(result)
                .build();
    }



    @PreAuthorize("@reservationService.isOwnerOfReservation(#id, authentication.name)")
    @GetMapping("/current-step")
    ApiResponse<ReservationStepResponse> getCurrentStep (@RequestParam("id") String id)  {
        ReservationStepResponse result = reservationService.getCurrentStep(id);

        return ApiResponse.<ReservationStepResponse>builder()
                .code(200)
                .result(result)
                .build();
    }

    @PreAuthorize("@reservationService.isOwnerOfReservation(#request.reservationId, authentication.name)")
    @GetMapping("/apply-discount")
    ApiResponse<ReservationStepResponse> applyDiscount (ApplyDiscountRequest request) {
        ReservationStepResponse result = null;

        return ApiResponse.<ReservationStepResponse>builder()
                .code(200)
                .result(result)
                .build();
    }

}
