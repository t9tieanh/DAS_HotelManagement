package com.dashotel.hotelmanagement.service.room;

import com.dashotel.hotelmanagement.dto.request.hotel.HotelImageRequest;
import com.dashotel.hotelmanagement.dto.request.room.RoomTypeImageRequest;
import com.dashotel.hotelmanagement.dto.response.CreationResponse;
import com.dashotel.hotelmanagement.dto.response.room.RoomTypeResponse;
import com.dashotel.hotelmanagement.entity.hotel.HotelEntity;
import com.dashotel.hotelmanagement.entity.hotel.HotelImageEntity;
import com.dashotel.hotelmanagement.entity.room.RoomAvailabilityEntity;
import com.dashotel.hotelmanagement.entity.room.RoomImageEntity;
import com.dashotel.hotelmanagement.entity.room.RoomTypeEntity;
import com.dashotel.hotelmanagement.enums.RoomStatusEnum;
import com.dashotel.hotelmanagement.exception.CustomException;
import com.dashotel.hotelmanagement.exception.ErrorCode;
import com.dashotel.hotelmanagement.mapper.RoomImageMapper;
import com.dashotel.hotelmanagement.mapper.RoomTypeMapper;
import com.dashotel.hotelmanagement.repository.room.RoomTypeRepository;
import com.dashotel.hotelmanagement.service.other.FileStorageService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class RoomTypeService {
    RoomTypeRepository roomTypeRepository;

    RoomTypeMapper roomTypeMapper;
    RoomImageMapper roomImageMapper;

    FileStorageService fileStorageService;
    public List<RoomTypeEntity> getRoomAvailable(LocalDate checkIn, LocalDate checkOut, Long numAdults, Long numRooms) {
        Long numDays = ChronoUnit.DAYS.between(checkIn, checkOut);
        List<String> roomTypeIds = roomTypeRepository.findAvailableRooms(checkIn, checkOut, numRooms, numDays);

        List<RoomTypeEntity> roomList = new ArrayList<>();

       for(String roomId : roomTypeIds) {
           RoomTypeEntity room = roomTypeRepository.findById(roomId).orElseThrow(() -> new RuntimeException("Khong tim thay phong"));
           if(room.getMaxOccupation() >= numAdults && room.getRoomStatus().equals(RoomStatusEnum.BOOKED)) {
               roomList.add(room);
           }
       }
        return roomList;
    }

    public List<RoomTypeResponse> getRoomByHotelId(String hotelId) {
        List<RoomTypeEntity> rooms = roomTypeRepository.getRoomTypeEntitiesByHotelId(hotelId);
        return rooms.stream()
                .map(roomTypeMapper::toRoomTypeDTO)
                .collect(Collectors.toList());
    }

    @Transactional
    public CreationResponse addImageForRoomType(RoomTypeImageRequest request) throws IOException {

        RoomTypeEntity roomType = roomTypeRepository.findById(request.getRoomTypeId())
                .orElseThrow(() -> new CustomException(ErrorCode.ROOM_NOT_FOUND));

        RoomImageEntity roomImageEntity = roomImageMapper.toEntity(request);

        // tiến hành lưu ảnh
        roomImageEntity.setImgUrl(fileStorageService.storeImage(request.getImg()));

        roomType.getImages().add(roomImageEntity);
        roomImageEntity.setRoomType(roomType);

        if (request.getIsAvatar())
            roomType.setAvatar(roomImageEntity.getImgUrl());

        roomType = roomTypeRepository.save(roomType);

        return CreationResponse.builder()
                .id(roomType.getId())
                .isSuccess(true)
                .build();
    }
}
