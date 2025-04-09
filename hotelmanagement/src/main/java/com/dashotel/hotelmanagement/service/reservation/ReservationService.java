package com.dashotel.hotelmanagement.service.reservation;

import com.dashotel.hotelmanagement.dto.common.DiscountDTO;
import com.dashotel.hotelmanagement.dto.request.reservation.initial.InitialReservationRequest;
import com.dashotel.hotelmanagement.dto.request.reservation.initial.ReservationDetailRequest;
import com.dashotel.hotelmanagement.dto.request.reservation.updateinfo.UpdateReservationInfoRequest;
import com.dashotel.hotelmanagement.dto.response.CreationResponse;
import com.dashotel.hotelmanagement.dto.response.reservation.InitialReservationResponse;
import com.dashotel.hotelmanagement.dto.response.reservation.ReservationStepResponse;
import com.dashotel.hotelmanagement.dto.response.reservation.common.ReservationDetailResponse;
import com.dashotel.hotelmanagement.dto.response.reservation.common.ReservationResponse;
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
import com.dashotel.hotelmanagement.mapper.DiscountMapper;
import com.dashotel.hotelmanagement.mapper.ReservationMapper;
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
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.ParseException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
@Slf4j
public class ReservationService {
    ReservationRepository reservationRepository;
    CustomerRepository customerRepository;
    DiscountRepository discountRepository;

    RoomTypeService roomTypeService;

    RoomOccupantMapper roomOccupantMapper;
    DiscountMapper discountMapper;
    ReservationMapper reservationMapper;
    JwtUtils jwtUtils;

    private List <ReservationDetailEntity> getRoomAvailable (List<ReservationDetailRequest> reservationDetailRequests, LocalDate checkInDate, LocalDate checkOutDate, ReservationEntity reservationEntity) {
        List <ReservationDetailEntity> reservationDetailEntities = new ArrayList<>();

        for (ReservationDetailRequest reservationDetailRequest : reservationDetailRequests) {
            RoomTypeEntity roomType = roomTypeService.getRoomAvailable(reservationDetailRequest.getRoomId(), checkInDate,
                    checkOutDate , reservationDetailRequest.getQuantity());
            if (roomType == null) return null;

            ReservationDetailEntity reservationDetail = ReservationDetailEntity.builder()
                    .roomType(roomType)
                    .quantity(reservationDetailRequest.getQuantity())
                    .reservation(reservationEntity) // tạo mối liên kết
                    .build();
            reservationDetailEntities.add(reservationDetail);
        }

        return reservationDetailEntities;
    }

    @Transactional
    public InitialReservationResponse createReservation (InitialReservationRequest request) throws ParseException {
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
        List <ReservationDetailEntity> reservationDetailEntities = getRoomAvailable(request.getReservationDetails(),
                request.getCheckIn(), request.getCheckOut(), reservationEntity);

        if (reservationDetailEntities == null) throw new CustomException(ErrorCode.ROOM_NOT_AVAILABLE);

        reservationEntity.setReservationDetail(reservationDetailEntities);

        // set trạng thái cho reservation
        reservationEntity = reservationRepository.save(reservationEntity);
        return InitialReservationResponse.builder()
                .expireDateTime(reservationEntity.getExpireDateTime())
                .reservationId(reservationEntity.getId())
                .build();
    }

