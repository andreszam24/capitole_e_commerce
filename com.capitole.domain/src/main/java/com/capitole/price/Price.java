package com.capitole.price;

import com.capitole.brand.Brand;
import com.capitole.brand.BrandId;
import com.capitole.common.DomainEntity;
import com.capitole.common.EntityBase;
import com.capitole.exception.NegativeOrNullNumberException;
import com.capitole.exception.NullOrEmptyValueException;
import com.capitole.price.event.PriceCreatedEvent;
import com.capitole.product.Product;

import java.math.BigDecimal;

@DomainEntity
public final class Price extends EntityBase {

    private final PriceId priceId;

    private final Brand brand;

    private final DateRange rangeDate;

    private final PriceList priceList;

    private final Product product;

    private final Priority priority;

    private final Money price;

    private Price(PriceId priceId, Brand brand, DateRange rangeDate, PriceList priceList, Product product, Priority priority, Money price) {
        this.priceId = priceId;
        this.brand = brand;
        this.rangeDate = rangeDate;
        this.priceList = priceList;
        this.product = product;
        this.priority = priority;
        this.price = price;

        addDomainEvent(new PriceCreatedEvent(this));
    }

    public static Price create(PriceId priceId, Brand brand, DateRange rangeDate, PriceList priceList, Product product, Priority priority, Money price) {
        return new Price(priceId, brand, rangeDate, priceList, product, priority, price);
    }

    public Money getPrice() {
        return price;
    }

    public PriceId getPriceId() {
        return priceId;
    }

    public Brand getBrand() {
        return brand;
    }

    public DateRange getRangeDate() {
        return rangeDate;
    }

    public PriceList getPriceList() {
        return priceList;
    }

    public Product getProduct() {
        return product;
    }

    public Priority getPriority() {
        return priority;
    }
}
