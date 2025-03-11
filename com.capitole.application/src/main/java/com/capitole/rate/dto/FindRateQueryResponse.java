package com.capitole.rate.dto;

import java.time.LocalDateTime;

public record FindRateQueryResponse(

        long productId,
        long brandId,
        int priceList,
        LocalDateTime startDate,
        LocalDateTime endDate,
        String price) {
}
