package com.dashotel.hotelmanagement.entity.account;

import com.dashotel.hotelmanagement.entity.AbstractEntity;
import com.dashotel.hotelmanagement.entity.peple.AdminEntity;
import com.dashotel.hotelmanagement.entity.peple.CustomerEntity;
import com.dashotel.hotelmanagement.entity.peple.OwnerEntity;
import com.dashotel.hotelmanagement.enums.AccountStatusEnum;
import com.dashotel.hotelmanagement.enums.RoleAccountEnum;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
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

    @OneToOne(mappedBy = "account",cascade = CascadeType.ALL)
    AdminEntity admin;

    @OneToOne(mappedBy = "account",cascade = CascadeType.ALL)
    CustomerEntity customer;

    @OneToOne(mappedBy = "account", cascade = CascadeType.ALL)
    OwnerEntity owner;

}
