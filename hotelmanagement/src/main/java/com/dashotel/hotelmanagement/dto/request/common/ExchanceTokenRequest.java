package com.dashotel.hotelmanagement.dto.request.common;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.*;
import lombok.experimental.FieldDefaults;



@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class ExchanceTokenRequest {
    String code;
    String clientId;
    String clientSecret;
    String redirectUri;
    String grantType = "authorization_code";
}