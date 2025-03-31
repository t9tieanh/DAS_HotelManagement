package com.dashotel.hotelmanagement.entity.promotion;

import com.dashotel.hotelmanagement.entity.AbstractEntity;
import com.dashotel.hotelmanagement.entity.booking.ReservationEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
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
@Table(name = "promotion")
public class PromotionEntity extends AbstractEntity {
    String description;
    String promotionName;
    String code;
    LocalDate beginDate;
    LocalDate endDate;
    int discountPrecentage;
    int minloyaltyPoints;

    @ManyToMany(mappedBy = "promotions")
    List<ReservationEntity> bookings = new ArrayList<>();
}
