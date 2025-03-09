package com.capitole.price;

import com.capitole.brand.BrandId;
import com.capitole.price.Price;

import java.time.LocalDateTime;

public interface PriceRepository {

    Price findRateByDateAndProductIdAndBrandId(LocalDateTime date, long productId, BrandId brandId);
}
