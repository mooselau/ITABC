package net.itabc.services;

import net.itabc.common.CalculationRequest;
import net.itabc.common.CalculationResponse;
import net.itabc.common.IndividualTaxCalcResponse;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

/**
 * 个税计算器
 */
@Service
public class IndividualTaxService implements Calculator {

    @Override
    public CalculationResponse calculate(CalculationRequest request) {
//        BigDecimal months = request.getMonth();
        BigDecimal months = BigDecimal.valueOf(1l);
        // 月基本工资
        BigDecimal baseSalary = request.getMonthlyIncome();
        // 征税起征点
        BigDecimal taxingSalaryStartLine = BigDecimal.valueOf(5000d);
        // 社保公积金 TODO
        BigDecimal socialBenefitsAndHousingFund = BigDecimal.valueOf(2000d);
        // 专项附加扣除
        BigDecimal specificAdditionalDeduction = BigDecimal.valueOf(0d);
        // 商业保险
        BigDecimal commercialInsurance = BigDecimal.valueOf(100d);

        // 本月应纳税所得
        /* 本月应纳税所得 = 月基本工资 - 征税起征点 - 社保公积金 - 专项附加扣除 + 商业保险 */
        BigDecimal currentMonthTaxingIncome = baseSalary.subtract(taxingSalaryStartLine)
                .subtract(socialBenefitsAndHousingFund).subtract(specificAdditionalDeduction)
                .add(commercialInsurance);

        // 累计应纳税所得 = 本月应纳税额的N倍
        BigDecimal accumulativeTaxingIncome = currentMonthTaxingIncome.multiply(months);

        /* TODO: Lookup Table, get taxingRate & deductionNumber */
        BigDecimal taxingRate = BigDecimal.valueOf(0.03d); // 个税税率
        BigDecimal deductionNumber = BigDecimal.valueOf(0d); // 速算扣除

        // 本月累计应缴纳税额 = 累计应纳税所得额 * 税率 - 速算扣除数
        BigDecimal currentMonthAccumulativeTax = accumulativeTaxingIncome.multiply(taxingRate)
                .subtract(deductionNumber);
        // 上月累计已缴纳税额 TODO
        BigDecimal lastMonthAccumulativeTax = BigDecimal.valueOf(10d);

        // 本期应缴税额 = 本月累计应缴纳税额 - 上月累计已缴纳税额
        BigDecimal currentMonthTax = currentMonthAccumulativeTax.subtract(lastMonthAccumulativeTax);

        return new IndividualTaxCalcResponse();
    }

}
