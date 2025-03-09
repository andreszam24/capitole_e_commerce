package com.capitole.rate.service;

import com.capitole.price.Price;

import java.time.LocalDateTime;

public interface RateService {

    Price findRateByBrandIdAndProductIdAndApplicationDate(int brandId, long productId, LocalDateTime applicationDate);
}
