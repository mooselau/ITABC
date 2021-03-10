package net.itabc.models;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Builder
@Setter
@Getter
public class SocialBenefitRates {
    private BigDecimal pensionRate;
    private BigDecimal unemploymentRate;
    private BigDecimal medicalRate;
    private BigDecimal employmentInjuryRate; // 0.00 for employee
    private BigDecimal maternityRate; // 0.00 for employee
    private BigDecimal housingFundRate;
    private BigDecimal additionalHousingFundRate; // mostly none

    /**
     * Get total rate, including: pension, unemployment, medical & housing fund.
     */
    public BigDecimal getTotalRate() {
        return pensionRate.add(unemploymentRate).add(medicalRate).add(housingFundRate);
    }
}
