package com.dashotel.hotelmanagement.service.impl.payment;

import com.dashotel.hotelmanagement.configuration.VNPayConfig;
import com.dashotel.hotelmanagement.dto.request.payment.PaymentRequest;
import com.dashotel.hotelmanagement.dto.response.payment.PaymentResponse;
import com.dashotel.hotelmanagement.dto.response.reservation.common.ReservationResponse;
import com.dashotel.hotelmanagement.entity.booking.PaymentEntity;
import com.dashotel.hotelmanagement.entity.booking.ReservationEntity;
import com.dashotel.hotelmanagement.enums.BookingStatusEnum;
import com.dashotel.hotelmanagement.enums.PaymentMethodEnum;
import com.dashotel.hotelmanagement.enums.PaymentStatusEnum;
import com.dashotel.hotelmanagement.exception.CustomException;
import com.dashotel.hotelmanagement.exception.ErrorCode;
import com.dashotel.hotelmanagement.mapper.PaymentMapper;
import com.dashotel.hotelmanagement.repository.payment.PaymentRepository;
import com.dashotel.hotelmanagement.repository.reservation.ReservationRepository;
import com.dashotel.hotelmanagement.service.impl.reservation.ReservationService;
import com.dashotel.hotelmanagement.service.impl.room.RoomAvailabilityService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

@Component
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
@Slf4j
public class PaymentProcessorService {

    PaymentMapper paymentMapper;
    PaymentRepository paymentRepository;
    ReservationRepository reservationRepository;
    ReservationService reservationService;
    RoomAvailabilityService roomAvailabilityService;


    @Transactional
    protected PaymentResponse savePayment(String reservationId, PaymentMethodEnum paymentMethod) {
        ReservationEntity reservation = reservationRepository.findById(reservationId)
                .orElseThrow(() -> new CustomException(ErrorCode.BOOKING_NOT_AVAILABLE));

        PaymentRequest request = PaymentRequest.builder()
                .amount(reservationService.getTotalPrice(reservationId))
                .paymentDate(LocalDate.now())
                .paymentMethod(paymentMethod)
                .status(PaymentStatusEnum.SUCCESS)
                .build();

        PaymentEntity entity = paymentMapper.toEntity(request);
        entity.setBooking(reservation);
        entity = paymentRepository.save(entity);

        return paymentMapper.toResponse(entity);
    }


    // nghiệp vụ payment
    boolean processPayment(String reservationId, PaymentMethodEnum method, BookingStatusEnum status) {
        PaymentResponse payment = savePayment(reservationId, method);
        boolean roomAvailability = roomAvailabilityService.updateRoomAvailable(reservationId);
        ReservationResponse reservation = reservationService.updateReservationStatus(reservationId, status);

        return payment != null && reservation != null && roomAvailability;
    }
}
