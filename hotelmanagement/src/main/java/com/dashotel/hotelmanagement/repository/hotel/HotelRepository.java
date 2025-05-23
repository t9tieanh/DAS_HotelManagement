package com.dashotel.hotelmanagement.repository.hotel;

import com.dashotel.hotelmanagement.dto.response.hotel.HotelImageTypeCountReponse;
import com.dashotel.hotelmanagement.entity.hotel.HotelEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Repository
public interface HotelRepository extends JpaRepository<HotelEntity, String> {
    @Query("SELECT new com.dashotel.hotelmanagement.dto.response.hotel.HotelImageTypeCountReponse(hi.imageType, COUNT(hi)) " +
            "FROM HotelImageEntity hi " +
            "WHERE hi.hotel.id = :hotelId " +
            "GROUP BY hi.imageType")
    List<HotelImageTypeCountReponse> countImagesByTypeForHotel(@Param("hotelId") String hotelId);

    @Query("SELECT h FROM HotelEntity h WHERE h.id IN :hotelIds")
    Page<HotelEntity> findByIdIn (@Param("hotelIds") Set<String> hotelIds, Pageable pageable);


    @Query("SELECT h FROM HotelEntity h WHERE h.address.city = :city AND h.address.district = :district")
    List<HotelEntity> findByCityAndDistrict(@Param("city") String city, @Param("district") String district);

    List<HotelEntity> findAll();
}
