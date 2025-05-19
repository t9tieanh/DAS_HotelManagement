package com.dashotel.hotelmanagement.service.impl.reservation;

import com.dashotel.hotelmanagement.dto.common.DiscountDTO;
import com.dashotel.hotelmanagement.dto.common.ResponseDTO;
import com.dashotel.hotelmanagement.dto.request.reservation.initial.ApplyDiscountRequest;
import com.dashotel.hotelmanagement.dto.request.reservation.initial.InitialReservationRequest;
import com.dashotel.hotelmanagement.dto.request.reservation.initial.ReservationDetailRequest;
import com.dashotel.hotelmanagement.dto.request.reservation.updateinfo.UpdateReservationInfoRequest;
import com.dashotel.hotelmanagement.dto.response.common.CreationResponse;
import com.dashotel.hotelmanagement.dto.response.reservation.ApplyDiscountResponse;
import com.dashotel.hotelmanagement.dto.response.reservation.InitialReservationResponse;
import com.dashotel.hotelmanagement.dto.response.reservation.ReservationStepResponse;
import com.dashotel.hotelmanagement.dto.response.reservation.common.ReservationDetailResponse;
import com.dashotel.hotelmanagement.dto.response.reservation.common.ReservationResponse;
import com.dashotel.hotelmanagement.dto.response.reservation.history.ReservationHistoryResponse;
import com.dashotel.hotelmanagement.entity.booking.ReservationDetailEntity;
import com.dashotel.hotelmanagement.entity.booking.ReservationEntity;
import com.dashotel.hotelmanagement.entity.booking.RoomOccupantEntity;
import com.dashotel.hotelmanagement.entity.hotel.AddressEntity;
import com.dashotel.hotelmanagement.entity.hotel.HotelEntity;
import com.dashotel.hotelmanagement.entity.promotion.DiscountEntity;
import com.dashotel.hotelmanagement.entity.room.RoomTypeEntity;
import com.dashotel.hotelmanagement.entity.user.CustomerEntity;
import com.dashotel.hotelmanagement.enums.BookingStatusEnum;
import com.dashotel.hotelmanagement.exception.CustomException;
import com.dashotel.hotelmanagement.exception.ErrorCode;
import com.dashotel.hotelmanagement.mapper.AddressMapper;
import com.dashotel.hotelmanagement.mapper.DiscountMapper;
import com.dashotel.hotelmanagement.mapper.ReservationMapper;
import com.dashotel.hotelmanagement.mapper.RoomOccupantMapper;
import com.dashotel.hotelmanagement.repository.CustomerRepository;
import com.dashotel.hotelmanagement.repository.promotion.DiscountRepository;
import com.dashotel.hotelmanagement.repository.reservation.ReservationRepository;
import com.dashotel.hotelmanagement.service.impl.promotion.DiscountService;
import com.dashotel.hotelmanagement.service.impl.room.RoomTypeService;
import com.dashotel.hotelmanagement.service.reservation.IReservationService;
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
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
@Slf4j
public class ReservationService implements IReservationService {
    ReservationRepository reservationRepository;
    CustomerRepository customerRepository;
    DiscountRepository discountRepository;

    RoomTypeService roomTypeService;
    DiscountService discountService;

    RoomOccupantMapper roomOccupantMapper;
    DiscountMapper discountMapper;
    ReservationMapper reservationMapper;
    AddressMapper addressMapper;

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

        if (reservationEntity.getStatus().equals(BookingStatusEnum.PAID))
            throw new CustomException(ErrorCode.RESERVATION_CANNOT_CANCEL);

        reservationEntity.setStatus(BookingStatusEnum.CANCELLED);
        reservationEntity.setExpireDateTime(LocalDateTime.now().minusMinutes(1));

