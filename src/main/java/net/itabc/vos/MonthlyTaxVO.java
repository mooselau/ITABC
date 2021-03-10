package net.itabc.vos;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Builder
@Data
public class MonthlyTaxVO {
    private Integer month;
    private BigDecimal tax;
}
