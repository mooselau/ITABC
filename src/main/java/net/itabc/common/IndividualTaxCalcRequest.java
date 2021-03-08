package net.itabc.common;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

/**
 * Request for Calculate Tax For Each Month.
 */
@Setter
@Getter
@Builder
public class IndividualTaxCalcRequest extends CalculationRequest {
    private BigDecimal monthlySocialBenefitsTotal;
}
