package com.dashotel.hotelmanagement.dto.response.hotel;

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
public class FacilitiesResponse {
    String name;
    HotelFacilityCategory category;
    String categoryName;
}
