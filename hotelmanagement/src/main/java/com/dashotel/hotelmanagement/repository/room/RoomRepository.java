package com.dashotel.hotelmanagement.repository.room;

import com.dashotel.hotelmanagement.entity.room.RoomEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoomRepository extends JpaRepository<RoomEntity, String> {
}
