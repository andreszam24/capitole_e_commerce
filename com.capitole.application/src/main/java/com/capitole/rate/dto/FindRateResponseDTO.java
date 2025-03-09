package com.capitole.rate.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class FindRateResponseDTO {
    private final long productId;
    private final int brandId;
    private final int priceList;
    private final LocalDateTime startDate;
    private final LocalDateTime endDate;
    private final BigDecimal amount;
    private final String currency;

    public FindRateResponseDTO(long productId, int brandId, int priceList,
                               LocalDateTime startDate, LocalDateTime endDate,
                               BigDecimal amount, String currency) {

        this.productId = productId;
        this.brandId = brandId;
        this.priceList = priceList;
        this.startDate = startDate;
        this.endDate = endDate;
        this.amount = amount;
        this.currency = currency;
    }

    public long getProductId() {
        return productId;
    }

    public int getBrandId() {
        return brandId;
    }

    public int getPriceList() {
        return priceList;
    }

    public LocalDateTime getStartDate() {
        return startDate;
    }

    public LocalDateTime getEndDate() {
        return endDate;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public String getCurrency() {
        return currency;
    }
}