    private Boolean checkRoomAvailable (List<ReservationDetailEntity> reservationDetailRequests, LocalDate checkInDate, LocalDate checkOutDate) {

        for (ReservationDetailEntity reservationDetail : reservationDetailRequests) {
            RoomTypeEntity roomType = roomTypeService.getRoomAvailable(reservationDetail.getRoomType().getId(), checkInDate,
                    checkOutDate , reservationDetail.getQuantity());
            if (roomType == null) return false;
        }

        return true;
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

        // check xem room còn avaiable hay không
        if (!checkRoomAvailable(reservationEntity.getReservationDetail(),
                reservationEntity.getCheckIn(), reservationEntity.getCheckOut()))
            throw new CustomException(ErrorCode.ROOM_NOT_AVAILABLE);

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

        // tạm thời comment lại dùng cho lần sau -> sai nghiệp vụ
        // tiến hành cập nhật lại available room
//        for (ReservationDetailEntity reservationDetail : reservationEntity.getReservationDetail()) {
//            RoomTypeEntity roomType= reservationDetail.getRoomType();
//            List<RoomAvailabilityEntity> roomAvailabilityLst = roomAvailabilityRepository.findByAvailableRoomType(
//                    reservationEntity.getCheckIn(), reservationEntity.getCheckOut(), roomType.getId()
//            );
//
//            for (RoomAvailabilityEntity roomAvailability : roomAvailabilityLst) {
//                if ((roomAvailability.getTotalRoom() - roomAvailability.getBookedRoom()) < reservationDetail.getQuantity()) {
//                    throw new CustomException(ErrorCode.ROOM_NOT_AVAILABLE); // phòng này đã không còn trống nữa -> roll back transaction
//                } else roomAvailability.setBookedRoom(roomAvailability.getBookedRoom() + reservationDetail.getQuantity());
//                // nếu phòng vẫn còn thì cập nhật
//            }
//
//            roomAvailabilityRepository.saveAll(roomAvailabilityLst);
//        }

        // set trạng thái cho reservation
        reservationEntity.setStatus(BookingStatusEnum.UNPAID);


        reservationEntity = reservationRepository.save(reservationEntity);
        return CreationResponse.builder()
                .id(reservationEntity.getId())
                .isSuccess(true)
                .build();
    }


    public ReservationStepResponse getCurrentStep (String reservationId) {
        ReservationEntity reservation = reservationRepository.findById(reservationId)
                .orElseThrow(() -> new CustomException(ErrorCode.USER_NOT_FOUND));

        //check xem còn trong thời gian transaction không
        if (reservation.getExpireDateTime().isBefore(LocalDateTime.now()))
            throw new CustomException(ErrorCode.BOOKING_TIMEOUT);

        // lấy thông tin room, hotel, giá tiền
        List<ReservationDetailResponse> roomInfoForReservationResponses = new ArrayList<>();
        reservation.getReservationDetail().forEach(
                reservationDetail -> {
                    ReservationDetailResponse roomInfoForReservationResponse = roomTypeService.getRoomInfoForReservation(
                            reservationDetail.getRoomType().getId()
                    ); // lấy thông tin room, hotel (price, ...) từ room service
                    roomInfoForReservationResponse.setQuantity(reservationDetail.getQuantity());
                    roomInfoForReservationResponses.add(roomInfoForReservationResponse);
                }
        );

        // lấy thông tin
        List<DiscountDTO> discounts = reservation.getDiscounts().stream()
                .map(discountMapper::toDiscountDTOForReservation)
                .collect(Collectors.toList());


        return ReservationStepResponse.builder()
                .currentStep(reservation.getStatus().getStep())
                .description(reservation.getStatus().getDescription())
                .expireDateTime(reservation.getExpireDateTime())
                .checkIn(reservation.getCheckIn())
                .checkOut(reservation.getCheckOut())
                .discounts(discounts)
                .reservationDetail(roomInfoForReservationResponses)
                .totalPrice(100000.0)
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


    @Transactional
    public ReservationResponse updateReservationIsSuccess(String reservationId) {
        try {
            ReservationEntity reservation = reservationRepository.findById(reservationId)
                    .orElseThrow(() -> new IllegalArgumentException("Đơn đặt không tồn tại"));
            reservation.setStatus(BookingStatusEnum.PAID);

            reservation.setReservationDate(LocalDate.now());

            reservation = reservationRepository.save(reservation);

            return reservationMapper.toResponse(reservation);
        }
        catch (IllegalArgumentException e) {
            log.error("Lỗi với reservation {}", e.getMessage());
            throw e;
        }
        catch (Exception e) {
            log.error("Lỗi khi cập nhật reservation", e);
            throw new RuntimeException("Lỗi server khi cập nhật");
        }
    }

}
