package com.dashotel.hotelmanagement.service.impl.hotel;

import com.dashotel.hotelmanagement.dto.common.AddressDTO;
import com.dashotel.hotelmanagement.entity.hotel.HotelEntity;
import com.dashotel.hotelmanagement.mapper.AddressMapper;
import com.dashotel.hotelmanagement.utils.AddressMatcher;
import com.dashotel.hotelmanagement.repository.hotel.HotelRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class HotelSearchService {

    HotelRepository hotelRepository;

    AddressMapper mapper;
    AddressMatcher matcher;

    // dùng cho tìm kiếm hotel
    public Set<HotelEntity> findByAddress (AddressDTO address) {
        var hotelLst = hotelRepository.findAll();

        return
                hotelLst.stream().filter(
                        hotel -> matcher.isAddressSimilar(
                                mapper.toDTO(hotel.getAddress()), address)
                ).collect(Collectors.toSet());
    }
}
