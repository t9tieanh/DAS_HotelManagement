package com.dashotel.hotelmanagement.entity.account;

import com.dashotel.hotelmanagement.entity.AbstractEntity;
import com.dashotel.hotelmanagement.entity.peple.CustomerEntity;
import com.dashotel.hotelmanagement.enums.AccountStatusEnum;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
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
@Table(name = "social_account")
public class SocialAccount extends AbstractEntity {
    String ggId;
    AccountStatusEnum status;

    @OneToOne
    @JoinColumn(name = "customer_id")
    CustomerEntity customer;

}
