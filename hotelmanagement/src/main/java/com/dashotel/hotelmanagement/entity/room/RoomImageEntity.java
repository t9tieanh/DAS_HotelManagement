package com.dashotel.hotelmanagement.entity.room;

import com.dashotel.hotelmanagement.entity.AbstractEntity;
import com.dashotel.hotelmanagement.entity.hotel.HotelEntity;
import com.dashotel.hotelmanagement.enums.HotelImageEnum;
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
@Setter
@Getter
@Entity
@Table(name = "room_type_image")
public class RoomImageEntity extends AbstractEntity {
    String imgUrl;
    Boolean isAvatar;

    @ManyToOne
    @JoinColumn(name = "room_type_id")
    RoomTypeEntity roomType;
}
