package net.itabc.models;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Builder
@Setter
@Getter
public class MonthlyTax {
    private Integer month;
    private BigDecimal tax;
}
