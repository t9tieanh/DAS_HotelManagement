package com.dashotel.hotelmanagement.entity.room;

import com.dashotel.hotelmanagement.entity.AbstractEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;
import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@FieldDefaults(level = AccessLevel.PRIVATE)
@Getter
@Setter
@Entity
@Table(name = "room_availability")
public class RoomAvailability extends AbstractEntity {

    Long totalRoom;
    Long bookedRoom;
    LocalDate availableDate;
    Boolean status;

    @ManyToOne
    @JoinColumn(name = "room_type_id")
    RoomTypeEntity roomType;

}
