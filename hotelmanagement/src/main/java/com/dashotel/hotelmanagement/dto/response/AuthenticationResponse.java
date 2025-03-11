package com.dashotel.hotelmanagement.dto.response;

import com.dashotel.hotelmanagement.enums.RoleAccountEnum;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AuthenticationResponse {
    String token;
    String username;
    RoleAccountEnum role;
    boolean valid = true;
}
