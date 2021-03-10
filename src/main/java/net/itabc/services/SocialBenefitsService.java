package net.itabc.services;

import net.itabc.common.CalculationRequest;
import net.itabc.common.CalculationResponse;
import net.itabc.common.SocialBenefitsCalcResponse;
import net.itabc.models.SocialBenefitRates;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

import static net.itabc.utils.CalculationUtil.multiply;

/**
 * 社保、公积金计算器
 * <p>
 * 养老 Pension
 * 失业 Unemployment
 * 医疗 Medical
 * 工伤 EmploymentInjury
 * 生育 Maternity
 * 住房公积金 HousingFund
 * 补充公积金 AdditionalHousingFund
 * <p>
 * 个人 EE(Employee) 公司 ER(Employer)
 */
@Service
public class SocialBenefitsService implements Calculator {
    @Autowired
    private SocialBenefitsRatesHolder socialBenefitsRatesHolder;

    @Override
    public CalculationResponse calculate(CalculationRequest request) {
        BigDecimal income = request.getMonthlyIncome();
        SocialBenefitsCalcResponse response = new SocialBenefitsCalcResponse();
        // calculate actual social benefits
        SocialBenefitRates rates = socialBenefitsRatesHolder.generateSocialBenefitRates();
        response.setPension(multiply(income, rates.getPensionRate()));
        response.setUnemployment(multiply(income, rates.getUnemploymentRate()));
        response.setMedical(multiply(income, rates.getMedicalRate()));
        response.setHousingFund(multiply(income, rates.getHousingFundRate()));
        response.setInTotal(multiply(income, rates.getTotalRate()));
        return response;
    }
}
