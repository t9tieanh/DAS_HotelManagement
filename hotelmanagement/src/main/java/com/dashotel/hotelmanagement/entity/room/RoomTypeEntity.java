package com.dashotel.hotelmanagement.entity.room;

import com.dashotel.hotelmanagement.entity.AbstractEntity;
import com.dashotel.hotelmanagement.entity.booking.ReservationDetailEntity;
import com.dashotel.hotelmanagement.entity.hotel.HotelEntity;
import com.dashotel.hotelmanagement.entity.hotel.HotelImageEntity;
import com.dashotel.hotelmanagement.enums.RoomStatusEnum;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;

import java.util.List;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@FieldDefaults(level = AccessLevel.PRIVATE)
@Getter
@Setter
@Entity
@Table(name = "room_type")
public class RoomTypeEntity extends AbstractEntity {
    String name;
    Double price;
    Long maxOccupation;
    Long freeChildren;
    String description;
    String avatar;

    Set<String> imgRoomUrl;
    RoomStatusEnum roomStatus;

    @OneToMany(mappedBy = "roomType")
    List<RoomAvailabilityEntity> roomAvailabilities;

    @ManyToOne
    @JoinColumn(name = "hotel_id")
    HotelEntity hotel;

    @OneToMany(mappedBy = "roomType")
    List<ReservationDetailEntity> reservationDetail;

    @OneToMany(mappedBy = "roomType", cascade = CascadeType.ALL)
    List<RoomImageEntity> images;

}
