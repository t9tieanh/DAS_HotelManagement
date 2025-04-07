package com.dashotel.hotelmanagement.service.payment;

import com.dashotel.hotelmanagement.configuration.VNPayConfig;
import com.dashotel.hotelmanagement.dto.other.PaymentDTO;
import com.dashotel.hotelmanagement.utils.VNPayUtils;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@RequiredArgsConstructor
public class PaymentService {
    private final VNPayConfig vnPayConfig;

    public PaymentDTO.VNPayResponse creatVNPayPayment(HttpServletRequest request) {
        long amount = Integer.parseInt(request.getParameter("amount")) * 100L;
        String bankCode = "NCB";
        Map<String, String> vnpParamsMap = vnPayConfig.getVNPayConfig();
        vnpParamsMap.put("vnp_Amount", String.valueOf(amount));
        vnpParamsMap.put("vnp_BankCode", bankCode);
        vnpParamsMap.put("vnp_IpAddr", VNPayUtils.getIpAddress(request));

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
}
