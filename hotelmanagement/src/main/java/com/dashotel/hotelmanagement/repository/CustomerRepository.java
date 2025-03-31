package com.dashotel.hotelmanagement.repository;

import com.dashotel.hotelmanagement.entity.user.CustomerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<CustomerEntity,String> {
    CustomerEntity findByAccountId(String accountId);
}
