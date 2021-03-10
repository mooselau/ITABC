package net.itabc.common;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

/**
 * Request for Calculate Tax For Each Month.
 */
@Setter
@Getter
public class IndividualTaxCalcRequest extends CalculationRequest {
    private BigDecimal monthlySocialBenefitsTotal = BigDecimal.ZERO;
    private BigDecimal lastMonthAccumulativeTax = BigDecimal.ZERO; // 上月累计已缴纳税额
}
