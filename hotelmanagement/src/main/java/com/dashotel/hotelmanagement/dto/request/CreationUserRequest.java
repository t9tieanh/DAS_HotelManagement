package com.dashotel.hotelmanagement.dto.request;

import com.dashotel.hotelmanagement.enums.RoleAccountEnum;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CreationUserRequest {
    String username;
    String password;
    RoleAccountEnum role;
    String name;
    String email;
    String phone;
    String imgUrl;
    MultipartFile img;
}
