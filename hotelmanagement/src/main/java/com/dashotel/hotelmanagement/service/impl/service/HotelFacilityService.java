package com.dashotel.hotelmanagement.service.impl.service;

import com.dashotel.hotelmanagement.dto.request.service.HotelFacilityRequest;
import com.dashotel.hotelmanagement.dto.response.common.CreationResponse;
import com.dashotel.hotelmanagement.entity.service.HotelFacilityEntity;
import com.dashotel.hotelmanagement.mapper.HotelFacilityMapper;
import com.dashotel.hotelmanagement.repository.HotelFacilityRepository;
import com.dashotel.hotelmanagement.service.service.IFacilityService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class HotelFacilityService implements IFacilityService {
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
