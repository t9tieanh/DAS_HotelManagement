package com.dashotel.hotelmanagement.dto.request.payment;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class VNPayRequest {
    private Long amount;
    private String id;
    private Boolean android;
    private String ipAddress;
}
