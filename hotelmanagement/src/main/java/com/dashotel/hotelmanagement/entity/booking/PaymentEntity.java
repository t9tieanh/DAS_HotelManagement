package com.dashotel.hotelmanagement.entity.booking;

import com.dashotel.hotelmanagement.entity.AbstractEntity;
import com.dashotel.hotelmanagement.enums.PaymentMethodEnum;
import com.dashotel.hotelmanagement.enums.PaymentStatusEnum;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@FieldDefaults(level = AccessLevel.PRIVATE)
@Data
@Entity
@Table(name = "payment")
public class PaymentEntity extends AbstractEntity {
    Double amount;
    LocalDate paymentDate;
    PaymentStatusEnum status;
    PaymentMethodEnum paymentMethod;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "booking_id")
    ReservationEntity booking;
}
