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

    @Lob
    @Column(columnDefinition = "TEXT")
    String description;

    String avatar;

    Set<String> imgRoomUrl;

    RoomStatusEnum roomStatus;

    @OneToMany(mappedBy = "roomType", cascade = CascadeType.ALL)
    List<RoomAvailabilityEntity> roomAvailabilities;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "hotel_id", nullable = false)
    HotelEntity hotel;

    @OneToMany(mappedBy = "roomType")
    List<ReservationDetailEntity> reservationDetail;

}
