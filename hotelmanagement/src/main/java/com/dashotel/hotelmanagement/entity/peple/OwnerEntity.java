package com.dashotel.hotelmanagement.entity.peple;


import com.dashotel.hotelmanagement.entity.account.AccountEntity;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;


@Entity
@Data
@Table(name = "owner")
public class OwnerEntity extends UserEntity {
    @OneToOne
    @JoinColumn(name = "account_id")
    AccountEntity account;

}
