package rate;

import com.capitole.brand.Brand;
import com.capitole.exception.NegativeOrNullNumberException;
import com.capitole.exception.StartDateAfterEndDateException;
import com.capitole.brand.BrandId;
import com.capitole.price.*;
import com.capitole.product.Product;
import com.capitole.product.ProductId;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Currency;

import static org.junit.jupiter.api.Assertions.*;

public class PriceTest {

    private PriceId validPriceId;
    private Brand validBrand;
    private DateRange validDateRange;
    private Product validProduct;

    private PriceList validPriceList;

    private Priority validPriority;

    private Money validMoney;

    @BeforeEach
    void setUp() {
        validPriceId = new PriceId(1);
        validBrand = Brand.create(new BrandId(1), "Zara");
        validDateRange = DateRange.create(
                LocalDateTime.now().minusDays(1),
                LocalDateTime.now().plusDays(1)
        );
        validProduct = Product.create(new ProductId(35455L), "Product 1");
        validPriceList = PriceList.create(1);
        validPriority = Priority.create(1);
        validMoney = Money.create(new BigDecimal("35.50"), Currency.getInstance("EUR"));
    }

    @Test
    void createPrice_validArguments_shouldCreatePrice() {

        // Arrange

        // Act
        Price price = Price.create(validPriceId, validBrand, validDateRange, validPriceList, validProduct, validPriority, validMoney);

        // Assert
        assertNotNull(price);
        assertEquals(validPriceId, price.getPriceId());
        assertEquals(validBrand, price.getBrand());
        assertEquals(validDateRange, price.getRangeDate());
        assertEquals(validPriceList, price.getPriceList());
        assertEquals(validProduct, price.getProduct());
        assertEquals(validPriority, price.getPriority());
        assertEquals(validMoney, price.getMoney());

    }

    @Test
    void createPrice_negativePrice_shouldThrowException() {

        //Arrange
        var amount = new BigDecimal("-35.50");
        var currency = Currency.getInstance("USD");

        //Act
        NegativeOrNullNumberException exception = assertThrows(NegativeOrNullNumberException.class, () ->
                Money.create(amount, currency));

        //Assert
        assertEquals("The field price cannot be negative or null", exception.getMessage());
    }

    @Test
    void createDateRange_endDateBeforeStartDate_shouldThrowException() {

        //Arrange
        var startDate = LocalDateTime.now().plusDays(1);
        var endDate = LocalDateTime.now().minusDays(1);

        //Act
        StartDateAfterEndDateException exception = assertThrows(StartDateAfterEndDateException.class, () ->
                DateRange.create(startDate, endDate));

        //Assert
        assertEquals("The start date " + startDate.toString() + ", must be before than end date " + endDate.toString(), exception.getMessage());
    }
}
