package net.itabc.services;

import net.itabc.common.CalculationRequest;
import net.itabc.common.CalculationResponse;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

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
    @Override
    public CalculationResponse calculate(CalculationRequest request) {
        BigDecimal income = request.getMonthlyIncome();
        // TODO: finish here..
        return null;
    }
}
