package com.dashotel.hotelmanagement.controller.hotel;
import com.dashotel.hotelmanagement.dto.request.hotel.HotelCreationRequest;
import com.dashotel.hotelmanagement.dto.request.hotel.HotelImageRequest;
import com.dashotel.hotelmanagement.dto.response.ApiResponse;
import com.dashotel.hotelmanagement.dto.response.CreationResponse;
import com.dashotel.hotelmanagement.dto.response.hotel.*;
import com.dashotel.hotelmanagement.dto.response.paging.PagingResponse;
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

    @GetMapping("/search-hotel")
    public ApiResponse<PagingResponse<HotelResultResponse>> getListHotel(@RequestParam(required = true) LocalDate checkIn,
                                                    @RequestParam(required = true) LocalDate checkOut,
                                                    @RequestParam(defaultValue = "1") Long numAdults,
                                                    @RequestParam(defaultValue = "1") Long numRooms,
                                                    @RequestParam(defaultValue = "0") int page,
                                                    @RequestParam(defaultValue = "3") int size) {


        if (checkIn == null || checkOut == null || checkIn.isAfter(checkOut)) {
            throw new IllegalArgumentException("Ngày check-in và check-out không hợp lệ.");
        }

        PagingResponse<HotelResultResponse> hotelResult = hotelService.getHotelBySearch(checkIn, checkOut, numAdults, numRooms, page, size);

        return ApiResponse.<PagingResponse<HotelResultResponse>>builder()
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

    @PostMapping(consumes = "multipart/form-data")
    ApiResponse<CreationResponse> addHotel(@ModelAttribute HotelCreationRequest request) throws IOException {
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
