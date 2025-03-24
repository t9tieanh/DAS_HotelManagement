package com.dashotel.hotelmanagement.dto.response;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CustomerResponseDTO {
    String name;
    String email;
    String phone;
    String imgUrl;
    Long loyaltyPoints;
}
