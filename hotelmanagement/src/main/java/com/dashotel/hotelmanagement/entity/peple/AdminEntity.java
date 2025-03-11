package com.dashotel.hotelmanagement.entity.peple;

import com.dashotel.hotelmanagement.entity.account.AccountEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;

@Entity
@Table(name = "admin")
@Data
@SuperBuilder
@NoArgsConstructor
public class AdminEntity extends UserEntity {

    @OneToOne
    @JoinColumn(name = "account_id")
    AccountEntity account;
}
