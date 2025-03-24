package com.dashotel.hotelmanagement.entity.user;


import com.dashotel.hotelmanagement.entity.account.AccountEntity;
import jakarta.persistence.*;
import lombok.*;


@Entity
@Data
@Table(name = "owner")
public class OwnerEntity extends UserEntity {
    @OneToOne
    @JoinColumn(name = "account_id")
    AccountEntity account;

}
