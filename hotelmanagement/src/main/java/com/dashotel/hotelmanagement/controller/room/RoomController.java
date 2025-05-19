package com.dashotel.hotelmanagement.controller.room;

import com.dashotel.hotelmanagement.dto.request.room.OpenRoomRequest;
import com.dashotel.hotelmanagement.dto.request.room.RoomTypeCreationRequest;
import com.dashotel.hotelmanagement.dto.request.room.RoomTypeImageRequest;
import com.dashotel.hotelmanagement.dto.response.ApiResponse;
import com.dashotel.hotelmanagement.dto.response.common.CreationResponse;
import com.dashotel.hotelmanagement.service.impl.room.RoomTypeService;
import com.dashotel.hotelmanagement.service.room.IRoomService;
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
    IRoomService roomTypeService;

    @PostMapping(consumes = "multipart/form-data")
    ApiResponse<CreationResponse> createRoom (@ModelAttribute RoomTypeCreationRequest request) throws IOException {
        var result = roomTypeService.createRoom(request);

        return ApiResponse.<CreationResponse>builder()
                .code(200)
                .result(result)
                .build();
    }

    @PostMapping(value="/open-room")
    ApiResponse<CreationResponse> openRoom (@RequestBody OpenRoomRequest request) {
        var result = roomTypeService.openRoom(request);

        return ApiResponse.<CreationResponse>builder()
                .code(200)
                .result(result)
                .build();
    }

    @PostMapping(value="/update-image", consumes = "multipart/form-data")
    ApiResponse<CreationResponse> addImage(@ModelAttribute RoomTypeImageRequest request) throws IOException {
        var result = roomTypeService.addImageForRoomType(request);

        return ApiResponse.<CreationResponse>builder()
                .code(200)
                .result(result)
                .build();
    }
}
