package com.capitole.price;


import com.capitole.brand.BrandId;
import com.capitole.product.ProductId;

import java.time.LocalDateTime;

public interface PriceRepository {

    Price findRateByDateAndProductIdAndBrandId(ProductId productId, LocalDateTime date, BrandId brandId);
}
