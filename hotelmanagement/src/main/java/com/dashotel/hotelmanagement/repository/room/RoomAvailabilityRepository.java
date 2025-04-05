package com.dashotel.hotelmanagement.repository.room;

import com.dashotel.hotelmanagement.entity.room.RoomAvailabilityEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface RoomAvailabilityRepository extends JpaRepository<RoomAvailabilityEntity, String> {
    @Query("SELECT ra FROM RoomAvailabilityEntity ra " +
            "WHERE ra.availableDate >= :checkIn " +
            "AND ra.availableDate < :checkOut")
    List<RoomAvailabilityEntity> findByAvailableDateBetween(
            @Param("checkIn") LocalDate checkIn,
            @Param("checkOut") LocalDate checkOut);

    @Query("SELECT ra FROM RoomAvailabilityEntity ra " +
            "WHERE ra.availableDate >= :checkIn " +
            "AND ra.availableDate < :checkOut " +
            "AND ra.roomType.id = :roomId")
    List<RoomAvailabilityEntity> findByAvailableRoomType(
            @Param("checkIn") LocalDate checkIn,
            @Param("checkOut") LocalDate checkOut,
            @Param("roomId") String roomId);
}
