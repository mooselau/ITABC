package net.itabc.services;

import lombok.extern.slf4j.Slf4j;
import net.itabc.common.IndividualTaxCalcRequest;
import net.itabc.common.IndividualTaxCalcResponse;
import net.itabc.common.SocialBenefitsCalcRequest;
import net.itabc.common.SocialBenefitsCalcResponse;
import net.itabc.vos.IndividualYearlyTaxesVO;
import net.itabc.vos.MonthlyTaxVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Arrays;

import static net.itabc.utils.TaxUtil.setSocialBenefits;

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
    public void yearlyTax(Double inputIncome) {
        BigDecimal monthlyIncome = BigDecimal.valueOf(inputIncome);

        IndividualYearlyTaxesVO individualYearlyTaxesVO = new IndividualYearlyTaxesVO();

        // step 1. calculate social benefits
        SocialBenefitsCalcRequest socialBenefitsCalcRequest = new SocialBenefitsCalcRequest();
        socialBenefitsCalcRequest.setMonthlyIncome(monthlyIncome);

        SocialBenefitsCalcResponse monthlySocialBenefits = (SocialBenefitsCalcResponse) socialBenefitsService
                .calculate(socialBenefitsCalcRequest);

        LOGGER.info("===>" + monthlySocialBenefits);

        // fill into yearly tax
        setSocialBenefits(individualYearlyTaxesVO, monthlySocialBenefits);

        MonthlyTaxVO[] monthlyTaxes = new MonthlyTaxVO[12];
        // 2. for loop to calculate monthly tax;
        for (int i = 0; i < 12; i++) {
            int month = i + 1;
            IndividualTaxCalcRequest individualTaxCalcRequest = new IndividualTaxCalcRequest();
            individualTaxCalcRequest.setMonthlyIncome(monthlyIncome);
            individualTaxCalcRequest.setMonthlySocialBenefitsTotal(monthlySocialBenefits.getInTotal());
            individualTaxCalcRequest.setMonth(month);

            if (i > 0) {
                individualTaxCalcRequest.setLastMonthAccumulativeTax(monthlyTaxes[i - 1].getTax());
            }

            /* Calculate Monthly Tax */
            IndividualTaxCalcResponse individualTax = (IndividualTaxCalcResponse) individualTaxService
                    .calculate(individualTaxCalcRequest);

            // month = i + 1, from 1 to 12
            monthlyTaxes[i] = MonthlyTaxVO.builder().tax(individualTax.getTax()).month(month).build();
        }

        individualYearlyTaxesVO.setMonthlyTaxVOs(Arrays.asList(monthlyTaxes));

        // TESTPOINT: print monthly tax
        individualYearlyTaxesVO.getMonthlyTaxVOs().forEach(tax -> LOGGER.info(tax.toString()));
    }
}
