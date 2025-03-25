package com.dashotel.hotelmanagement.repository;

import com.dashotel.hotelmanagement.entity.service.HotelFacilityEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HotelFacilityRepository extends JpaRepository<HotelFacilityEntity, String> {
}
