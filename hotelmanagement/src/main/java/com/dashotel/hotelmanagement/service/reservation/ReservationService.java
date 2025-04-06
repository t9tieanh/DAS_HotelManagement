package com.dashotel.hotelmanagement.service.reservation;

import com.dashotel.hotelmanagement.dto.request.reservation.initial.InitialReservationRequest;
import com.dashotel.hotelmanagement.dto.request.reservation.initial.ReservationDetailRequest;
import com.dashotel.hotelmanagement.dto.request.reservation.updateinfo.UpdateReservationInfoRequest;
import com.dashotel.hotelmanagement.dto.response.CreationResponse;
import com.dashotel.hotelmanagement.entity.booking.ReservationDetailEntity;
import com.dashotel.hotelmanagement.entity.booking.ReservationEntity;
import com.dashotel.hotelmanagement.entity.booking.RoomOccupantEntity;
import com.dashotel.hotelmanagement.entity.promotion.DiscountEntity;
import com.dashotel.hotelmanagement.entity.room.RoomAvailabilityEntity;
import com.dashotel.hotelmanagement.entity.room.RoomTypeEntity;
import com.dashotel.hotelmanagement.entity.user.CustomerEntity;
import com.dashotel.hotelmanagement.enums.BookingStatusEnum;
import com.dashotel.hotelmanagement.exception.CustomException;
import com.dashotel.hotelmanagement.exception.ErrorCode;
import com.dashotel.hotelmanagement.mapper.RoomOccupantMapper;
import com.dashotel.hotelmanagement.repository.CustomerRepository;
import com.dashotel.hotelmanagement.repository.promotion.DiscountRepository;
import com.dashotel.hotelmanagement.repository.reservation.ReservationRepository;
import com.dashotel.hotelmanagement.repository.reservation.RoomOccupantRepository;
import com.dashotel.hotelmanagement.repository.room.RoomAvailabilityRepository;
import com.dashotel.hotelmanagement.repository.room.RoomTypeRepository;
import com.dashotel.hotelmanagement.service.room.RoomTypeService;
import com.dashotel.hotelmanagement.utils.JwtUtils;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.ParseException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class ReservationService {
    ReservationRepository reservationRepository;
    CustomerRepository customerRepository;
    RoomTypeRepository roomTypeRepository;
    DiscountRepository discountRepository;
    RoomAvailabilityRepository roomAvailabilityRepository;
    RoomOccupantRepository roomOccupantRepository;

    RoomTypeService roomTypeService;

    RoomOccupantMapper roomOccupantMapper;
    JwtUtils jwtUtils;


    @Transactional
    public CreationResponse createReservation (InitialReservationRequest request) throws ParseException {
        String customerId = jwtUtils.getUsername();

        CustomerEntity customerEntity = customerRepository.findByUsername(customerId)
                .orElseThrow(() -> new CustomException(ErrorCode.USER_NOT_FOUND));

        // lấy thông tin từ request

        // tạo reservation
        ReservationEntity reservationEntity = ReservationEntity.builder()
                .customer(customerEntity)
                .checkIn(request.getCheckIn())
                .checkOut(request.getCheckOut())
                .expireDateTime(LocalDateTime.now().plusMinutes(20)) // thời gian transaction là 20 phút
                .status(BookingStatusEnum.CREATED) // set trạng thái mới khởi tạo
                .build();

        // lấy room, nếu room không còn trống nữa thì thown exception
        List <ReservationDetailEntity> reservationDetailEntities = new ArrayList<>();

        for (ReservationDetailRequest reservationDetailRequest : request.getReservationDetails()) {
            RoomTypeEntity roomType = roomTypeService.getRoomAvailable(reservationDetailRequest.getRoomId(), request.getCheckIn(),
                    request.getCheckOut() , reservationDetailRequest.getQuantity());
            if (roomType == null) throw new CustomException(ErrorCode.ROOM_NOT_AVAILABLE);

            ReservationDetailEntity reservationDetail = ReservationDetailEntity.builder()
                    .roomType(roomType)
                    .quantity(reservationDetailRequest.getQuantity())
                    .reservation(reservationEntity)
                    .build();
            reservationDetailEntities.add(reservationDetail);
        }

        reservationEntity.setReservationDetail(reservationDetailEntities);

        // set trạng thái cho reservation
        reservationEntity = reservationRepository.save(reservationEntity);
        return CreationResponse.builder()
                .id(reservationEntity.getId())
                .isSuccess(true)
                .build();
    }

    // method dùng khi user ấn hoàn thành bước 1 (chuyển qua bước 2 (chọn phương thức thanh toán))
    // check lại room available -> lỗi thì roll back
    @Transactional
    public CreationResponse updateInfoReservation (UpdateReservationInfoRequest request) throws ParseException {
        ReservationEntity reservationEntity = reservationRepository.findById(request.getReservationId())
                .orElseThrow(() -> new CustomException(ErrorCode.USER_NOT_FOUND));

        //check xem còn trong thời gian transaction không
        if (reservationEntity.getExpireDateTime().isBefore(LocalDateTime.now()))
            throw new CustomException(ErrorCode.BOOKING_TIMEOUT);

        //check xem reservation đó đã đươ update info hay chưa
        if (reservationEntity.getStatus() != BookingStatusEnum.CREATED)
            throw new CustomException(ErrorCode.STEP_ALREADY_COMPLETED);

        // lấy thông tin từ request
        RoomOccupantEntity roomOccupantEntity = roomOccupantMapper.toEntity(request);
        reservationEntity.setRoomOccupant(roomOccupantEntity);

        // lấy thông tin discount nếu có
        List<DiscountEntity> discountEntityList = new ArrayList<>();

        if (request.getAppliedDiscounts() != null) {
            request.getAppliedDiscounts().forEach(discountId -> {
                  DiscountEntity appliedDiscount = discountRepository.getDiscountAvailable(discountId);
                  if (appliedDiscount == null)
                      throw new CustomException(ErrorCode.DISCOUNT_NOT_AVAILABLE); // không có thì ném exception

                  discountEntityList.add(appliedDiscount);
            });
            reservationEntity.setDiscounts(discountEntityList); // thêm discount
        }

        // tiến hành cập nhật lại available room
        for (ReservationDetailEntity reservationDetail : reservationEntity.getReservationDetail()) {
            RoomTypeEntity roomType= reservationDetail.getRoomType();
            List<RoomAvailabilityEntity> roomAvailabilityLst = roomAvailabilityRepository.findByAvailableRoomType(
                    reservationEntity.getCheckIn(), reservationEntity.getCheckOut(), roomType.getId()
            );

            for (RoomAvailabilityEntity roomAvailability : roomAvailabilityLst) {
                if ((roomAvailability.getTotalRoom() - roomAvailability.getBookedRoom()) < reservationDetail.getQuantity()) {
                    throw new CustomException(ErrorCode.ROOM_NOT_AVAILABLE); // phòng này đã không còn trống nữa -> roll back transaction
                } else roomAvailability.setBookedRoom(roomAvailability.getBookedRoom() + reservationDetail.getQuantity());
                // nếu phòng vẫn còn thì cập nhật
            }

            roomAvailabilityRepository.saveAll(roomAvailabilityLst);
        }

        // set trạng thái cho reservation
        reservationEntity.setStatus(BookingStatusEnum.UNPAID);


        reservationEntity = reservationRepository.save(reservationEntity);
        return CreationResponse.builder()
                .id(reservationEntity.getId())
                .isSuccess(true)
                .build();
    }


    // dùng cho authorization
    public boolean isOwnerOfReservation (String reservationId, String username) {
        ReservationEntity reservation = reservationRepository.findById(reservationId)
                .orElseThrow(() -> new CustomException(ErrorCode.USER_NOT_FOUND));

        CustomerEntity customer = customerRepository.findByUsername(username)
                .orElseThrow(() -> new CustomException(ErrorCode.UNAUTHORIZED));

        // Kiểm tra xem reservation có thuộc về người dùng này không
        return reservation.getCustomer().getId().equals(customer.getId());
    }

}
