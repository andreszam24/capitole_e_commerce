package com.capitole.price;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

@Repository
public interface PriceJpaRepository extends JpaRepository<PriceDAO, Long> {
    @Query(value = "SELECT * FROM PRICES p " +
            "WHERE p.BRAND_ID = :brandId " +
            "  AND p.PRODUCT_ID = :productId " +
            "  AND :applicationDate BETWEEN p.START_DATE AND p.END_DATE " +
            "ORDER BY p.PRIORITY DESC LIMIT 1", nativeQuery = true)
    PriceDAO findApplicablePrice(@Param("brandId") long brandId,
                                 @Param("applicationDate") LocalDateTime applicationDate,
                                 @Param("productId") long productId);
}