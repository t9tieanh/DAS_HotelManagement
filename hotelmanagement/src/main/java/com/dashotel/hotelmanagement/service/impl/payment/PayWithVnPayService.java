package com.dashotel.hotelmanagement.service.impl.payment;

import com.dashotel.hotelmanagement.configuration.VNPayConfig;
import com.dashotel.hotelmanagement.dto.other.PaymentDTO;
import com.dashotel.hotelmanagement.dto.request.payment.VNPayRequest;
import com.dashotel.hotelmanagement.dto.request.payment.VnPayCallbackRequest;
import com.dashotel.hotelmanagement.enums.BookingStatusEnum;
import com.dashotel.hotelmanagement.enums.PaymentMethodEnum;
import com.dashotel.hotelmanagement.exception.CustomException;
import com.dashotel.hotelmanagement.exception.ErrorCode;
import com.dashotel.hotelmanagement.service.payment.IPaymentService;
import com.dashotel.hotelmanagement.service.payment.IVNPayPaymentService;
import com.dashotel.hotelmanagement.utils.VNPayUtils;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.net.URI;
import java.util.Map;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
@Slf4j
public class PayWithVnPayService implements IVNPayPaymentService {

    VNPayConfig vnPayConfig;
    PaymentProcessorService paymentProcessorService;

    public PaymentDTO.VNPayResponse creatVNPayPayment(VNPayRequest request) {
        long amount = request.getAmount() * 100L;
        String reservationId = request.getId();
        String bankCode = "NCB";
        Map<String, String> vnpParamsMap = vnPayConfig.getVNPayConfig(reservationId, request.getAndroid());
        vnpParamsMap.put("vnp_Amount", String.valueOf(amount));
        vnpParamsMap.put("vnp_BankCode", bankCode);
        vnpParamsMap.put("vnp_IpAddr", request.getIpAddress());

        String queryUrl = VNPayUtils.getPaymentUrl(vnpParamsMap, true);
        String hashData = VNPayUtils.getPaymentUrl(vnpParamsMap, false);
        String vnpSecureHash = VNPayUtils.hmacSHA512(vnPayConfig.getVnPaySecretKey(), hashData);
        queryUrl += "&vnp_SecureHash=" + vnpSecureHash;

        String paymentUrl = vnPayConfig.getVnPayUrl() + "?" + queryUrl;

        return PaymentDTO.VNPayResponse.builder()
                .code("ok")
                .message("success")
                .paymentUrl(paymentUrl)
                .build();
    }

    @Override
    public URI processPay(VnPayCallbackRequest request) {
        if (request.getVnpResponseCode().equals("00")) {
            boolean success = paymentProcessorService.processPayment(request.getReservationId(), PaymentMethodEnum.VNPAY, BookingStatusEnum.PAID);

            if (success) {
                String location = request.getAndroid() ? "myapp://reservation/success" : "http://localhost:3000/reservation";
                return URI.create(location);
            } else
                throw new CustomException(ErrorCode.PAYMENT_RESERVATION_AVAILABILITY_FAILED);
        } else {
            // Trả về lỗi nếu mã phản hồi không phải là "00"
            throw new CustomException(ErrorCode.PAYMENT_FAILED_WITH_RESPONSE_CODE);
        }
    }
}
