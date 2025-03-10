package com.capitole.rate;

import com.capitole.common.QueryBusImpl;
import com.capitole.rate.dto.FindRateResponseDTO;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/api")
public final class PriceController {

    private final QueryBusImpl queryBus;

    public PriceController(QueryBusImpl queryBus) {
        this.queryBus = queryBus;
    }

    @GetMapping("rate")
    public ResponseEntity<FindRateResponseDTO> findRate(
            @RequestParam long brandId,
            @RequestParam long productId,
            @RequestParam @DateTimeFormat LocalDateTime applicationDate) {

        var query = new FindRateByBrandIdAndProductIdAndApplicationDateQuery(applicationDate, productId, brandId);
        var response = queryBus.execute(query);

        return ResponseEntity.ok(response);
    }
}
