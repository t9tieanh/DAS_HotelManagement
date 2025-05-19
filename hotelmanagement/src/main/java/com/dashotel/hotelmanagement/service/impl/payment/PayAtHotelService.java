package com.dashotel.hotelmanagement.service.impl.payment;

import com.dashotel.hotelmanagement.dto.common.RequestDTO;
import com.dashotel.hotelmanagement.dto.response.common.CreationResponse;
import com.dashotel.hotelmanagement.enums.BookingStatusEnum;
import com.dashotel.hotelmanagement.enums.PaymentMethodEnum;
import com.dashotel.hotelmanagement.exception.CustomException;
import com.dashotel.hotelmanagement.exception.ErrorCode;
import com.dashotel.hotelmanagement.service.payment.IPaymentService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
@Slf4j
public class PayAtHotelService implements IPaymentService<RequestDTO, CreationResponse> {

    PaymentProcessorService paymentProcessorService;

    @Override
    public CreationResponse processPay(RequestDTO reservation) {
        boolean success = paymentProcessorService.processPayment(reservation.getId(), PaymentMethodEnum.CASH, BookingStatusEnum.PAID);

        if (success) {
            return CreationResponse.builder()
                    .isSuccess(true)
                    .build();
        } else {
            throw new CustomException(ErrorCode.PAYMENT_RESERVATION_AVAILABILITY_FAILED);
        }
    }
}
