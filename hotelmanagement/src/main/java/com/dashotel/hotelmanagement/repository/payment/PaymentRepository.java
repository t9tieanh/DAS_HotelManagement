package com.dashotel.hotelmanagement.repository.payment;

import com.dashotel.hotelmanagement.entity.booking.PaymentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRepository extends JpaRepository<PaymentEntity, String> {

}
