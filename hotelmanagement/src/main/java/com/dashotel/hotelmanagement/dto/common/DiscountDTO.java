package com.dashotel.hotelmanagement.dto.common;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class DiscountDTO {
    String name;
    String code;
    LocalDate beginDate;
    LocalDate endDate;

    int discountPrecentage;
    int minloyaltyPoints = 0;
    double minBookingAmount;
    double maxDiscountAmount;
    Boolean isPublic;
}
