package com.dashotel.hotelmanagement.service.payment;

import com.dashotel.hotelmanagement.dto.other.PaymentDTO;
import com.dashotel.hotelmanagement.dto.request.payment.VNPayRequest;
import com.dashotel.hotelmanagement.dto.request.payment.VnPayCallbackRequest;

import java.net.URI;

public interface IVNPayPaymentService extends IPaymentService<VnPayCallbackRequest, URI>{
    PaymentDTO.VNPayResponse creatVNPayPayment(VNPayRequest request);
}
