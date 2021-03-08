package net.itabc.models;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class MonthlySocialBenefit {
    // 养老
    private BigDecimal pension;
    // 失业
    private BigDecimal unemployment;
    // 医疗
    private BigDecimal medical;
    // 工伤
    private BigDecimal employmentInjury;
    // 生育
    private BigDecimal maternity;
    // 住房公积金
    private BigDecimal housingFund;
    // 补充公积金
    private BigDecimal additionalHousingFund;
}
