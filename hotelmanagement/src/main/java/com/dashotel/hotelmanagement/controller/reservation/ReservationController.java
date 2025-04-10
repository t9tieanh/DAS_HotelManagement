package com.dashotel.hotelmanagement.controller.reservation;

import com.dashotel.hotelmanagement.dto.common.DiscountDTO;
import com.dashotel.hotelmanagement.dto.request.reservation.initial.InitialReservationRequest;
import com.dashotel.hotelmanagement.dto.request.reservation.updateinfo.UpdateReservationInfoRequest;
import com.dashotel.hotelmanagement.dto.response.ApiResponse;
import com.dashotel.hotelmanagement.dto.response.CreationResponse;
import com.dashotel.hotelmanagement.dto.response.reservation.InitialReservationResponse;
import com.dashotel.hotelmanagement.dto.response.reservation.ReservationStepResponse;
import com.dashotel.hotelmanagement.dto.response.reservation.history.ReservationHistoryResponse;
import com.dashotel.hotelmanagement.service.auth.AuthenticationService;
import com.dashotel.hotelmanagement.service.reservation.ReservationService;
import com.dashotel.hotelmanagement.utils.JwtUtils;
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
    ReservationService reservationService;
    AuthenticationService authService;

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

//    @DeleteMapping("/{id}")
//    public ApiResponse<Cancel> cancelReservation(@PathVariable Long id) {
//        // Gọi service để xóa reservation theo id
//        boolean deleted = reservationService.cancelReservation(id);
//
//        if (deleted) {
//            return ResponseEntity.ok("Reservation deleted successfully.");
//        } else {
//            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Reservation not found.");
//        }
//    }


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

}
