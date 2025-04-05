package com.dashotel.hotelmanagement.repository;

import com.dashotel.hotelmanagement.entity.user.CustomerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepository<CustomerEntity,String> {
    CustomerEntity findByAccountId(String accountId);

    @Query("SELECT ct FROM CustomerEntity ct JOIN ct.account ac WHERE ac.username = :username")
    Optional<CustomerEntity> findByUsername(@Param("username") String username);
}
