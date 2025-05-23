package com.dashotel.hotelmanagement.service.room;

import com.dashotel.hotelmanagement.dto.request.room.OpenRoomRequest;
import com.dashotel.hotelmanagement.dto.request.room.RoomTypeCreationRequest;
import com.dashotel.hotelmanagement.dto.request.room.RoomTypeImageRequest;
import com.dashotel.hotelmanagement.dto.response.common.CreationResponse;

import java.io.IOException;

public interface IRoomService {
    CreationResponse openRoom(OpenRoomRequest request);
    CreationResponse createRoom(RoomTypeCreationRequest request) throws IOException;
    CreationResponse addImageForRoomType(RoomTypeImageRequest request) throws IOException;
}

