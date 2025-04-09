package com.dashotel.hotelmanagement.service.room;

import com.dashotel.hotelmanagement.dto.request.room.RoomAvailabilityRequest;
import com.dashotel.hotelmanagement.dto.response.room.RoomAvailabilityResponse;
import com.dashotel.hotelmanagement.entity.booking.ReservationDetailEntity;
import com.dashotel.hotelmanagement.entity.booking.ReservationEntity;
import com.dashotel.hotelmanagement.entity.room.RoomAvailabilityEntity;
import com.dashotel.hotelmanagement.mapper.RoomAvailabilityMapper;
import com.dashotel.hotelmanagement.mapper.RoomTypeMapper;
import com.dashotel.hotelmanagement.repository.reservation.ReservationRepository;
import com.dashotel.hotelmanagement.repository.room.RoomAvailabilityRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
@Slf4j
public class RoomAvailabilityService {
    ReservationRepository reservationRepository;
    RoomAvailabilityRepository roomAvailabilityRepository;

    @Transactional
    public Boolean updateRoomAvail(String reservationId) {
        try {
            ReservationEntity reservation = reservationRepository.findById(reservationId)
                    .orElseThrow(() -> new IllegalArgumentException("Đơn đặt không tồn tại"));

            if (reservation == null || reservation.getReservationDetail().isEmpty()) {
                return false; // Không tìm thấy reservation
            }

            ReservationDetailEntity detail = reservation.getReservationDetail().getFirst();
            if(detail.getRoomType() == null || detail.getRoomType().getRoomAvailabilities() == null) {
                return false;
            }

            Long bookedQuantity = detail.getQuantity();
            for (RoomAvailabilityEntity entity : detail.getRoomType().getRoomAvailabilities()) {
                if(entity.getBookedRoom() == null) {
                    entity.setBookedRoom(0L);
                }
                entity.setBookedRoom(entity.getBookedRoom() + bookedQuantity);
                roomAvailabilityRepository.save(entity);
            }

            return true;
        } catch (Exception e) {
            log.error("Lỗi khi cập nhật phòng: " + e.getMessage());
            return false;
        }
    }
}
