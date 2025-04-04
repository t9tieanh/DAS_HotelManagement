package com.dashotel.hotelmanagement.configuration;

import com.dashotel.hotelmanagement.utils.VNPayUtils;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

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

    public Map<String, String> getVNPayConfig() {
        Map<String, String> vnPayParam = new HashMap<>();
        vnPayParam.put("vnp_Version", this.vnPayVersion);
        vnPayParam.put("vnp_Command", this.vnPayCommand);
        vnPayParam.put("vnp_TmnCode", this.vnPayTmnCode);
        vnPayParam.put("vnp_CurrCode", "VND");
        vnPayParam.put("vnp_TxnRef",  VNPayUtils.getRandomNumber(8));
        vnPayParam.put("vnp_OrderInfo", "Payment for order:" +  VNPayUtils.getRandomNumber(8));
        vnPayParam.put("vnp_OrderType", this.vnPayOrderType);
        vnPayParam.put("vnp_Locale", "vn");
        vnPayParam.put("vnp_ReturnUrl", this.vnPayReturnUrl);


        Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("Etc/GMT+7"));
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
        String vnpCreateDate = formatter.format(calendar.getTime());
        vnPayParam.put("vnp_CreateDate", vnpCreateDate);
        calendar.add(Calendar.MINUTE, 15);
        String vnp_ExpireDate = formatter.format(calendar.getTime());
        vnPayParam.put("vnp_ExpireDate", vnp_ExpireDate);
        return vnPayParam;
    }
}
