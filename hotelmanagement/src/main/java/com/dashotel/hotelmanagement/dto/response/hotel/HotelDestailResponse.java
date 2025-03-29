package com.dashotel.hotelmanagement.dto.response.hotel;

import com.dashotel.hotelmanagement.dto.common.AddressDTO;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class HotelDestailResponse {
    String id;
    String name;
    String subName;
    String description;

    AddressDTO address;
    List<FacilitiesResponse> facilities;
    List<String> imgs;

    String avartar;
}
