package com.dashotel.hotelmanagement.repository;

import com.dashotel.hotelmanagement.entity.account.AccountEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AccountRepository extends JpaRepository<AccountEntity, String> {

    boolean existsByUsername(String username);

    boolean existsByEmail(String email);

    Optional<AccountEntity> findByUsername(String username);
    Optional<AccountEntity> findByEmail(String email);
}
