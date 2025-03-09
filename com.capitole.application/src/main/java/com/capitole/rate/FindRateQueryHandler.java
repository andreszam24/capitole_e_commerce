package com.capitole.rate;

import com.capitole.common.QueryHandler;
import com.capitole.price.Price;
import com.capitole.rate.dto.FindRateResponseDTO;
import com.capitole.rate.service.RateService;

public final class FindRateQueryHandler implements QueryHandler<FindRateQuery, FindRateResponseDTO> {

    private final RateService rateService;

    public FindRateQueryHandler(RateService rateService) {
        this.rateService = rateService;
    }

    @Override
    public FindRateResponseDTO handle(FindRateQuery query) {
        Price price = rateService.findRateByBrandIdAndProductIdAndApplicationDate(query.getBrandId(), query.getProductId(), query.getApplicationDate());
        return new FindRateResponseDTO(
                query.getProductId(),
                query.getBrandId(),
                price.getPriceList().getId(),
                price.getRangeDate().getStartDate(),
                price.getRangeDate().getEndDate(),
                price.getPrice().getAmount(),
                price.getPrice().getCurrency().getDisplayName());
    }
}
