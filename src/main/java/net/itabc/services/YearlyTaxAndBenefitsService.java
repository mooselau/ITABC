package net.itabc.services;

import lombok.extern.slf4j.Slf4j;
import net.itabc.common.SocialBenefitsCalcRequest;
import net.itabc.common.SocialBenefitsCalcResponse;
import net.itabc.models.IndividualYearlyTaxes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Slf4j
@Service
public class YearlyTaxAndBenefitsService {

    @Autowired
    private IndividualTaxService individualTaxService;
    @Autowired
    private SocialBenefitsService socialBenefitsService;

    /**
     * 获取年度个税缴纳情况
     */
    public void yearlyTax() {
        double inputIncome = 5000.00d;
        BigDecimal monthlyIncome = BigDecimal.valueOf(inputIncome);

        IndividualYearlyTaxes individualYearlyTaxes = new IndividualYearlyTaxes();

        // step 1. calculate social benefits
        SocialBenefitsCalcRequest socialBenefitsCalcRequest = new SocialBenefitsCalcRequest();
        socialBenefitsCalcRequest.setMonthlyIncome(monthlyIncome);

        SocialBenefitsCalcResponse monthlySocialBenefits = (SocialBenefitsCalcResponse) socialBenefitsService
                .calculate(socialBenefitsCalcRequest);

        // fill into yearly tax
//        setSocialBenefits(individualYearlyTaxes, monthlySocialBenefits);
//
//        // TESTPOINT: print monthly social benefits
//        LOGGER.info(individualYearlyTaxes.getMonthlySocialBenefit().toString());
//
//        List<MonthlyTax> monthlyTaxes = new ArrayList<>(12);
//        // 2. for loop to calculate monthly tax;
//        for (int i = 0; i < 12; i++) {
//            IndividualTaxCalcRequest individualTaxCalcRequest = IndividualTaxCalcRequest.builder()
//                    .monthlySocialBenefitsTotal().build();
//            IndividualTaxCalcResponse individualTax = (IndividualTaxCalcResponse) individualTaxService
//                    .calculate(individualTaxCalcRequest);
//            // month = i + 1, from 1 to 12
//            monthlyTaxes.add(MonthlyTax.builder().tax(individualTax.getTax()).month(i + 1).build());
//        }
//
//        individualYearlyTaxes.setMonthlyTaxes(monthlyTaxes);
//
//        // TESTPOINT: print monthly tax
//        individualYearlyTaxes.getMonthlyTaxes().forEach(tax -> LOGGER.info(tax.toString()));
    }
}
