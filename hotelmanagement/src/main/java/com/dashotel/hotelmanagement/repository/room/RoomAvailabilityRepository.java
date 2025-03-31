package com.dashotel.hotelmanagement.repository.room;

import com.dashotel.hotelmanagement.entity.room.RoomAvailabilityEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

public interface RoomAvailabilityRepository extends JpaRepository<RoomAvailabilityEntity, String> {




}
