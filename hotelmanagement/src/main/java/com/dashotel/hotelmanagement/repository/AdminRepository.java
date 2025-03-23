package com.dashotel.hotelmanagement.repository;

import com.dashotel.hotelmanagement.entity.peple.AdminEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminRepository  extends JpaRepository<AdminEntity,String> {
}
