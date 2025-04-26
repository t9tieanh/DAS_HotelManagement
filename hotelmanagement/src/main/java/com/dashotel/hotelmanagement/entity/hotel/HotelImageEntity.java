package com.dashotel.hotelmanagement.entity.hotel;

import com.dashotel.hotelmanagement.entity.AbstractEntity;
import com.dashotel.hotelmanagement.enums.HotelImageEnum;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;

@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@FieldDefaults(level = AccessLevel.PRIVATE)
@Setter
@Getter
@Entity
@Table(name = "hotel_image")
public class HotelImageEntity extends AbstractEntity {
    String imgUrl;

    @Enumerated(EnumType.STRING)
    HotelImageEnum imageType;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "hotel_id")
    HotelEntity hotel;
}
