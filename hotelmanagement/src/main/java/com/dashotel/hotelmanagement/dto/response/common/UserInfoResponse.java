package com.dashotel.hotelmanagement.dto.response.common;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import lombok.experimental.FieldDefaults;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserInfoResponse {
    String id;

    String email;

    @JsonProperty("verified_email")
    boolean verifiedEmail;

    String name;
    @JsonProperty("given_name")

    String givenName;
    @JsonProperty("family_name")

    String familyName;
    String picture;
    String hd;
}

