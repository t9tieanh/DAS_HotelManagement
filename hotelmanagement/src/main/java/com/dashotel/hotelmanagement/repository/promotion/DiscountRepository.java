package com.dashotel.hotelmanagement.repository.promotion;

import com.dashotel.hotelmanagement.entity.promotion.DiscountEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DiscountRepository extends JpaRepository<DiscountEntity, String> {
    boolean existsByCode(String code);

    Optional<DiscountEntity> findByCode(String code);

    @Query("select dc from DiscountEntity dc where dc.beginDate <= CURRENT_TIMESTAMP and dc.endDate > CURRENT_TIMESTAMP " +
            "and dc.isPublic = true and dc.isActive = true")
    List<DiscountEntity> findAvailabeDiscount();
}
