package com.dashotel.hotelmanagement.entity.booking;

import com.dashotel.hotelmanagement.entity.AbstractEntity;
import com.dashotel.hotelmanagement.entity.promotion.DiscountEntity;
import com.dashotel.hotelmanagement.entity.user.CustomerEntity;
import com.dashotel.hotelmanagement.entity.service.ServiceEntity;
import com.dashotel.hotelmanagement.enums.BookingStatusEnum;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;
import java.time.LocalDateTime;
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


    LocalDateTime expireDateTime;

    @ManyToMany
    Set<DiscountEntity> discounts;

    @ManyToMany
    List <ServiceEntity> services;

    @OneToOne(mappedBy = "booking", fetch = FetchType.LAZY)
    PaymentEntity payment;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id")
    CustomerEntity customer;

    @OneToMany(mappedBy = "reservation", cascade = CascadeType.ALL)
    List<ReservationDetailEntity> reservationDetail;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "room_occupant", referencedColumnName = "id")
    private RoomOccupantEntity roomOccupant;
}
