package net.itabc.models;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Setter
@Getter
@Builder
public class TaxingRateBundle {
    private BigDecimal taxingRate; // 个税税率
    private BigDecimal deductionNumber; // 速算扣除
}
