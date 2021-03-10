package net.itabc.services;

import lombok.extern.slf4j.Slf4j;
import net.itabc.models.TaxingRateBundle;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Slf4j
@Component
public class TaxingLookUpTable {

    public TaxingRateBundle lookUp(BigDecimal accumulativeTaxingIncome) {
        // TODO
        return TaxingRateBundle.builder().taxingRate(BigDecimal.valueOf(0.03d))
                .deductionNumber(BigDecimal.valueOf(0d)).build();
    }

}
