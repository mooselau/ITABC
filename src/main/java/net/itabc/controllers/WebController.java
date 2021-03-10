package net.itabc.controllers;

import net.itabc.services.YearlyTaxAndBenefitsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WebController {
    @Autowired
    private YearlyTaxAndBenefitsService yearlyTaxAndBenefitsService;

    @GetMapping("/v1/hello")
    public String hello() {
        return "Hi, there!";
    }

    @GetMapping("/v1/trigger")
    public void demo() {
        yearlyTaxAndBenefitsService.yearlyTax(8001d);
    }
}
