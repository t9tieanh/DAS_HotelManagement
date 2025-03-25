package com.dashotel.hotelmanagement.repository.hotel;

import com.dashotel.hotelmanagement.entity.hotel.HotelEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HotelRepository extends JpaRepository<HotelEntity, String> {
}
