package com.dashotel.hotelmanagement.entity.user;

import com.dashotel.hotelmanagement.entity.AbstractEntity;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;


@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@FieldDefaults(level = AccessLevel.PRIVATE)
@MappedSuperclass
@Data
public class UserEntity extends AbstractEntity {

    String name;

    String phone;

    String imgUrl;
}
