package com.capitole.rate;

import com.capitole.common.Query;
import com.capitole.rate.dto.FindRateQueryResponse;

import java.time.LocalDateTime;

public final class FindRateByBrandIdAndProductIdAndApplicationDateQuery implements Query<FindRateQueryResponse> {

    private final LocalDateTime applicationDate;
    private final long productId;
    private final long brandId;

    public FindRateByBrandIdAndProductIdAndApplicationDateQuery(LocalDateTime applicationDate, long productId, long brandId) {
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

    public long getBrandId() {
        return brandId;
    }

}
