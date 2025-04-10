package com.dashotel.hotelmanagement.dto.response.reservation;

import com.dashotel.hotelmanagement.dto.common.DiscountDTO;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ApplyDiscountResponse {
    boolean isSuccess;
    Set<DiscountDTO> discounts;
}
