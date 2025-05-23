package com.dashotel.hotelmanagement.controller.reservation;

import com.dashotel.hotelmanagement.dto.common.DiscountDTO;
import com.dashotel.hotelmanagement.dto.common.ResponseDTO;
import com.dashotel.hotelmanagement.dto.request.reservation.initial.ApplyDiscountRequest;
import com.dashotel.hotelmanagement.dto.request.reservation.initial.InitialReservationRequest;
import com.dashotel.hotelmanagement.dto.request.reservation.updateinfo.UpdateReservationInfoRequest;
import com.dashotel.hotelmanagement.dto.response.ApiResponse;
import com.dashotel.hotelmanagement.dto.response.common.CreationResponse;
import com.dashotel.hotelmanagement.dto.response.reservation.ApplyDiscountResponse;
import com.dashotel.hotelmanagement.dto.response.reservation.InitialReservationResponse;
import com.dashotel.hotelmanagement.dto.response.reservation.ReservationStepResponse;
import com.dashotel.hotelmanagement.dto.response.reservation.history.ReservationHistoryResponse;
import com.dashotel.hotelmanagement.service.auth.IAuthenticationService;
import com.dashotel.hotelmanagement.service.impl.auth.AuthenticationService;
import com.dashotel.hotelmanagement.service.impl.reservation.ReservationService;
import com.dashotel.hotelmanagement.service.reservation.IReservationService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.List;

@RestController
@RequestMapping("/reservation")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ReservationController {
    IReservationService reservationService;
    IAuthenticationService authService;

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
    ApiResponse<CreationResponse> updateCustomerInfoReservation (@RequestBody UpdateReservationInfoRequest request) {
        CreationResponse result = reservationService.updateCustomerInfoReservation(request);
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

        String message = "Xác nhận hủy đặt phòng thành công !";

        return ApiResponse.<ResponseDTO>builder()
                .code(200)
                .message(message)
                .result(deleted)
                .build();
    }


    @PreAuthorize("@reservationService.isOwnerOfReservation(#id, authentication.name)")
    @GetMapping("/total-price/{id}")
    ApiResponse<Double> getTotalPrice (@PathVariable("id") String id)  {
        Double result = reservationService.getTotalPrice(id);

        return ApiResponse.<Double>builder()
                .code(200)
                .result(result)
                .build();
    }


    @PreAuthorize("@reservationService.isOwnerOfReservation(#request.reservationId, authentication.name)")
    @PostMapping("apply-discount")
    ApiResponse<ApplyDiscountResponse> applyDiscount (@RequestBody ApplyDiscountRequest request) throws ParseException {
        ApplyDiscountResponse result = reservationService.applyDiscount(request);

        return ApiResponse.<ApplyDiscountResponse>builder()
                .code(200)
                .message("Áp dụng mã giảm giá thành công !")
                .result(result)
                .build();
    }


    @PreAuthorize("@reservationService.isOwnerOfReservation(#id, authentication.name)")
    @GetMapping("applied-discounts/{id}")
    ApiResponse<List<DiscountDTO>> getDiscountByReservation (@PathVariable("id") String id) {
        List<DiscountDTO> result = reservationService.getDiscountByReservation(id);

        return ApiResponse.<List<DiscountDTO>>builder()
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

    @GetMapping("/history")
    ApiResponse<List<ReservationHistoryResponse>> getReservationHistory() {
        String customerId = authService.getCurrentUserId();
        List<ReservationHistoryResponse> result = reservationService.getReservationHistory(customerId);

        return ApiResponse.<List<ReservationHistoryResponse>>builder()
                .code(HttpStatus.OK.value())
                .result(result)
                .build();
    }

    @GetMapping("/reservations-completed-count")
     ApiResponse<Long> getReservationCompletedCount() {
        String customerId = authService.getCurrentUserId();
        Long result = reservationService.getReservationCompletedCount(customerId);

        return ApiResponse.<Long>builder()
                .code(HttpStatus.OK.value())
                .result(result)
                .build();
    }
}
