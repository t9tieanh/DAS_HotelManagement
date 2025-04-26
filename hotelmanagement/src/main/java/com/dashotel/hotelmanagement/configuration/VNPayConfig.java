package com.dashotel.hotelmanagement.configuration;

import com.dashotel.hotelmanagement.utils.VNPayUtils;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.util.UriComponentsBuilder;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import java.util.TimeZone;

@Configuration
@Getter
public class VNPayConfig {
    @Value("${VNPAY.URL}")
    private String vnPayUrl;

    @Value("${VNPAY.TMN_CODE}")
    private String vnPayTmnCode;

    @Value("${VNPAY.SECRET_KEY}")
    private String vnPaySecretKey;

    @Value("${VNPAY.RETURN_URL}")
    private String vnPayReturnUrl;

    @Value("${VNPAY.VERSION}")
    private String vnPayVersion;

    @Value("${VNPAY.COMMAND}")
    private String vnPayCommand;

    @Value("${VNPAY.ORDER_TYPE}")
    private String vnPayOrderType;

    public Map<String, String> getVNPayConfig(String reservationId) {
        Map<String, String> vnPayParam = new HashMap<>();
        vnPayParam.put("vnp_Version", this.vnPayVersion);
        vnPayParam.put("vnp_Command", this.vnPayCommand);
        vnPayParam.put("vnp_TmnCode", this.vnPayTmnCode);
        vnPayParam.put("vnp_CurrCode", "VND");
        vnPayParam.put("vnp_TxnRef",  VNPayUtils.getRandomNumber(8));
        vnPayParam.put("vnp_OrderInfo", "Payment for order:" +  VNPayUtils.getRandomNumber(8));
        vnPayParam.put("vnp_OrderType", this.vnPayOrderType);
        vnPayParam.put("vnp_Locale", "vn");
        vnPayParam.put("vnp_ReturnUrl", this.vnPayReturnUrl + "/" + reservationId);


        Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("Etc/GMT+7"));
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
        String vnpCreateDate = formatter.format(calendar.getTime());
        vnPayParam.put("vnp_CreateDate", vnpCreateDate);
        calendar.add(Calendar.MINUTE, 15);
        String vnp_ExpireDate = formatter.format(calendar.getTime());
        vnPayParam.put("vnp_ExpireDate", vnp_ExpireDate);
        return vnPayParam;
    }

    public Map<String, String> getVNPayConfig(String reservationId, boolean isAndroid) {
        Map<String, String> vnPayParams = new HashMap<>();

        // Các tham số cố định
        vnPayParams.put("vnp_Version", vnPayVersion);
        vnPayParams.put("vnp_Command", vnPayCommand);
        vnPayParams.put("vnp_TmnCode", vnPayTmnCode);
        vnPayParams.put("vnp_CurrCode", "VND");
        vnPayParams.put("vnp_TxnRef", VNPayUtils.getRandomNumber(8));
        vnPayParams.put("vnp_OrderInfo", "Payment for order: " + VNPayUtils.getRandomNumber(8));
        vnPayParams.put("vnp_OrderType", vnPayOrderType);
        vnPayParams.put("vnp_Locale", "vn");

        // 1. Chọn base URL tùy Android hay web
        String baseReturnUrl = isAndroid
                ? "http://10.0.2.2:8080/api/v1/payment/vn-pay-callback"
                : vnPayReturnUrl;

        String returnUrl = UriComponentsBuilder
                .fromHttpUrl(baseReturnUrl)
                .pathSegment(reservationId)
                .queryParam("platform", isAndroid ? "android" : "web")
                .build()
                .toUriString();

        vnPayParams.put("vnp_ReturnUrl", returnUrl);

        // Xử lý thời gian tạo & hết hạn
        Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("Etc/GMT+7"));
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
        String vnpCreateDate = formatter.format(calendar.getTime());
        vnPayParams.put("vnp_CreateDate", vnpCreateDate);

        calendar.add(Calendar.MINUTE, 15); // +15 phút
        String vnpExpireDate = formatter.format(calendar.getTime());
        vnPayParams.put("vnp_ExpireDate", vnpExpireDate);

        return vnPayParams;
    }
}
