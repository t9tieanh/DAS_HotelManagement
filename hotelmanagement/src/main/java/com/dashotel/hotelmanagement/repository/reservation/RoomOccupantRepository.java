package com.dashotel.hotelmanagement.repository.reservation;

import com.dashotel.hotelmanagement.entity.booking.RoomOccupantEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoomOccupantRepository extends JpaRepository<RoomOccupantEntity, String> {
}
