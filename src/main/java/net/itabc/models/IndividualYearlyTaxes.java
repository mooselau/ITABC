package net.itabc.models;

import lombok.Data;

import java.util.List;

@Data
public class IndividualYearlyTaxes {
    private List<MonthlyTax> monthlyTaxes;
    private MonthlySocialBenefit monthlySocialBenefit;
}
