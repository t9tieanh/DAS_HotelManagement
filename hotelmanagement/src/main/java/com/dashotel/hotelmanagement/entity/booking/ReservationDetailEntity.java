package com.dashotel.hotelmanagement.entity.booking;

import com.dashotel.hotelmanagement.entity.AbstractEntity;
import com.dashotel.hotelmanagement.entity.room.RoomTypeEntity;
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
@Getter
@Setter
@Entity
@Table(name = "reservation_detail")
public class ReservationDetailEntity extends AbstractEntity {
    Long quantity;

    @ManyToOne
    @JoinColumn(name = "reservation_id")
    ReservationEntity reservation;

    @ManyToOne
    @JoinColumn(name = "room_type_id")
    RoomTypeEntity roomType;
}
