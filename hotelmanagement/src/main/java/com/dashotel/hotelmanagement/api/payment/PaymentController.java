package com.dashotel.hotelmanagement.api.payment;

import com.dashotel.hotelmanagement.dto.common.RequestDTO;
import com.dashotel.hotelmanagement.dto.other.PaymentDTO;
import com.dashotel.hotelmanagement.dto.request.payment.VNPayRequest;
import com.dashotel.hotelmanagement.dto.request.payment.VnPayCallbackRequest;
import com.dashotel.hotelmanagement.dto.response.ApiResponse;
import com.dashotel.hotelmanagement.dto.response.common.CreationResponse;
import com.dashotel.hotelmanagement.service.payment.IPaymentService;
import com.dashotel.hotelmanagement.service.payment.IVNPayPaymentService;
import com.dashotel.hotelmanagement.utils.VNPayUtils;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("/api/v1/payment")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class PaymentController {

    @Qualifier("payAtHotelService")
    IPaymentService<RequestDTO, CreationResponse> payAtHotelService;
    IVNPayPaymentService payWithVnPayService;

    @GetMapping("/vn-pay")
    @PreAuthorize("@reservationService.isOwnerOfReservation(#reservationId, authentication.name)")
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
        return new ApiResponse<>(HttpStatus.OK.value(), "Success", payWithVnPayService.creatVNPayPayment(vnPayRequest));
    }


    // api nhận callback từ vnpay
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
            URI redirectUri = payWithVnPayService.processPay(request);

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


    @PostMapping("/at-hotel")
    @PreAuthorize("@reservationService.isOwnerOfReservation(#requestDTO.id, authentication.name)")
    public ApiResponse<CreationResponse> payAtHotel(@RequestBody RequestDTO requestDTO) {
        CreationResponse result = payAtHotelService.processPay(requestDTO);

        String message = (result.getIsSuccess()) ? "Bạn đã thanh toán thành công !" : "Thanh toán thất bại";

        return ApiResponse.<CreationResponse>builder()
                .code(200)
                .result(result)
                .message(message)
                .build();
    }
}
