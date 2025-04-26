package com.dashotel.hotelmanagement.service.payment;

import com.dashotel.hotelmanagement.configuration.VNPayConfig;
import com.dashotel.hotelmanagement.dto.other.PaymentDTO;
import com.dashotel.hotelmanagement.dto.request.payment.PaymentRequest;
import com.dashotel.hotelmanagement.dto.request.payment.VNPayRequest;
import com.dashotel.hotelmanagement.dto.request.payment.VnPayCallbackRequest;
import com.dashotel.hotelmanagement.dto.response.payment.PaymentResponse;
import com.dashotel.hotelmanagement.dto.response.reservation.common.ReservationResponse;
import com.dashotel.hotelmanagement.entity.booking.PaymentEntity;
import com.dashotel.hotelmanagement.entity.booking.ReservationEntity;
import com.dashotel.hotelmanagement.enums.PaymentMethodEnum;
import com.dashotel.hotelmanagement.enums.PaymentStatusEnum;
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
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.net.URI;
import java.time.LocalDate;
import java.util.Map;
import java.util.Optional;

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
    public PaymentResponse savePayment(String reservationId, PaymentMethodEnum paymentMethod) {
        try {
            ReservationEntity reservation = reservationRepository.findById(reservationId)
                    .orElseThrow(() -> new IllegalArgumentException("Đơn đặt không tồn tại"));

            PaymentRequest request = PaymentRequest.builder()
                    .amount(1000D)
                    .paymentDate(LocalDate.now())
                    .paymentMethod(paymentMethod)
                    .status(PaymentStatusEnum.SUCCESS)
                    .build();

            PaymentEntity entity = paymentMapper.toEntity(request);
            entity.setBooking(reservation);
            entity = paymentRepository.save(entity);

            return paymentMapper.toResponse(entity);
        }
        catch (IllegalArgumentException e) {
            log.error("Dữ liệu đầu vào không hợp lệ: {}", e.getMessage());
            throw e;
        }
        catch (Exception e) {
            log.error("Lỗi khi lưu payment", e);
            throw new RuntimeException("Lỗi server khi lưu payment");
        }
    }


    @Transactional
    public URI handlePaymentWithVnPay(VnPayCallbackRequest request) {
        if (request.getVnpResponseCode().equals("00")) {

            // Save payment
            PaymentResponse payment = savePayment(request.getReservationId(), PaymentMethodEnum.VNPAY);

            // Update reservation
            ReservationResponse reservation = reservationService.updateReservationIsSuccess(request.getReservationId());

            // Update room availability
            Boolean roomAvailability = roomAvailabilityService.updateRoomAvail(request.getReservationId());

            // Kiểm tra kết quả
            if (payment != null && reservation != null && roomAvailability) {
                String location = request.getAndroid() ? "myapp://reservation/success" : "http://localhost:3000/reservation";
                return URI.create(location);
            } else {
                throw new RuntimeException("Failed to process payment, reservation or room availability");
            }
        } else {
            // Trả về lỗi nếu mã phản hồi không phải là "00"
            throw new RuntimeException("Payment failed with response code: " + request.getVnpResponseCode());
        }
    }

}
