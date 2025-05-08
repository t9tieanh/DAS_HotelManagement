package com.dashotel.hotelmanagement.service.room;

import com.dashotel.hotelmanagement.dto.request.room.RoomAvailabilityRequest;
import com.dashotel.hotelmanagement.dto.response.room.RoomAvailabilityResponse;
import com.dashotel.hotelmanagement.entity.booking.ReservationDetailEntity;
import com.dashotel.hotelmanagement.entity.booking.ReservationEntity;
import com.dashotel.hotelmanagement.entity.room.RoomAvailabilityEntity;
import com.dashotel.hotelmanagement.exception.CustomException;
import com.dashotel.hotelmanagement.exception.ErrorCode;
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

import java.util.List;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
@Slf4j
public class RoomAvailabilityService {
    ReservationRepository reservationRepository;
    RoomAvailabilityRepository roomAvailabilityRepository;

    @Transactional
    public Boolean updateRoomAvailable(String reservationId) {

        ReservationEntity reservation = reservationRepository.findById(reservationId)
                .orElseThrow(() -> new CustomException(ErrorCode.BOOKING_NOT_AVAILABLE));

        if (reservation.getReservationDetail().isEmpty()) {
            throw new CustomException(ErrorCode.BOOKING_NOT_AVAILABLE);
        }

        for (var reservationDetail : reservation.getReservationDetail()) {
            // -> lấy ra những roomavailable của phòng đó, từ checkIn -> checkOut
            List<RoomAvailabilityEntity> roomAvailabilitys = roomAvailabilityRepository.findByAvailableRoomType(
                    reservation.getCheckIn(), reservation.getCheckOut(), reservationDetail.getRoomType().getId()
            );

            for (RoomAvailabilityEntity roomAvailability : roomAvailabilitys) {
                // check lại số lượng phòng
                if (roomAvailability.getBookedRoom() + reservationDetail.getQuantity() > roomAvailability.getTotalRoom()) {
                    throw new CustomException(ErrorCode.ROOM_NOT_ENOUGH);
                }

                roomAvailability.setBookedRoom(
                        roomAvailability.getBookedRoom() + reservationDetail.getQuantity()
                );
            }

            // lưu lại room availility
            roomAvailabilityRepository.saveAll(roomAvailabilitys);
        }

        return true;
    }
}
