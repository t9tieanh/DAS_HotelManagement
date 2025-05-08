package com.dashotel.hotelmanagement.dto.request.common;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CustomerRequestDTO {
    String name;
    String email;
    String phone;
    String imgUrl;
    Long loyaltyPoints;
}
