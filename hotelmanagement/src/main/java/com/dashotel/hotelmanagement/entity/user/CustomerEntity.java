package com.dashotel.hotelmanagement.entity.user;

import com.dashotel.hotelmanagement.entity.account.AccountEntity;
import com.dashotel.hotelmanagement.entity.booking.ReservationEntity;
import com.dashotel.hotelmanagement.entity.review.ReviewEntity;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@Table(name = "customer")
public class CustomerEntity extends UserEntity {
    private Long loyaltyPoints;

    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL)
    List<ReservationEntity> bookings;

    @OneToMany(mappedBy = "createBy", cascade = CascadeType.ALL)
    List <ReviewEntity> reviews;


    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "account_id")
    AccountEntity account;
}
