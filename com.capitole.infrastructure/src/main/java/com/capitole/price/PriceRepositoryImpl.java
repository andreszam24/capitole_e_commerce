package com.capitole.price;

import com.capitole.brand.Brand;
import com.capitole.brand.BrandId;
import com.capitole.product.Product;
import com.capitole.product.ProductId;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Currency;

@Repository
public class PriceRepositoryImpl implements PriceRepository {

    private final PriceJpaRepository priceJpaRepository;

    public PriceRepositoryImpl(PriceJpaRepository priceJpaRepository) {
        this.priceJpaRepository = priceJpaRepository;
    }

    @Override
    public Price findRateByDateAndProductIdAndBrandId(LocalDateTime date, ProductId productId, BrandId brandId) {
        var priceDAO = priceJpaRepository.findApplicablePrice(brandId.id(), productId.id(), date);

        var priceId = new PriceId(priceDAO.getId());

        var brand = Brand.create(new BrandId(priceDAO.getBrand().getId()), priceDAO.getBrand().getName());
        var product = Product.create(new ProductId(priceDAO.getProduct().getId()), priceDAO.getProduct().getName());

        var rangeDate = DateRange.create(priceDAO.getStartDate(), priceDAO.getEndDate());
        var priceList = PriceList.create(priceDAO.getPriceList());

        var priority = Priority.create(priceDAO.getPriority());
        var money = Money.create(priceDAO.getPrice(), Currency.getInstance(priceDAO.getCurrency()));

        return Price.create(priceId, brand, rangeDate, priceList, product, priority, money);

    }
}
