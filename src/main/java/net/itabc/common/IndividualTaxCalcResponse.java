package net.itabc.common;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

/**
 * Response for Calculate
 */
@Setter
@Getter
public class IndividualTaxCalcResponse extends CalculationResponse {
    private BigDecimal tax;
}
