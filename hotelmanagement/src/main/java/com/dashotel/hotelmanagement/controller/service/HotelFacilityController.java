package com.dashotel.hotelmanagement.controller.service;

import com.dashotel.hotelmanagement.dto.request.CreationUserRequest;
import com.dashotel.hotelmanagement.dto.request.service.HotelFacilityRequest;
import com.dashotel.hotelmanagement.dto.response.ApiResponse;
import com.dashotel.hotelmanagement.dto.response.AuthenticationResponse;
import com.dashotel.hotelmanagement.dto.response.CreationResponse;
import com.dashotel.hotelmanagement.enums.HotelFacilityCategory;
import com.dashotel.hotelmanagement.service.service.HotelFacilityService;
import com.nimbusds.jose.JOSEException;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/hotel-facility")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class HotelFacilityController {

    HotelFacilityService hotelFacilityService;
    @PostMapping
    ApiResponse<CreationResponse> addHotelFacility (@RequestBody HotelFacilityRequest request) {
        request.setCategory(HotelFacilityCategory.PUBLIC_FACILITIES);

        var response = hotelFacilityService.addHotelFacility(request);
        return ApiResponse.<CreationResponse>builder()
                .code(200)
                .result(response)
                .build();
    }
}
