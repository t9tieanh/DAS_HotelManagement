package com.dashotel.hotelmanagement.entity.promotion;

import com.dashotel.hotelmanagement.entity.AbstractEntity;
import com.dashotel.hotelmanagement.entity.booking.ReservationEntity;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@FieldDefaults(level = AccessLevel.PRIVATE)
@Data
@Entity
@Table(name = "discount")
public class DiscountEntity extends AbstractEntity {
    String name;
    String code;
    LocalDate beginDate;
    LocalDate endDate;
    int discountPrecentage;
    int minloyaltyPoints = 0;
    double minBookingAmount;
    double maxDiscountAmount;
    Boolean isPublic;
    Boolean isActive;
}
