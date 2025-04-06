package com.dashotel.hotelmanagement.repository.room;

import com.dashotel.hotelmanagement.entity.room.RoomTypeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

public interface RoomTypeRepository extends JpaRepository<RoomTypeEntity, String> {
    @Query("SELECT ra.roomType.id FROM RoomAvailabilityEntity ra " +
            "WHERE ra.availableDate >= :checkIn AND ra.availableDate < :checkOut " +
            "AND (ra.totalRoom - ra.bookedRoom) >= :numRooms " +
//            "AND ra.status = true " +
            "GROUP BY ra.roomType.id " +
            "HAVING COUNT(ra.availableDate) = :numDays")
    List<String> findAvailableRooms(@Param("checkIn") LocalDate checkIn,
                                    @Param("checkOut") LocalDate checkOut,
                                    @Param("numRooms") Long numRooms,
                                    @Param("numDays") Long numDays);

    @Query("SELECT r FROM RoomTypeEntity r WHERE r.hotel.id = :hotelId")
    List<RoomTypeEntity> getRoomTypeEntitiesByHotelId(@Param("hotelId") String hotelId);


    @Query("SELECT ra.roomType FROM RoomAvailabilityEntity ra " +
            "WHERE ra.availableDate >= :checkIn " +
            "AND ra.availableDate < :checkOut " +
            "AND (ra.totalRoom - ra.bookedRoom) >= :numRooms " +
            "AND ra.roomType.id = :roomTypeId " +
            "GROUP BY ra.roomType.id " +
            "HAVING COUNT(ra) = :numDays")
    RoomTypeEntity getRoomAvailable(@Param("roomTypeId") String roomTypeId,
                                    @Param("checkIn") LocalDate checkIn,
                                    @Param("checkOut") LocalDate checkOut,
                                    @Param("numRooms") Long numRooms,
                                    @Param("numDays") Long numDays);
}