        reservationRepository.save(reservationEntity);
        return ResponseDTO.builder()
                .isSuccess(true)
                .build();
    }


    public ReservationStepResponse getCurrentStep(String reservationId) {
        ReservationEntity reservation = reservationRepository.findById(reservationId)
                .orElseThrow(() -> new CustomException(ErrorCode.USER_NOT_FOUND));

        // check trạng thái của reservation
        if (reservation.getStatus().equals(BookingStatusEnum.CANCELLED))
            throw new CustomException(ErrorCode.BOOKING_CANCELED);

        // check xem còn trong thời gian transaction không
        if (reservation.getExpireDateTime().isBefore(LocalDateTime.now()))
            throw new CustomException(ErrorCode.BOOKING_TIMEOUT);

        // lấy thông tin room, hotel, giá tiền
        List<ReservationDetailResponse> roomInfoForReservationResponses = new ArrayList<>();
        double totalPrice = 0;

        for (ReservationDetailEntity reservationDetail : reservation.getReservationDetail()) {
            ReservationDetailResponse roomInfo = roomTypeService.getRoomInfoForReservation(
                    reservationDetail.getRoomType().getId()
            );
            roomInfo.setQuantity(reservationDetail.getQuantity()); // lấy quantity của từng reservation detail
            roomInfoForReservationResponses.add(roomInfo);

            // Tính tổng tiền cho mỗi phòng (price * số lượng * số đêm)
            totalPrice += roomInfo.getPrice() *  reservationDetail.getQuantity()
                    * ChronoUnit.DAYS.between(reservation.getCheckIn(), reservation.getCheckOut());
        }

        // lấy thông tin discount
        Set<DiscountDTO> discounts = reservation.getDiscounts().stream()
                .map(discountMapper::toDiscountDTOForReservation)
                .collect(Collectors.toSet());

        // Áp dụng giảm giá (nếu có)
        for (DiscountDTO discount : discounts) {
            totalPrice -= Math.min(discount.getMaxDiscountAmount() ,(totalPrice * discount.getDiscountPrecentage() / 100));
            // số tiền giảm của discount không quá thuộc tính MaxDiscountAmount
        }

        return ReservationStepResponse.builder()
                .currentStep(reservation.getStatus().getStep())
                .description(reservation.getStatus().getDescription())
                .expireDateTime(reservation.getExpireDateTime())
                .checkIn(reservation.getCheckIn())
                .checkOut(reservation.getCheckOut())
                .discounts(discounts)
                .reservationDetail(roomInfoForReservationResponses)
                .totalPrice(Math.max(totalPrice, 0))
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

        // Tính tổng số tiền được giảm, theo từng mã giảm giá
        double totalDiscountAmount = reservation.getDiscounts().stream()
                .mapToDouble(discount -> {
                    double percentDiscount = (discount.getDiscountPrecentage() / 100.0) * totalPrice;
                    double maxAllowed = discount.getMaxDiscountAmount(); // cần thêm getter này trong entity
                    return Math.min(percentDiscount, maxAllowed);
                })
                .sum();

        // Áp dụng giảm giá
        double finalPrice = totalPrice - totalDiscountAmount;

        return finalPrice > 0 ? finalPrice : 0;
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


    public List<DiscountDTO> getDiscountByReservation (String reservationId) {
        ReservationEntity reservation = reservationRepository.findById(reservationId)
                .orElseThrow(() -> new CustomException(ErrorCode.BOOKING_NOT_AVAILABLE));

        return reservation.getDiscounts().stream().map(
                discountMapper::toDTO
        ).toList();
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
    public ReservationResponse updateReservationStatus(String reservationId, BookingStatusEnum bookingStatus) {
        ReservationEntity reservation = reservationRepository.findById(reservationId)
                .orElseThrow(() -> new CustomException(ErrorCode.BOOKING_NOT_AVAILABLE));

        // update status reservation
        reservation.setStatus(bookingStatus);

        return reservationMapper.toResponse(reservationRepository.save(reservation));
    }

    public List<ReservationHistoryResponse> getReservationHistory(String customerId) {

        List<ReservationEntity> reservations = reservationRepository.findAllByCustomerId(customerId);

        return reservations.stream()
                .filter(reservation -> reservation.getStatus() == BookingStatusEnum.PAID)
                .map(reservation -> {
                    ReservationDetailEntity detail = reservation.getReservationDetail().getFirst();
                    RoomTypeEntity roomType = detail.getRoomType();
                    HotelEntity hotel = roomType.getHotel();
                    AddressEntity address = hotel.getAddress();

                    return ReservationHistoryResponse.builder()
                            .hotelName(hotel.getName())
                            .img(hotel.getAvatar())
                            .address(addressMapper.toDTO(address))
                            .roomTypeName(roomType.getName())
                            .totalPrice(reservation.getPayment().getAmount() != null ? reservation.getPayment().getAmount() : 0)
                            .reservationDate(reservation.getReservationDate())
                            .checkInDate(reservation.getCheckIn())
                            .checkOutDate(reservation.getCheckOut())
                            .build();
                }).collect(Collectors.toList());
    }

    public Long getReservationCompletedCount(String customerId) {
        return reservationRepository.countByStatusAndCustomerId(BookingStatusEnum.PAID, customerId);
    }
}
