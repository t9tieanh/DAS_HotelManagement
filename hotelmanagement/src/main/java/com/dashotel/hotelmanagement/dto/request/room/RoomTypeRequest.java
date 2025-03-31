package com.dashotel.hotelmanagement.dto.request.room;

import com.dashotel.hotelmanagement.enums.RoomStatusEnum;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class RoomTypeRequest {
    String id;
    String name;
    Long price;
    Long maxOccupation;
    Long freeChildren;
    String description;
    List<String> imgRoomUrl;
    RoomStatusEnum status;
    String avatar;
}
