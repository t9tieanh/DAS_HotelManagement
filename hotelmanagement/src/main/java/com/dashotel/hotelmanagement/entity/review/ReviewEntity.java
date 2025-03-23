package com.dashotel.hotelmanagement.entity.review;

import com.dashotel.hotelmanagement.entity.AbstractEntity;
import com.dashotel.hotelmanagement.entity.hotel.HotelEntity;
import com.dashotel.hotelmanagement.entity.peple.CustomerEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;

@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@FieldDefaults(level = AccessLevel.PRIVATE)
@Data
@Entity
@Table(name = "review")
public class ReviewEntity extends AbstractEntity {
    String content;
    int RateValue;

    @ManyToOne
    @JoinColumn(name = "create_by")
    CustomerEntity createBy;

    @ManyToOne
    @JoinColumn(name = "hotel_id")
    HotelEntity hotel;
}
