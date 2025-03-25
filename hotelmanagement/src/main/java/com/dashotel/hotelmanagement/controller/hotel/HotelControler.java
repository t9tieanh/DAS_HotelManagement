package com.dashotel.hotelmanagement.controller.hotel;
import com.dashotel.hotelmanagement.dto.request.hotel.HotelCreationRequest;
import com.dashotel.hotelmanagement.dto.request.hotel.HotelImageRequest;
import com.dashotel.hotelmanagement.dto.response.ApiResponse;
import com.dashotel.hotelmanagement.dto.response.CreationResponse;
import com.dashotel.hotelmanagement.dto.response.hotel.HotelDestailResponse;
import com.dashotel.hotelmanagement.service.hotel.HotelService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("/hotel")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class HotelControler {

    HotelService hotelService;

    @GetMapping("detail/{hotelId}")
    ApiResponse<HotelDestailResponse> getHotelDetail(@PathVariable String hotelId) {
        var result = hotelService.getHotelDetail(hotelId);
        return ApiResponse.<HotelDestailResponse>builder()
                .code(200)
                .result(result)
                .build();
    }

    @PostMapping
    ApiResponse<CreationResponse> addHotel(@RequestBody HotelCreationRequest request) {
        var result = hotelService.addHotel(request);
        return ApiResponse.<CreationResponse>builder()
                .code(200)
                .result(result)
                .build();
    }

    @PostMapping(value="/update-image", consumes = "multipart/form-data")
    ApiResponse<CreationResponse> addImage(@ModelAttribute HotelImageRequest request) throws IOException {
        var result = hotelService.addImageForHotel(request);

        return ApiResponse.<CreationResponse>builder()
                .code(200)
                .result(result)
                .build();
    }
}
