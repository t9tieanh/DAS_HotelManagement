package com.dashotel.hotelmanagement.entity.peple;

import com.dashotel.hotelmanagement.entity.account.AccountEntity;
import com.dashotel.hotelmanagement.entity.account.SocialAccount;
import com.dashotel.hotelmanagement.entity.booking.BookingEntity;
import com.dashotel.hotelmanagement.entity.review.ReviewEntity;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@Table(name = "customer")
public class CustomerEntity extends UserEntity {
    private Long loyaltyPoints;

    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL)
    List<BookingEntity> bookings;

    @OneToMany(mappedBy = "createBy", cascade = CascadeType.ALL)
    List <ReviewEntity> reviews;

    @OneToOne(mappedBy = "customer")
    SocialAccount socialAccount;

    @OneToOne
    @JoinColumn(name = "account_id")
    AccountEntity account;

}
