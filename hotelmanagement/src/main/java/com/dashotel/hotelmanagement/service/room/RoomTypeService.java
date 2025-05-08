package com.dashotel.hotelmanagement.service.room;

import com.dashotel.hotelmanagement.dto.request.room.OpenRoomRequest;
import com.dashotel.hotelmanagement.dto.request.room.RoomTypeCreationRequest;
import com.dashotel.hotelmanagement.dto.request.room.RoomTypeImageRequest;
import com.dashotel.hotelmanagement.dto.response.common.CreationResponse;
import com.dashotel.hotelmanagement.dto.response.reservation.common.ReservationDetailResponse;
import com.dashotel.hotelmanagement.dto.response.room.RoomTypeResponse;
import com.dashotel.hotelmanagement.entity.hotel.HotelEntity;
import com.dashotel.hotelmanagement.entity.room.RoomAvailabilityEntity;
import com.dashotel.hotelmanagement.entity.room.RoomTypeEntity;
import com.dashotel.hotelmanagement.enums.RoomStatusEnum;
import com.dashotel.hotelmanagement.exception.CustomException;
import com.dashotel.hotelmanagement.exception.ErrorCode;
import com.dashotel.hotelmanagement.mapper.AddressMapper;
import com.dashotel.hotelmanagement.mapper.RoomTypeMapper;
import com.dashotel.hotelmanagement.repository.hotel.HotelRepository;
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
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class RoomTypeService {

    RoomTypeRepository roomTypeRepository;
    HotelRepository hotelRepository;
    FileStorageService fileStorageService;

    AddressMapper addressMapper;
    RoomTypeMapper roomTypeMapper;

    public List<RoomTypeEntity> getRoomAvailable(LocalDate checkIn, LocalDate checkOut, Long numAdults, Long numRooms) {
        Long numDays = ChronoUnit.DAYS.between(checkIn, checkOut);
        List<String> roomTypeIds = roomTypeRepository.findAvailableRooms(checkIn, checkOut, numRooms, numDays);

        List<RoomTypeEntity> roomList = roomTypeRepository.findAllById(roomTypeIds);

        return roomList.stream()
                .filter(room -> room.getMaxOccupation() >= numAdults && room.getRoomStatus().equals(RoomStatusEnum.AVAILABLE))
                .collect(Collectors.toList());
    }

    public List<RoomTypeResponse> getRoomByHotelId(String hotelId, LocalDate checkIn, LocalDate checkOut, Long numAdults, Long numRooms) {
        List<String> roomTypeIds = roomTypeRepository.findAvailableRooms(checkIn, checkOut, numAdults, numRooms);

        List<RoomTypeEntity> roomList = roomTypeRepository.findAllById(roomTypeIds);

        roomList = roomList.stream()
                .filter(room -> room.getMaxOccupation() >= numAdults && room.getRoomStatus().equals(RoomStatusEnum.AVAILABLE)
                        && room.getHotel().getId().equals(hotelId))
                .collect(Collectors.toList());

        return roomList.stream()
                .map(roomTypeMapper::toRoomTypeDTO)
                .collect(Collectors.toList());
    }

    @Transactional
    public CreationResponse openRoom(OpenRoomRequest request) {

        RoomTypeEntity roomTypeEntity = roomTypeRepository.findById(request.getRoomTypeId())
                .orElseThrow(() -> new RuntimeException("Not room found"));

        if (roomTypeEntity.getRoomAvailabilities() == null)
            roomTypeEntity.setRoomAvailabilities(new ArrayList<>());

        for (LocalDate date = request.getStartDate(); !date.isAfter(request.getEndDate()); date = date.plusDays(1)) {
            roomTypeEntity.getRoomAvailabilities().add(RoomAvailabilityEntity.builder()
                    .roomType(roomTypeEntity)
                    .totalRoom(request.getTotalRooms())
                    .bookedRoom(0L)
                    .status(true)
                    .availableDate(date)
                    .build()
            );
        }

        roomTypeRepository.save(roomTypeEntity);

        return CreationResponse.builder()
                .isSuccess(true)
                .build();
    }


    @Transactional
    public CreationResponse createRoom(RoomTypeCreationRequest request) throws IOException {
        // tiến hành tìm hotel

        HotelEntity hotelEntity = hotelRepository.findById(request.getHotelId())
                .orElseThrow(() -> new RuntimeException("Hotel not found"));

        RoomTypeEntity roomTypeEntity = roomTypeMapper.toEntity(request);

        // tiến hành lưu ảnh
        roomTypeEntity.setAvatar(fileStorageService.storeImage(request.getImg()));
        roomTypeEntity.setRoomStatus(RoomStatusEnum.AVAILABLE);

        // set mối liên kết
        roomTypeEntity.setHotel(hotelEntity);

        roomTypeEntity = roomTypeRepository.save(roomTypeEntity);

        return CreationResponse.builder()
                .isSuccess(true)
                .id(roomTypeEntity.getId())
                .build();
    }


    @Transactional
    public CreationResponse addImageForRoomType(RoomTypeImageRequest request) throws IOException {
        RoomTypeEntity roomType = roomTypeRepository.findById(request.getRoomTypeId())
                .orElseThrow(() -> new CustomException(ErrorCode.ROOM_NOT_FOUND));


        // tiến hành lưu ảnh
        String imgUrl = fileStorageService.storeImage(request.getImg());

        // lưu đường dẫn url vào db
        if (roomType.getImgRoomUrl() == null)
            roomType.setImgRoomUrl(new HashSet<>() {
            });

        roomType.getImgRoomUrl().add(imgUrl);

        if (request.getIsAvatar())
            roomType.setAvatar(imgUrl);


        roomType = roomTypeRepository.save(roomType);

        return CreationResponse.builder()
                .id(roomType.getId())
                .isSuccess(true)
                .build();
    }

    // lấy những phòng có sẵn
    public RoomTypeEntity getRoomAvailable (String roomTypeId, LocalDate checkIn, LocalDate checkOut, Long numRooms) {
        return roomTypeRepository.getRoomAvailable(roomTypeId, checkIn, checkOut, numRooms,
                ChronoUnit.DAYS.between(checkIn, checkOut));
    }

    // lấy thông tin cho việc đặt phòng (reservation)
    public ReservationDetailResponse getRoomInfoForReservation (String roomId) {
        RoomTypeEntity room = roomTypeRepository.findById(roomId)
                .orElseThrow(() -> new CustomException(ErrorCode.ROOM_NOT_FOUND));

        return ReservationDetailResponse.builder()
                .hotelName(room.getHotel().getName())
                .hotelImgUrl(room.getHotel().getAvatar())
                .address(addressMapper.toDTO(room.getHotel().getAddress()))
                .name(room.getName())
                .description(room.getDescription())
                .imgUrl(room.getAvatar())
                .price(room.getPrice())
                .build();
    }
}
