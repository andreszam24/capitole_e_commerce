package com.capitole.price;

import com.capitole.brand.Brand;
import com.capitole.brand.BrandId;
import com.capitole.product.Product;
import com.capitole.product.ProductId;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Currency;

@Service
public class PriceRepositoryImpl implements PriceRepository{
    private final PriceJpaRepository priceJpaRepository;

    public PriceRepositoryImpl(PriceJpaRepository priceJpaRepository) {
        this.priceJpaRepository = priceJpaRepository;
    }

    @Override
    public Price findRateByDateAndProductIdAndBrandId(ProductId productId, LocalDateTime date, BrandId brandId) {
        var priceDAO = this.priceJpaRepository.findApplicablePrice(brandId.id(), date, productId.id());
        if (priceDAO == null){
            return null;
        }
        var priceId = new PriceId(priceDAO.getId());
        var brand = Brand.create(brandId,priceDAO.getBrand().getName());
        var rangeDate = DateRange.create(priceDAO.getStartDate(), priceDAO.getEndDate());
        var priceList = PriceList.create(priceDAO.getPriceList());
        var product = Product.create(productId, priceDAO.getProduct().getName());
        var priority = Priority.create(priceDAO.getPriority());
        var money = Money.create(priceDAO.getPrice(), Currency.getInstance(priceDAO.getCurrency()));

        return Price.create(priceId,brand,rangeDate,priceList,product,priority,money);
    }
}
