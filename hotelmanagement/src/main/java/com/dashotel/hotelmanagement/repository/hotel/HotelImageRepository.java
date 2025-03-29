package com.dashotel.hotelmanagement.repository.hotel;

import com.dashotel.hotelmanagement.entity.hotel.HotelImageEntity;
import com.dashotel.hotelmanagement.enums.HotelImageEnum;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface HotelImageRepository extends JpaRepository<HotelImageEntity, String> {
    List<HotelImageEntity> findByHotelIdAndImageType(String hotelId, HotelImageEnum hotelImageType);
}
