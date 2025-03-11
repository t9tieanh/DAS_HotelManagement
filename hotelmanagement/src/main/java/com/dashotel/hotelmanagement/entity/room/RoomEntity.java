package com.dashotel.hotelmanagement.entity.room;

import com.dashotel.hotelmanagement.entity.AbstractEntity;
import com.dashotel.hotelmanagement.entity.booking.BookingEntity;
import com.dashotel.hotelmanagement.enums.RoomStatusEnum;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@FieldDefaults(level = AccessLevel.PRIVATE)
@Data
@Entity
@Table(name = "room")
public class RoomEntity extends AbstractEntity {
    String id;
    String roomNumber;
    RoomStatusEnum roomStatus;
    String description;

    Set <String> imgRoomUrl;

    @ManyToMany(mappedBy = "rooms")
    List<BookingEntity> bookings = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "room_type_id")
    RoomTypeEntity roomType;
}
