package com.dashotel.hotelmanagement.controller.payment;

import com.dashotel.hotelmanagement.dto.other.PaymentDTO;
import com.dashotel.hotelmanagement.dto.request.payment.VNPayRequest;
import com.dashotel.hotelmanagement.dto.request.payment.VnPayCallbackRequest;
import com.dashotel.hotelmanagement.dto.response.ApiResponse;
import com.dashotel.hotelmanagement.dto.response.payment.PaymentResponse;
import com.dashotel.hotelmanagement.dto.response.reservation.common.ReservationResponse;
import com.dashotel.hotelmanagement.enums.PaymentMethodEnum;
import com.dashotel.hotelmanagement.service.payment.PaymentService;
import com.dashotel.hotelmanagement.service.reservation.ReservationService;
import com.dashotel.hotelmanagement.service.room.RoomAvailabilityService;
import com.dashotel.hotelmanagement.utils.VNPayUtils;
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

//    @GetMapping("/vn-pay")
//    public ApiResponse<PaymentDTO.VNPayResponse> pay(
//            HttpServletRequest request,
//            @RequestParam(value = "platform",  defaultValue = "web") String platform
//    ) {
//        if ("android".equalsIgnoreCase(platform)) {
//            System.out.println("Request đến từ Android app");
//        } else {
//            System.out.println("Request đến từ Web app hoặc không xác định");
//        }
//
//        return new ApiResponse<>(HttpStatus.OK.value(), "Success", paymentService.creatVNPayPayment(request));
//    }

    @GetMapping("/vn-pay")
    public ApiResponse<PaymentDTO.VNPayResponse> pay(
            HttpServletRequest servletRequest,
            @RequestParam(value = "platform", defaultValue = "web") String platform,
            @RequestParam("amount") Long amount,
            @RequestParam("id") String reservationId
    ) {
        // Lấy IP client
        String clientIp = VNPayUtils.getIpAddress(servletRequest);

        // Xác định có phải Android không
        boolean isAndroid = "android".equalsIgnoreCase(platform);

        VNPayRequest vnPayRequest = VNPayRequest.builder()
                .amount(amount)
                .id(reservationId)
                .android(isAndroid)
                .ipAddress(clientIp)
                .build();

        // Gọi Service
        return new ApiResponse<>(HttpStatus.OK.value(), "Success", paymentService.creatVNPayPayment(vnPayRequest));
    }

    @GetMapping("/vn-pay-callback/{id}")
    public ResponseEntity<Void> payCallbackHandler(
            HttpServletRequest servletRequest,
            @PathVariable("id") String id,
            @RequestParam(value = "platform", defaultValue = "web") String platform
    ) {
        // 1. Lấy vnp_ResponseCode
        String vnpResponseCode = servletRequest.getParameter("vnp_ResponseCode");

        // 2. Build DTO
        VnPayCallbackRequest request = VnPayCallbackRequest.builder()
                .reservationId(id)
                .vnpResponseCode(vnpResponseCode)
                .android("android".equalsIgnoreCase(platform))
                .build();

        try {
            // 3. Gọi service để xử lý và nhận URI redirect
            URI redirectUri = paymentService.handlePaymentWithVnPay(request);

            // 4. Trả về 302 Found với Location header
            return ResponseEntity
                    .status(HttpStatus.FOUND)
                    .location(redirectUri)
                    .build();

        } catch (RuntimeException ex) {
            // 5. Xử lý lỗi (có thể log thêm ex.getMessage())
            return ResponseEntity
                    .badRequest()
                    .build();
        }
    }

}
