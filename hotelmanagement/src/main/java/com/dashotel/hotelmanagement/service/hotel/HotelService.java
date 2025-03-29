package com.dashotel.hotelmanagement.service.hotel;

import com.dashotel.hotelmanagement.dto.request.hotel.HotelCreationRequest;
import com.dashotel.hotelmanagement.dto.request.hotel.HotelImageRequest;
import com.dashotel.hotelmanagement.dto.request.hotel.HotelImageUpdationRequest;
import com.dashotel.hotelmanagement.dto.response.CreationResponse;
import com.dashotel.hotelmanagement.dto.response.hotel.FacilitiesResponse;
import com.dashotel.hotelmanagement.dto.response.hotel.HotelDestailResponse;
import com.dashotel.hotelmanagement.dto.response.hotel.HotelImageResponse;
import com.dashotel.hotelmanagement.dto.response.hotel.HotelImageTypeCountReponse;
import com.dashotel.hotelmanagement.entity.hotel.HotelEntity;
import com.dashotel.hotelmanagement.entity.hotel.HotelImageEntity;
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
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
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

    public HotelDestailResponse getHotelDetail (String hotelId) {
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

        return hotelDestailResponse;
    }


    @Transactional
    public CreationResponse addHotel(HotelCreationRequest request) {

        // add các tiện nghi của hotel
        List<HotelFacilityEntity> hotelFacilityEntities =
                request.getFacilitiesIds().stream()
                        .map(id -> hotelFacilityRepository.findById(id)
                                .orElseThrow(() -> new CustomException(ErrorCode.USER_NOT_FOUND)))
                        .collect(Collectors.toList());


        HotelEntity hotelEntity = hotelMapper.toEntity(request);
        hotelEntity.setAddress(
                addressMapper.toEntity(request.getAddress())
        );

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
            hotelEntity.setAvartar(hotelImageEntity.getImgUrl());

        hotelEntity = hotelRepository.save(hotelEntity);

        return CreationResponse.builder()
                .id(hotelEntity.getId())
                .isSuccess(true)
                .build();
    }
}
