package com.dashotel.hotelmanagement.controller.hotel;
import com.dashotel.hotelmanagement.dto.request.hotel.HotelCreationRequest;
import com.dashotel.hotelmanagement.dto.request.hotel.HotelImageRequest;
import com.dashotel.hotelmanagement.dto.response.ApiResponse;
import com.dashotel.hotelmanagement.dto.response.CreationResponse;
import com.dashotel.hotelmanagement.dto.response.hotel.HotelDestailResponse;
import com.dashotel.hotelmanagement.dto.response.hotel.HotelImageResponse;
import com.dashotel.hotelmanagement.dto.response.hotel.HotelImageTypeCountReponse;
import com.dashotel.hotelmanagement.dto.response.hotel.HotelResultResponse;
import com.dashotel.hotelmanagement.enums.HotelImageEnum;
import com.dashotel.hotelmanagement.service.hotel.HotelService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/hotel")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class HotelController {

    HotelService hotelService;

    @GetMapping("/result")
    ApiResponse<List<HotelResultResponse>> getListHotelToReservation(@RequestParam LocalDate checkIn,
                                                               @RequestParam LocalDate checkOut,
                                                               @RequestParam Long numAdults,
                                                               @RequestParam Long numRooms) {
        List<HotelResultResponse> hotelResult = hotelService.getHotelBySearch(checkIn, checkOut, numAdults, numRooms);

        return ApiResponse.<List<HotelResultResponse>>builder()
                .code(HttpStatus.OK.value())
                .result(hotelResult)
                .build();
    }

    @GetMapping("detail/{hotelId}")
    ApiResponse<HotelDestailResponse> getHotelDetail(@PathVariable String hotelId) {
        var result = hotelService.getHotelDetail(hotelId);
        return ApiResponse.<HotelDestailResponse>builder()
                .code(200)
                .result(result)
                .build();
    }

    @GetMapping("image-category")
    ApiResponse<HotelImageResponse> getImage(@RequestParam String hotelId,
                                             @RequestParam String imageType) {
        var result = hotelService.getHotelImages(hotelId,HotelImageEnum.fromValue(imageType));
        return ApiResponse.<HotelImageResponse>builder()
                .code(200)
                .result(result)
                .build();
    }

    @GetMapping("image-category/{hotelId}")
    ApiResponse<List<HotelImageTypeCountReponse>> getImageCategory(@PathVariable String hotelId) {
        var result = hotelService.getHotelImageCategory(hotelId);
        return ApiResponse.<List<HotelImageTypeCountReponse>>builder()
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
