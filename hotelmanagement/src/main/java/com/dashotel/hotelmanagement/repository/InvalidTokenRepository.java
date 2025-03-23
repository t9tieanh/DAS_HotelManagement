package com.dashotel.hotelmanagement.repository;

import com.dashotel.hotelmanagement.entity.auth.InvalidTokenEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InvalidTokenRepository extends JpaRepository<InvalidTokenEntity, String> {
    @Override
    boolean existsById(String token);
}
