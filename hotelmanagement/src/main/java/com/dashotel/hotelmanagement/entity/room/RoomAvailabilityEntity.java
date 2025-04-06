package com.dashotel.hotelmanagement.entity.room;

import com.dashotel.hotelmanagement.entity.AbstractEntity;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@FieldDefaults(level = AccessLevel.PRIVATE)
@Getter
@Setter
@Entity
@Table(name = "room_availability")
public class RoomAvailabilityEntity extends AbstractEntity {

    Long totalRoom;
    Long bookedRoom;
    LocalDate availableDate;
    Boolean status;

    @ManyToOne(cascade = CascadeType.DETACH)
    @JoinColumn(name = "room_type_id")
    RoomTypeEntity roomType;

}
