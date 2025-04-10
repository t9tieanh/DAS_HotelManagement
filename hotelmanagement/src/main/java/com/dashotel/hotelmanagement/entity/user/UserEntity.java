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
@Getter
@Setter
public class UserEntity extends AbstractEntity {
    String name;
    String phone;
    String email;
    String imgUrl;
}
