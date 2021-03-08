package net.itabc.common;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public abstract class CalculationRequest {
    protected BigDecimal monthlyIncome;
    protected Integer month;
}
