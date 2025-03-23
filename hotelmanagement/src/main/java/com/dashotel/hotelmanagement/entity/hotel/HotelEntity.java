package com.dashotel.hotelmanagement.entity.hotel;

import com.dashotel.hotelmanagement.entity.AbstractEntity;
import com.dashotel.hotelmanagement.entity.review.ReviewEntity;
import jakarta.persistence.*;
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
@Table(name = "hotel")
public class HotelEntity extends AbstractEntity {
    String id;
    String name;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "address_id", nullable = false)
    AddressEntity address;
    int rating;

    @OneToMany(mappedBy = "hotel")
    List<ReviewEntity> reviews;
}
