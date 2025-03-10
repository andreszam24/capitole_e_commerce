package rate;

import com.capitole.brand.Brand;
import com.capitole.brand.BrandId;
import com.capitole.price.*;
import com.capitole.product.Product;
import com.capitole.product.ProductId;
import com.capitole.rate.FindRateByBrandIdAndProductIdAndApplicationDateQuery;
import com.capitole.rate.FindRateByBrandIdAndProductIdAndApplicationDateQueryHandler;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Currency;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class FindRateByBrandIdAndProductIdAndApplicationDateQueryHandlerTest {

    @Test
    public void should_find_rate_by_brand_id_and_product_id_and_application_date() {
        //Arrange
        var priceId = new PriceId(1L);
        var brand = Brand.create(new BrandId(1L), "ZARA");
        var rangeDate = DateRange.create(LocalDateTime.now().minusDays(1), LocalDateTime.now());
        var priceList = PriceList.create(1);
        var product = Product.create(new ProductId(1), "product1");
        var priority = Priority.create(1);
        var money = Money.create(BigDecimal.valueOf(100), Currency.getInstance("EUR"));

        var price = Price.create(priceId, brand, rangeDate, priceList, product, priority, money);

        var priceRepository = Mockito.mock(PriceRepository.class);
        when(priceRepository.findRateByDateAndProductIdAndBrandId(any(), any(), any()))
                .thenReturn(price);

        var query = new FindRateByBrandIdAndProductIdAndApplicationDateQuery(
                rangeDate.getStartDate(),
                product.getId().id(),
                brand.getId().id());

        var handler = new FindRateByBrandIdAndProductIdAndApplicationDateQueryHandler(priceRepository);

        //Act
        var rate = handler.handle(query);

        //Assert
        verify(priceRepository, Mockito.times(1)).
                findRateByDateAndProductIdAndBrandId(any(), any(), any());

        assertEquals(brand.getId().id(), rate.brandId());
        assertEquals(rangeDate.getStartDate(), rate.startDate());
        assertEquals(rangeDate.getEndDate(), rate.endDate());
        assertEquals(priceList.getId(), rate.priceList());
        assertEquals(product.getId().id(), rate.productId());
        assertEquals(money.getAmount() + " " + money.getCurrency(), rate.price());
    }
}
