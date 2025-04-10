package com.dashotel.hotelmanagement.repository.reservation;

import com.dashotel.hotelmanagement.entity.booking.ReservationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReservationRepository extends JpaRepository<ReservationEntity, String> {
    List<ReservationEntity> findAllByCustomerId(String customerId);
}
