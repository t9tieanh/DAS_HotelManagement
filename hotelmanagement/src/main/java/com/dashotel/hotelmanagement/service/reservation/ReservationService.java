package com.dashotel.hotelmanagement.service.reservation;

import com.dashotel.hotelmanagement.dto.common.DiscountDTO;
import com.dashotel.hotelmanagement.dto.common.ResponseDTO;
import com.dashotel.hotelmanagement.dto.request.reservation.initial.ApplyDiscountRequest;
import com.dashotel.hotelmanagement.dto.request.reservation.initial.InitialReservationRequest;
import com.dashotel.hotelmanagement.dto.request.reservation.initial.ReservationDetailRequest;
import com.dashotel.hotelmanagement.dto.request.reservation.updateinfo.UpdateReservationInfoRequest;
import com.dashotel.hotelmanagement.dto.response.CreationResponse;
import com.dashotel.hotelmanagement.dto.response.reservation.ApplyDiscountResponse;
import com.dashotel.hotelmanagement.dto.response.reservation.InitialReservationResponse;
import com.dashotel.hotelmanagement.dto.response.reservation.ReservationStepResponse;
import com.dashotel.hotelmanagement.dto.response.reservation.common.ReservationDetailResponse;
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
import com.dashotel.hotelmanagement.mapper.RoomOccupantMapper;
import com.dashotel.hotelmanagement.repository.CustomerRepository;
import com.dashotel.hotelmanagement.repository.promotion.DiscountRepository;
import com.dashotel.hotelmanagement.repository.reservation.ReservationRepository;
import com.dashotel.hotelmanagement.repository.reservation.RoomOccupantRepository;
import com.dashotel.hotelmanagement.repository.room.RoomAvailabilityRepository;
import com.dashotel.hotelmanagement.repository.room.RoomTypeRepository;
import com.dashotel.hotelmanagement.service.promotion.DiscountService;
import com.dashotel.hotelmanagement.service.room.RoomTypeService;
import com.dashotel.hotelmanagement.utils.JwtUtils;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.ParseException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class ReservationService {
    ReservationRepository reservationRepository;
    CustomerRepository customerRepository;
    DiscountRepository discountRepository;

    RoomTypeService roomTypeService;
    DiscountService discountService;

    RoomOccupantMapper roomOccupantMapper;
    DiscountMapper discountMapper;
    JwtUtils jwtUtils;


    // hàm dùng của createReservation, nếu room vẫn thỏa mãn điều kiện có sẵn và sẵn sàng đặt thì trả về room đó
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
    public CreationResponse updateCustomerInfoReservation (UpdateReservationInfoRequest request) {
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

    // hủy đặt phòng
    public ResponseDTO cancelReservation (String id) {
        ReservationEntity reservationEntity = reservationRepository.findById(id)
                .orElseThrow(() -> new CustomException(ErrorCode.USER_NOT_FOUND));

        reservationEntity.setStatus(BookingStatusEnum.CANCELLED);
        reservationEntity.setExpireDateTime(LocalDateTime.now().minusMinutes(1));

        reservationRepository.save(reservationEntity);
        return ResponseDTO.builder()
                .isSuccess(true)
                .build();
    }


    // lấy thông tin reservation hiện tại
    public ReservationStepResponse getCurrentStep (String reservationId) {
        ReservationEntity reservation = reservationRepository.findById(reservationId)
                .orElseThrow(() -> new CustomException(ErrorCode.USER_NOT_FOUND));

        // check trạng thái của reservation
        if (reservation.getStatus().equals(BookingStatusEnum.CANCELLED))
            throw new CustomException(ErrorCode.BOOKING_CANCELED);

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

        // lấy thông tin discount
        Set<DiscountDTO> discounts = reservation.getDiscounts().stream()
                .map(discountMapper::toDiscountDTOForReservation)
                .collect(Collectors.toSet());


        return ReservationStepResponse.builder()
                .currentStep(reservation.getStatus().getStep())
                .description(reservation.getStatus().getDescription())
                .expireDateTime(reservation.getExpireDateTime())
                .checkIn(reservation.getCheckIn())
                .checkOut(reservation.getCheckOut())
                .discounts(discounts)
                .reservationDetail(roomInfoForReservationResponses)
                .build();
    }


    // lấy tổng tiền của reservation
    public Double getTotalPrice(String reservationId) {
        ReservationEntity reservation = reservationRepository.findById(reservationId)
                .orElseThrow(() -> new CustomException(ErrorCode.BOOKING_NOT_AVAILABLE));

        // Tính tổng giá phòng
        double totalPrice = reservation.getReservationDetail().stream()
                .mapToDouble(detail -> detail.getRoomType().getPrice() * detail.getQuantity())
                .sum();

        // Tính tổng phần trăm giảm giá
        double totalDiscountPercentage = reservation.getDiscounts().stream()
                .mapToDouble(DiscountEntity::getDiscountPrecentage)
                .sum();

        // Áp dụng giảm giá
        totalPrice -= (totalDiscountPercentage / 100.0) * totalPrice;

        return totalPrice > 0 ? totalPrice : 0;
    }


    //apply discount
    @Transactional
    public ApplyDiscountResponse applyDiscount (ApplyDiscountRequest request) throws RuntimeException, ParseException {
        ReservationEntity reservation = reservationRepository.findById(request.getReservationId())
                .orElseThrow(() -> new CustomException(ErrorCode.BOOKING_NOT_AVAILABLE));

        // lấy tổng tiền của reservation
        Double totalPrice = getTotalPrice(request.getReservationId());

        //kiểm tra tính hợp lệ của từng discount -> nếu oke thì thêm vào
        Set<DiscountEntity> discounts = new HashSet<>();
        for (String discountCode : request.getDiscountCodes()) {
            DiscountEntity discount = discountRepository.findByCode(discountCode)
                    .orElseThrow(() -> new CustomException(ErrorCode.DISCOUNT_NOT_AVAILABLE));

            if (discountService.isApplicableToReservation(discount, totalPrice)) {
                discounts.add(discount);
            }
        }

        // lưu lại reservation
        reservation.setDiscounts(discounts);
        reservationRepository.save(reservation);

        return ApplyDiscountResponse.builder()
                .isSuccess(true)
                .discounts(reservation.getDiscounts().stream()
                        .map(discountMapper::toDiscountDTOForReservation)
                        .collect(Collectors.toSet()))
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
