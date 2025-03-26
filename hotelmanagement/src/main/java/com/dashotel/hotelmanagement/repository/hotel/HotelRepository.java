package com.dashotel.hotelmanagement.repository.hotel;

import com.dashotel.hotelmanagement.dto.response.hotel.HotelImageTypeCountReponse;
import com.dashotel.hotelmanagement.entity.hotel.HotelEntity;
import com.dashotel.hotelmanagement.entity.hotel.HotelImageEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HotelRepository extends JpaRepository<HotelEntity, String> {
    @Query("SELECT new com.dashotel.hotelmanagement.dto.response.hotel.HotelImageTypeCountReponse(hi.imageType, COUNT(hi)) " +
            "FROM HotelImageEntity hi " +
            "WHERE hi.hotel.id = :hotelId " +
            "GROUP BY hi.imageType")
    List<HotelImageTypeCountReponse> countImagesByTypeForHotel(@Param("hotelId") String hotelId);
}
