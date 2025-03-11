package com.capitole.rate;

import com.capitole.common.QueryBusImpl;
import com.capitole.rate.dto.FindRateResponse;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;


@RestController
@RequestMapping("/api")
public final class PriceController implements RateApi {

    private final QueryBusImpl queryBus;

    public PriceController(QueryBusImpl queryBus) {
        this.queryBus = queryBus;
    }

    @GetMapping("rate")
    @Override
    public ResponseEntity<FindRateResponse> rateGet(
            Long brandId,
            Long productId,
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime applicationDate) {

        var query = new FindRateByBrandIdAndProductIdAndApplicationDateQuery(applicationDate, productId, brandId);
        var queryResponse = queryBus.execute(query);

        var findRateDTO = new FindRateResponse();
        findRateDTO.setPrice(queryResponse.price());
        findRateDTO.setBrandId(queryResponse.brandId());
        findRateDTO.priceList(queryResponse.priceList());
        findRateDTO.setProductId(queryResponse.productId());
        findRateDTO.setStartDate(queryResponse.startDate());
        findRateDTO.setEndDate(queryResponse.endDate());
        return ResponseEntity.ok(findRateDTO);
    }
}
