package com.dashotel.hotelmanagement.dto.request.room;

import com.dashotel.hotelmanagement.enums.HotelImageEnum;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.web.multipart.MultipartFile;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class RoomTypeImageRequest {
    String roomTypeId;
    Boolean isAvatar;

    @JsonIgnore
    MultipartFile img;
}
