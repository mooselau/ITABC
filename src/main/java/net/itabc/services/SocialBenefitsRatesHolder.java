package net.itabc.services;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import net.itabc.models.SocialBenefitRates;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Getter
@Setter
@Slf4j
@Component
@ConfigurationProperties(prefix = "itabc.social-benefits.rates")
public class SocialBenefitsRatesHolder {
    private String pension;
    private String unemployment;
    private String medical;
    private String employmentInjury;
    private String maternity;
    private String housingFund;
    private String additionalHousingFund;

    public SocialBenefitRates generateSocialBenefitRates() {
        return SocialBenefitRates.builder().pensionRate(BigDecimal.valueOf(Double.parseDouble(pension)))
                .unemploymentRate(BigDecimal.valueOf(Double.parseDouble(unemployment)))
                .medicalRate(BigDecimal.valueOf(Double.parseDouble(medical)))
                .housingFundRate(BigDecimal.valueOf(Double.parseDouble(housingFund))).build();
    }

}
