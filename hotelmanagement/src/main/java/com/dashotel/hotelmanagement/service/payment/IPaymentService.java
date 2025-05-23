package com.dashotel.hotelmanagement.service.payment;

public interface IPaymentService<REQUEST, RESPONSE> {
    RESPONSE processPay(REQUEST request);
}
