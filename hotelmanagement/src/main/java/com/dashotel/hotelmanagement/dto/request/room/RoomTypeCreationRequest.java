package com.dashotel.hotelmanagement.dto.request.room;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
public class RoomTypeCreationRequest {
    String name;
    Long price;
    Long maxOccupation;
    Long freeChildren;
    String description;
    String hotelId;

    @JsonIgnore
    MultipartFile img;
}
