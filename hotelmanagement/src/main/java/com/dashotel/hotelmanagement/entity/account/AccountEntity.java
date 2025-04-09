package com.dashotel.hotelmanagement.entity.account;

import com.dashotel.hotelmanagement.entity.AbstractEntity;
import com.dashotel.hotelmanagement.entity.user.AdminEntity;
import com.dashotel.hotelmanagement.entity.user.CustomerEntity;
import com.dashotel.hotelmanagement.entity.user.OwnerEntity;
import com.dashotel.hotelmanagement.enums.AccountStatusEnum;
import com.dashotel.hotelmanagement.enums.RoleAccountEnum;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "account")
public class AccountEntity extends AbstractEntity {
    String username;
    String password;
    RoleAccountEnum role;
    AccountStatusEnum status;
    String email;
    String imgUrl;

    @OneToOne(mappedBy = "account",cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    AdminEntity admin;

    @OneToOne(mappedBy = "account",cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    CustomerEntity customer;

    @OneToOne(mappedBy = "account", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    OwnerEntity owner;
}
