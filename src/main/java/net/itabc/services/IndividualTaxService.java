package net.itabc.services;

import lombok.extern.slf4j.Slf4j;
import net.itabc.common.CalculationRequest;
import net.itabc.common.CalculationResponse;
import net.itabc.common.IndividualTaxCalcRequest;
import net.itabc.common.IndividualTaxCalcResponse;
import net.itabc.models.TaxingRateBundle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.math.BigDecimal;

import static net.itabc.utils.CalculationUtil.multiply;
import static net.itabc.utils.CalculationUtil.subtract;

/**
 * 个税计算器
 */
@Slf4j
@Service
public class IndividualTaxService implements Calculator {

    @Autowired
    private TaxingLookUpTable table;

    @Value("${itabc.taxing.start-line}")
    private String taxingStartLine;

    @PostConstruct
    public void init() {
        LOGGER.info("===> " + taxingStartLine);
    }

    @Override
    public CalculationResponse calculate(CalculationRequest request) {
        IndividualTaxCalcRequest actualRequest = (IndividualTaxCalcRequest) request;

        Integer months = actualRequest.getMonth();
        // 月基本工资
        BigDecimal baseSalary = actualRequest.getMonthlyIncome();
        // 征税起征点
        BigDecimal taxingSalaryStartLine = BigDecimal.valueOf(Double.parseDouble(taxingStartLine));
        // 社保公积金
        BigDecimal socialBenefitsAndHousingFund = actualRequest.getMonthlySocialBenefitsTotal();
        // 专项附加扣除 TODO
        BigDecimal specificAdditionalDeduction = BigDecimal.valueOf(0d);
        // 商业保险 TODO
        BigDecimal commercialInsurance = BigDecimal.valueOf(0d);

        // 本月应纳税所得
        /* 本月应纳税所得 = 月基本工资 - 征税起征点 - 社保公积金 - 专项附加扣除 + 商业保险 */
        BigDecimal currentMonthTaxingIncome = subtract(subtract(subtract(baseSalary, taxingSalaryStartLine),
                socialBenefitsAndHousingFund), specificAdditionalDeduction).add(commercialInsurance);

        // 累计应纳税所得 = 本月应纳税额的N倍
        BigDecimal accumulativeTaxingIncome = multiply(currentMonthTaxingIncome, BigDecimal.valueOf(months));

        /* TODO: Lookup Table, get taxingRate & deductionNumber */
        TaxingRateBundle taxingRateBundle = table.lookUp(accumulativeTaxingIncome);
        BigDecimal taxingRate = taxingRateBundle.getTaxingRate(); // 个税税率
        BigDecimal deductionNumber = taxingRateBundle.getDeductionNumber(); // 速算扣除

        // 本月累计应缴纳税额 = 累计应纳税所得额 * 税率 - 速算扣除数
        BigDecimal currentMonthAccumulativeTax = subtract(multiply(accumulativeTaxingIncome, taxingRate), deductionNumber);
        // 上月累计已缴纳税额
        BigDecimal lastMonthAccumulativeTax = actualRequest.getLastMonthAccumulativeTax();

        // 本期应缴税额 = 本月累计应缴纳税额 - 上月累计已缴纳税额
        BigDecimal currentMonthTax = subtract(currentMonthAccumulativeTax, lastMonthAccumulativeTax);

        IndividualTaxCalcResponse response = new IndividualTaxCalcResponse();
        response.setTax(currentMonthTax);

        return response;
    }

}
