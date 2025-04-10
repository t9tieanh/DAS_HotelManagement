package com.dashotel.hotelmanagement.controller.payment;

import com.dashotel.hotelmanagement.dto.other.PaymentDTO;
import com.dashotel.hotelmanagement.dto.response.ApiResponse;
import com.dashotel.hotelmanagement.dto.response.payment.PaymentResponse;
import com.dashotel.hotelmanagement.dto.response.reservation.common.ReservationResponse;
import com.dashotel.hotelmanagement.enums.PaymentMethodEnum;
import com.dashotel.hotelmanagement.service.payment.PaymentService;
import com.dashotel.hotelmanagement.service.reservation.ReservationService;
import com.dashotel.hotelmanagement.service.room.RoomAvailabilityService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("/api/v1/payment")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class PaymentController {
    PaymentService paymentService;
    ReservationService reservationService;
    RoomAvailabilityService roomAvailabilityService;
    @GetMapping("/vn-pay")
    public ApiResponse<PaymentDTO.VNPayResponse> pay(HttpServletRequest request) {
        return new ApiResponse<>(HttpStatus.OK.value(), "Success", paymentService.creatVNPayPayment(request));
    }
    @GetMapping("/vn-pay-callback/{id}")
    public ResponseEntity payCallbackHandler(HttpServletRequest request, @PathVariable String id) {
        String status = request.getParameter("vnp_ResponseCode");
        if (status.equals("00")) {
            PaymentResponse payment = paymentService.savePayment(id, PaymentMethodEnum.VNPAY);

            // CHINH SUA RESERVATION
            ReservationResponse reservation = reservationService.updateReservationIsSuccess(id);

            // UPDATE ROOM AVAILABLE
            Boolean roomAvailability = roomAvailabilityService.updateRoomAvail(id);

            if(payment != null && reservation != null && roomAvailability) {
                return ResponseEntity
                        .status(HttpStatus.FOUND)
                        .location(URI.create("http://localhost:3000/reservation"))
                        .build();
            }
            else {
                return new ResponseEntity<>("Failed", null, HttpStatus.BAD_REQUEST.value());
            }
        } else {
            return new ResponseEntity<>("Failed", null, HttpStatus.BAD_REQUEST.value());
        }
    }

}
