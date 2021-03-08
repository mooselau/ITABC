package net.itabc.services;

import net.itabc.common.CalculationRequest;
import net.itabc.common.CalculationResponse;

public interface Calculator {
    public CalculationResponse calculate(CalculationRequest request);
}
