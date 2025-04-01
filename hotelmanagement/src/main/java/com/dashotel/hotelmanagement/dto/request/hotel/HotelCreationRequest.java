package com.dashotel.hotelmanagement.dto.request.hotel;
import com.dashotel.hotelmanagement.dto.common.AddressDTO;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class HotelCreationRequest {
    String name;
    String subName;
    String description;

    String addressConcrete;
    String addressCommune;
    String addressDistrict;
    String addressCity;

    List<String> facilitiesIds;

    @JsonIgnore
    MultipartFile img;
}
