package com.dashotel.hotelmanagement.dto.request.service;

import com.dashotel.hotelmanagement.enums.HotelFacilityCategory;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class HotelFacilityRequest {
    String name;
    HotelFacilityCategory category;
}
