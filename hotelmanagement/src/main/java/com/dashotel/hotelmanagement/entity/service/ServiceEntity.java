package com.dashotel.hotelmanagement.entity.service;

import com.dashotel.hotelmanagement.entity.AbstractEntity;
import com.dashotel.hotelmanagement.entity.booking.ReservationEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
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
@Table(name = "service")
public class ServiceEntity extends AbstractEntity {
    String serviceName;
    Double price;
    String description;

    @ManyToMany(mappedBy = "services")
    List<ReservationEntity> bookings;
}
