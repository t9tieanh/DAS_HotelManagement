package com.dashotel.hotelmanagement.dto.request.common;

import lombok.*;

@Builder // no usages
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CreateMonoRequest {
    private String partnerCode;
    private String requestType;
    private String ipnUrl;
    private String orderId;
    private long amount;
    private String orderInfo;
    private String requestId;
    private String redirectUrl;
    private String lang;
    private String extraData;
    private String signature;
}
