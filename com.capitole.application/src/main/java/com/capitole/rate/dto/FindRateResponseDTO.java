package com.capitole.rate.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record FindRateResponseDTO(

        long productId,
        long brandId,
        int priceList,
        LocalDateTime startDate,
        LocalDateTime endDate,
        String price) {
}
