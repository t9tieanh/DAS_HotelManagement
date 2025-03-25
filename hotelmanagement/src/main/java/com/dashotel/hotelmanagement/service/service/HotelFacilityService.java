package com.dashotel.hotelmanagement.service.service;

import com.dashotel.hotelmanagement.dto.request.CustomerRequestDTO;
import com.dashotel.hotelmanagement.dto.request.service.HotelFacilityRequest;
import com.dashotel.hotelmanagement.dto.response.CreationResponse;
import com.dashotel.hotelmanagement.dto.response.CustomerResponseDTO;
import com.dashotel.hotelmanagement.entity.service.HotelFacilityEntity;
import com.dashotel.hotelmanagement.entity.user.CustomerEntity;
import com.dashotel.hotelmanagement.enums.HotelFacilityCategory;
import com.dashotel.hotelmanagement.mapper.HotelFacilityMapper;
import com.dashotel.hotelmanagement.repository.HotelFacilityRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class HotelFacilityService {
    HotelFacilityRepository hotelFacilityRepository;

    HotelFacilityMapper mapper;

    @Transactional
    public CreationResponse addHotelFacility (HotelFacilityRequest request) {

        HotelFacilityEntity hotelFacilityEntity = mapper.toEntity(request);

        hotelFacilityEntity = hotelFacilityRepository.save(hotelFacilityEntity);

        return CreationResponse.builder()
                .isSuccess(true)
                .id(hotelFacilityEntity.getId())
                .build();
    }

}
