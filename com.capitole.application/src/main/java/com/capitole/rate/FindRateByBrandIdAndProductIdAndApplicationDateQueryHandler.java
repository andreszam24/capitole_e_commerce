package com.capitole.rate;

import com.capitole.brand.BrandId;
import com.capitole.common.QueryHandler;
import com.capitole.exception.NotFoundException;
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
                new ProductId(query.getProductId()),
                query.getApplicationDate(),
                new BrandId(query.getBrandId()));

        if (price == null){
            throw new NotFoundException("There are no prices with the search parameters.");
        }
        return new FindRateResponseDTO(
                query.getProductId(),
                query.getBrandId(),
                price.getPriceList().getId(),
                price.getRangeDate().getStartDate(),
                price.getRangeDate().getEndDate(),
                price.getPrice().getAmount() + " " + price.getPrice().getCurrency().getCurrencyCode());
    }
}
