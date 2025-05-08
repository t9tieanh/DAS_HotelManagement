package com.dashotel.hotelmanagement.service.payment;

import com.dashotel.hotelmanagement.configuration.VNPayConfig;
import com.dashotel.hotelmanagement.dto.common.RequestDTO;
import com.dashotel.hotelmanagement.dto.other.PaymentDTO;
import com.dashotel.hotelmanagement.dto.request.payment.PaymentRequest;
import com.dashotel.hotelmanagement.dto.request.payment.VNPayRequest;
import com.dashotel.hotelmanagement.dto.request.payment.VnPayCallbackRequest;
import com.dashotel.hotelmanagement.dto.response.common.CreationResponse;
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
import com.dashotel.hotelmanagement.service.reservation.ReservationService;
import com.dashotel.hotelmanagement.service.room.RoomAvailabilityService;
import com.dashotel.hotelmanagement.utils.VNPayUtils;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.net.URI;
import java.time.LocalDate;
import java.util.Map;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
@Slf4j
public class PaymentService {
    VNPayConfig vnPayConfig;
    PaymentMapper paymentMapper;
    PaymentRepository paymentRepository;
    ReservationRepository reservationRepository;
    ReservationService reservationService;
    RoomAvailabilityService roomAvailabilityService;

    public PaymentDTO.VNPayResponse creatVNPayPayment(HttpServletRequest request) {
        long amount = Integer.parseInt(request.getParameter("amount")) * 100L;
        String reservationId = (request.getParameter("id"));
        String bankCode = "NCB";
        Map<String, String> vnpParamsMap = vnPayConfig.getVNPayConfig(reservationId);
        vnpParamsMap.put("vnp_Amount", String.valueOf(amount));
        vnpParamsMap.put("vnp_BankCode", bankCode);
        vnpParamsMap.put("vnp_IpAddr", VNPayUtils.getIpAddress(request));

        String queryUrl = VNPayUtils.getPaymentUrl(vnpParamsMap, true);
        String hashData = VNPayUtils.getPaymentUrl(vnpParamsMap, false);
        String vnpSecureHash = VNPayUtils.hmacSHA512(vnPayConfig.getVnPaySecretKey(), hashData);
        queryUrl += "&vnp_SecureHash=" + vnpSecureHash;
        String paymentUrl = vnPayConfig.getVnPayUrl() + "?" + queryUrl;
        return PaymentDTO.VNPayResponse.builder()
                .code("ok")
                .message("success")
                .paymentUrl(paymentUrl)
                .build();
    }

    public PaymentDTO.VNPayResponse creatVNPayPayment(VNPayRequest request) {
        long amount = request.getAmount() * 100L;
        String reservationId = request.getId();
        String bankCode = "NCB";
        Map<String, String> vnpParamsMap = vnPayConfig.getVNPayConfig(reservationId, request.getAndroid());
        vnpParamsMap.put("vnp_Amount", String.valueOf(amount));
        vnpParamsMap.put("vnp_BankCode", bankCode);
        vnpParamsMap.put("vnp_IpAddr", request.getIpAddress());

        String queryUrl = VNPayUtils.getPaymentUrl(vnpParamsMap, true);
        String hashData = VNPayUtils.getPaymentUrl(vnpParamsMap, false);
        String vnpSecureHash = VNPayUtils.hmacSHA512(vnPayConfig.getVnPaySecretKey(), hashData);
        queryUrl += "&vnp_SecureHash=" + vnpSecureHash;

        String paymentUrl = vnPayConfig.getVnPayUrl() + "?" + queryUrl;

        return PaymentDTO.VNPayResponse.builder()
                .code("ok")
                .message("success")
                .paymentUrl(paymentUrl)
                .build();
    }

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


    private boolean processPayment(String reservationId, PaymentMethodEnum method, BookingStatusEnum status) {
        PaymentResponse payment = savePayment(reservationId, method);

        boolean roomAvailability = roomAvailabilityService.updateRoomAvailable(reservationId);

        ReservationResponse reservation = reservationService.updateReservationStatus(reservationId, status);

        return payment != null && reservation != null && roomAvailability;
    }


    @Transactional
    public URI handlePaymentWithVnPay(VnPayCallbackRequest request) {
        if (request.getVnpResponseCode().equals("00")) {
            boolean success = processPayment(request.getReservationId(), PaymentMethodEnum.VNPAY, BookingStatusEnum.PAID);

            if (success) {
                String location = request.getAndroid() ? "myapp://reservation/success" : "http://localhost:3000/reservation";
                return URI.create(location);
            } else
                throw new CustomException(ErrorCode.PAYMENT_RESERVATION_AVAILABILITY_FAILED);
        } else {
            // Trả về lỗi nếu mã phản hồi không phải là "00"
            throw new CustomException(ErrorCode.PAYMENT_FAILED_WITH_RESPONSE_CODE);
        }
    }


    @Transactional
    public CreationResponse payAtHotel(RequestDTO reservation) {
        boolean success = processPayment(reservation.getId(), PaymentMethodEnum.CASH, BookingStatusEnum.PAID);

        if (success) {
            return CreationResponse.builder()
                    .isSuccess(true)
                    .build();
        } else {
            throw new CustomException(ErrorCode.PAYMENT_RESERVATION_AVAILABILITY_FAILED);
        }
    }
}
