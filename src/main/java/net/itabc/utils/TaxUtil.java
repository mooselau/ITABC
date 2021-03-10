package net.itabc.utils;

import net.itabc.common.SocialBenefitsCalcResponse;
import net.itabc.vos.IndividualYearlyTaxesVO;
import net.itabc.vos.MonthlySocialBenefitVO;

public final class TaxUtil {
    private TaxUtil() {
    }

    public static void setSocialBenefits(IndividualYearlyTaxesVO yearlyTaxes, SocialBenefitsCalcResponse socialBenefits) {
        MonthlySocialBenefitVO monthlySocialBenefitVO = MonthlySocialBenefitVO.builder()
                .pension(socialBenefits.getPension().doubleValue())
                .unemployment(socialBenefits.getUnemployment().doubleValue())
                .medical(socialBenefits.getMedical().doubleValue())
                .housingFund(socialBenefits.getHousingFund().doubleValue())
                .build();
        yearlyTaxes.setMonthlySocialBenefitVO(monthlySocialBenefitVO);
    }
}
