package net.itabc.vos;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class MonthlySocialBenefitVO {
    // 养老
    private Double pension;
    // 失业
    private Double unemployment;
    // 医疗
    private Double medical;
    // 工伤
    private Double employmentInjury;
    // 生育
    private Double maternity;
    // 住房公积金
    private Double housingFund;
    // 补充公积金
    private Double additionalHousingFund;
}
