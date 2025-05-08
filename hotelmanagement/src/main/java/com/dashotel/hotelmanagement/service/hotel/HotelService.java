package com.dashotel.hotelmanagement.service.hotel;

import com.dashotel.hotelmanagement.dto.request.hotel.HotelCreationRequest;
import com.dashotel.hotelmanagement.dto.request.hotel.HotelImageRequest;
import com.dashotel.hotelmanagement.dto.response.common.CreationResponse;
import com.dashotel.hotelmanagement.dto.response.hotel.*;
import com.dashotel.hotelmanagement.dto.response.paging.PagingResponse;
import com.dashotel.hotelmanagement.entity.hotel.AddressEntity;
import com.dashotel.hotelmanagement.entity.hotel.HotelEntity;
import com.dashotel.hotelmanagement.entity.hotel.HotelImageEntity;
import com.dashotel.hotelmanagement.entity.room.RoomTypeEntity;
import com.dashotel.hotelmanagement.entity.service.HotelFacilityEntity;
import com.dashotel.hotelmanagement.enums.HotelImageEnum;
import com.dashotel.hotelmanagement.exception.CustomException;
import com.dashotel.hotelmanagement.exception.ErrorCode;
import com.dashotel.hotelmanagement.mapper.AddressMapper;
import com.dashotel.hotelmanagement.mapper.HotelFacilityMapper;
import com.dashotel.hotelmanagement.mapper.HotelImageMapper;
import com.dashotel.hotelmanagement.mapper.HotelMapper;
import com.dashotel.hotelmanagement.repository.HotelFacilityRepository;
import com.dashotel.hotelmanagement.repository.hotel.HotelImageRepository;
import com.dashotel.hotelmanagement.repository.hotel.HotelRepository;
import com.dashotel.hotelmanagement.service.other.FileStorageService;
import com.dashotel.hotelmanagement.service.room.RoomTypeService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class HotelService {
    HotelRepository hotelRepository;
    HotelFacilityRepository hotelFacilityRepository;
    HotelImageRepository hotelImageRepository;

    HotelMapper hotelMapper;
    AddressMapper addressMapper;
    HotelImageMapper hotelImageMapper;
    HotelFacilityMapper hotelFacilityMapper;

    RoomTypeService roomTypeService;
    FileStorageService fileStorageService;

    // lấy list category của tất cả image mà hotel đó có
    public List<HotelImageTypeCountReponse> getHotelImageCategory(String hotelId) {
        return hotelRepository.countImagesByTypeForHotel(hotelId);
    }

    //lấy list image theo hotelid và imageType
    public HotelImageResponse getHotelImages(String hotelId, HotelImageEnum hotelImageEnum) {
        List<HotelImageEntity> hotelImageEntities = hotelImageRepository.findByHotelIdAndImageType(hotelId, hotelImageEnum);

        return HotelImageResponse.builder()
                .imgs(
                        hotelImageEntities
                                .stream()
                                .map(HotelImageEntity::getImgUrl)
                                .collect(Collectors.toList())
                )
                .build();
    }

    public HotelDestailResponse getHotelDetail (String hotelId, LocalDate checkIn, LocalDate checkOut, Long numAdults, Long numRooms) {
        HotelEntity hotelEntity = hotelRepository.findById(hotelId).orElseThrow(
                () -> new CustomException(ErrorCode.USER_NOT_FOUND)
        );

        HotelDestailResponse hotelDestailResponse = hotelMapper.toResponse(hotelEntity);

        // lấy image
        hotelDestailResponse.setImgs( hotelEntity.getImages()
                .stream().limit(4)
                .map(HotelImageEntity::getImgUrl)
                .collect(Collectors.toList()));

        // lấy facility
        hotelDestailResponse.setFacilities(new ArrayList<>());

        hotelEntity.getFacilities().stream().forEach(
                facility -> {
                    FacilitiesResponse facilitiesResponse = hotelFacilityMapper.toResponse(facility);
                    facilitiesResponse.setCategoryName(facility.getCategory().getNameVi());

                    hotelDestailResponse.getFacilities().add(facilitiesResponse);
                }
        );

        hotelDestailResponse.setRooms(roomTypeService.getRoomByHotelId(hotelId, checkIn, checkOut, numAdults, numRooms));

        return hotelDestailResponse;
    }


    @Transactional
    public CreationResponse addHotel(HotelCreationRequest request) throws IOException {

        // add các tiện nghi của hotel
        List<HotelFacilityEntity> hotelFacilityEntities =
                request.getFacilitiesIds().stream()
                        .map(id -> hotelFacilityRepository.findById(id)
                                .orElseThrow(() -> new CustomException(ErrorCode.USER_NOT_FOUND)))
                        .collect(Collectors.toList());

        HotelEntity hotelEntity = hotelMapper.toEntity(request);
        hotelEntity.setAddress(
                AddressEntity.builder()
                        .concrete(request.getAddressConcrete())
                        .commune(request.getAddressCommune())
                        .district(request.getAddressDistrict())
                        .city(request.getAddressCity())
                        .build()
        );

        // tiến hành lưu avartar nếu có
        if (request.getImg() != null) {
            hotelEntity.setAvatar(fileStorageService.storeImage(request.getImg()));
            hotelEntity.setImages(List.of
                    (HotelImageEntity.builder()
                        .imageType(HotelImageEnum.AVARTAR)
                        .imgUrl(hotelEntity.getAvatar())
                        .hotel(hotelEntity)
                        .build()));
        }

        // lưu các facilities
        hotelEntity.setFacilities(hotelFacilityEntities);

        hotelEntity = hotelRepository.save(hotelEntity);
        return CreationResponse.builder()
                .id(hotelEntity.getId())
                .isSuccess(true)
                .build();
    }


    @Transactional
    public CreationResponse addImageForHotel(HotelImageRequest request) throws IOException {
        HotelEntity hotelEntity = hotelRepository.findById(request.getHotelId())
                .orElseThrow(() -> new CustomException(ErrorCode.USER_NOT_FOUND));

        HotelImageEntity hotelImageEntity = hotelImageMapper.toEntity(request);
        // tiến hành lưu ảnh
        hotelImageEntity.setImgUrl(fileStorageService.storeImage(request.getImg()));

        hotelEntity.getImages().add(hotelImageEntity);
        hotelImageEntity.setHotel(hotelEntity);

        // nếu ảnh là ảnh đại diện
        if (request.getIsAvartar())
            hotelEntity.setAvatar(hotelImageEntity.getImgUrl());

        hotelEntity = hotelRepository.save(hotelEntity);

        return CreationResponse.builder()
                .id(hotelEntity.getId())
                .isSuccess(true)
                .build();
    }


    public List<HotelResultResponse> getHotelBySearch(LocalDate checkIn, LocalDate checkOut, Long numAdults, Long numRooms) {

        List<RoomTypeEntity> roomList = roomTypeService.getRoomAvailable(checkIn, checkOut, numAdults, numRooms);
        if(roomList == null || roomList.isEmpty()) {
            return Collections.emptyList();
        }

        Set<String> hotelIds = roomList.stream()
                .map(RoomTypeEntity::getHotel)
                .filter(Objects::nonNull)
                .map(HotelEntity::getId)
                .filter(Objects::nonNull)
                .collect(Collectors.toSet());

        List<HotelEntity> hotelEntities = hotelRepository.findAllById(hotelIds);
        if(hotelEntities.isEmpty()) {
            return Collections.emptyList();
        }

        return hotelEntities.stream().map(entity -> {
            HotelResultResponse hotel = new HotelResultResponse();
            hotel.setId(entity.getId());
            hotel.setRating(entity.getRating());
            hotel.setAddress(entity.getAddress() != null ? addressMapper.toDTO(entity.getAddress()) : null);
            hotel.setName(entity.getName());
            hotel.setAvatar(entity.getAvatar());

            OptionalDouble minPriceOpt = entity.getRooms().stream()
                    .mapToDouble(RoomTypeEntity::getPrice)
                    .min();

            double minPrice = minPriceOpt.orElse(0.0);
            hotel.setMinRoomPrice(minPrice);

            return hotel;
        }).collect(Collectors.toList());

    }

    public PagingResponse<HotelResultResponse> getHotelBySearch(LocalDate checkIn, LocalDate checkOut, Long numAdults, Long numRooms,
                                           int page, int size) {

        List<RoomTypeEntity> roomList = roomTypeService.getRoomAvailable(checkIn, checkOut, numAdults, numRooms);
        if(roomList == null || roomList.isEmpty()) {
            return PagingResponse.<HotelResultResponse>builder()
                    .totalElements(0)
                    .totalPages(1)
                    .content(Collections.emptyList())
                    .build();
        }

        Set<String> hotelIds = roomList.stream()
                .map(RoomTypeEntity::getHotel)
                .filter(Objects::nonNull)
                .map(HotelEntity::getId)
                .filter(Objects::nonNull)
                .collect(Collectors.toSet());

        Pageable pageable = PageRequest.of(page, size);
        Page<HotelEntity> hotelEntitiesPage = hotelRepository.findByIdIn(hotelIds, pageable);

        List<HotelResultResponse> result = hotelEntitiesPage.getContent().stream()
                .map(entity -> {
                    HotelResultResponse hotel = hotelMapper.toSearchResponse(entity);
                    hotel.setAddress(entity.getAddress() != null ? addressMapper.toDTO(entity.getAddress()) : null);

                    OptionalDouble minPriceOpt = entity.getRooms().stream()
                            .mapToDouble(RoomTypeEntity::getPrice)
                            .min();

                    double minPrice = minPriceOpt.orElse(0.0);
                    hotel.setMinRoomPrice(minPrice);

                    return hotel;
                })
                .collect(Collectors.toList());

        return PagingResponse.<HotelResultResponse>builder()
                .totalElements(hotelEntitiesPage.getTotalElements())
                .totalPages(hotelEntitiesPage.getTotalPages())
                .content(result)
                .build();
    }
}
