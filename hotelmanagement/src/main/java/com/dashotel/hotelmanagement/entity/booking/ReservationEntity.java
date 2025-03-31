package com.dashotel.hotelmanagement.entity.booking;

import com.dashotel.hotelmanagement.entity.AbstractEntity;
import com.dashotel.hotelmanagement.entity.user.CustomerEntity;
import com.dashotel.hotelmanagement.entity.promotion.PromotionEntity;
import com.dashotel.hotelmanagement.entity.service.ServiceEntity;
import com.dashotel.hotelmanagement.enums.BookingStatusEnum;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@FieldDefaults(level = AccessLevel.PRIVATE)
@Getter
@Setter
@Entity
@Table(name = "reservation")
public class ReservationEntity extends AbstractEntity {
    LocalDate checkIn;
    LocalDate checkOut;
    LocalDate reservationDate;
    BookingStatusEnum status;

    @ManyToMany
    List<PromotionEntity> promotions;

    @ManyToMany
    List <ServiceEntity> services;

    @OneToOne(mappedBy = "booking")
    PaymentEntity payment;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    CustomerEntity customer;

    @OneToMany(mappedBy = "reservation")
    List<ReservationDetailEntity> reservationDetail;


}
