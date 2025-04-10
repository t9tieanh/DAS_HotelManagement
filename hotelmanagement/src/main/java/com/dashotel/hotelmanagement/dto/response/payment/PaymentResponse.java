package com.dashotel.hotelmanagement.dto.response.payment;

import com.dashotel.hotelmanagement.enums.PaymentMethodEnum;
import com.dashotel.hotelmanagement.enums.PaymentStatusEnum;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PaymentResponse {
    Double amount;
    LocalDate paymentDate;
    PaymentStatusEnum status;
    PaymentMethodEnum paymentMethod;
}