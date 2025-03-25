package com.dashotel.hotelmanagement.dto.request.hotel;

import com.dashotel.hotelmanagement.enums.HotelImageEnum;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.web.multipart.MultipartFile;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class HotelImageRequest {
    String hotelId;

    @JsonIgnore
    MultipartFile img;

    Boolean isAvartar;
    HotelImageEnum imageType;
}
