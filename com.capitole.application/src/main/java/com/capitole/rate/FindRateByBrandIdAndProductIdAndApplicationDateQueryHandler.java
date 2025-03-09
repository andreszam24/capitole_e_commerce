package com.capitole.rate;

import com.capitole.brand.BrandId;
import com.capitole.common.QueryHandler;
import com.capitole.price.Price;
import com.capitole.price.PriceRepository;
import com.capitole.product.ProductId;
import com.capitole.rate.dto.FindRateResponseDTO;

import javax.inject.Named;

@Named
public final class FindRateByBrandIdAndProductIdAndApplicationDateQueryHandler implements QueryHandler<FindRateByBrandIdAndProductIdAndApplicationDateQuery, FindRateResponseDTO> {

    private final PriceRepository priceRepository;

    public FindRateByBrandIdAndProductIdAndApplicationDateQueryHandler(PriceRepository priceRepository) {
        this.priceRepository = priceRepository;
    }

    @Override
    public FindRateResponseDTO handle(FindRateByBrandIdAndProductIdAndApplicationDateQuery query) {
        Price price = priceRepository.findRateByDateAndProductIdAndBrandId(
                query.getApplicationDate(),
                new ProductId(query.getProductId()),
                new BrandId(query.getBrandId()));

        return new FindRateResponseDTO(
                query.getProductId(),
                query.getBrandId(),
                price.getPriceList().getId(),
                price.getRangeDate().getStartDate(),
                price.getRangeDate().getEndDate(),
                price.getPriority().getValue(),
                price.getPrice().getAmount(),
                price.getPrice().getCurrency().getDisplayName());
    }
}
