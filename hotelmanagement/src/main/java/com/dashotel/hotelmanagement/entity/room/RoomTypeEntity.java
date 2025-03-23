package com.dashotel.hotelmanagement.entity.room;

import com.dashotel.hotelmanagement.entity.AbstractEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@FieldDefaults(level = AccessLevel.PRIVATE)
@Data
@Entity
@Table(name = "room_type")
public class RoomTypeEntity extends AbstractEntity {
    String name;
    Double price;
    Long maxOccupation;
    Long amenities;

    @OneToMany(mappedBy = "roomType")
    List<RoomEntity> rooms;
}
