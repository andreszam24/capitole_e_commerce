package com.capitole.rate;

import com.capitole.common.Query;
import com.capitole.rate.dto.FindRateResponseDTO;

import java.time.LocalDateTime;

public final class FindRateQuery implements Query<FindRateResponseDTO> {

    private final LocalDateTime applicationDate;
    private final long productId;
    private final int brandId;

    public FindRateQuery(LocalDateTime applicationDate, long productId, int brandId) {
        this.applicationDate = applicationDate;
        this.productId = productId;
        this.brandId = brandId;
    }

    public LocalDateTime getApplicationDate() {
        return applicationDate;
    }

    public long getProductId() {
        return productId;
    }

    public int getBrandId() {
        return brandId;
    }

}
