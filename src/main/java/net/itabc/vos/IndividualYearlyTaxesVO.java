package net.itabc.vos;

import lombok.Data;

import java.util.List;


@Data
public class IndividualYearlyTaxesVO {
    private List<MonthlyTaxVO> monthlyTaxVOs;
    private MonthlySocialBenefitVO monthlySocialBenefitVO;
}
