package com.capitole.price;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

@Repository
public interface PriceJpaRepository extends JpaRepository<Price, Long> {
    @Query("SELECT p FROM Price p " +
            "WHERE p.brand.id = :brandId " +
            "  AND p.product.id = :productId " +
            "  AND :applicationDate BETWEEN p.startDate AND p.endDate " +
            "ORDER BY p.priority DESC")
    PriceDAO findApplicablePrice(@Param("brandId") long brandId,
                              @Param("productId") long productId,
                              @Param("applicationDate") LocalDateTime applicationDate);
}
