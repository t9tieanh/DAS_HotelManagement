package com.dashotel.hotelmanagement.controller.room;

import com.dashotel.hotelmanagement.dto.request.hotel.HotelImageRequest;
import com.dashotel.hotelmanagement.dto.request.room.RoomTypeImageRequest;
import com.dashotel.hotelmanagement.dto.response.ApiResponse;
import com.dashotel.hotelmanagement.dto.response.CreationResponse;
import com.dashotel.hotelmanagement.service.room.RoomTypeService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("/room")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class RoomController {
    RoomTypeService roomTypeService;
    @PostMapping(value="/update-image", consumes = "multipart/form-data")
    ApiResponse<CreationResponse> addImage(@ModelAttribute RoomTypeImageRequest request) throws IOException {
        var result = roomTypeService.addImageForRoomType(request);

        return ApiResponse.<CreationResponse>builder()
                .code(200)
                .result(result)
                .build();
    }
}
