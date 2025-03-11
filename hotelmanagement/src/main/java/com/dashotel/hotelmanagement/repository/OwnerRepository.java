package com.dashotel.hotelmanagement.repository;

import com.dashotel.hotelmanagement.entity.peple.OwnerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OwnerRepository extends JpaRepository<OwnerEntity, String> {
}
