package com.dashotel.hotelmanagement.entity.hotel;

import com.dashotel.hotelmanagement.entity.AbstractEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.Table;
import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;

@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@FieldDefaults(level = AccessLevel.PRIVATE)
@Data
@Entity
@Table(name = "address")
public class AddressEntity extends AbstractEntity {
    String concrete;
    String commune;
    String district;
    String city;
}
