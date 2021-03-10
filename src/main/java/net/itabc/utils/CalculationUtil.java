package net.itabc.utils;

import java.math.BigDecimal;
import java.math.RoundingMode;

public final class CalculationUtil {

    private CalculationUtil() {
    }

    /**
     * Multiplication, HALF_UP Rounding with 2 Scale.
     */
    public static BigDecimal multiply(BigDecimal a, BigDecimal b) {
        return a.multiply(b).setScale(2, RoundingMode.HALF_UP);
    }

    /**
     * Subtraction, HALF_UP Rounding with 2 Scale. Result must larger or equals ZERO.
     */
    public static BigDecimal subtract(BigDecimal a, BigDecimal b) {
        BigDecimal result = a.subtract(b).setScale(2, RoundingMode.HALF_UP);
        // minimal result is 0 not negative number
        return result.compareTo(BigDecimal.ZERO) > 0 ? result : BigDecimal.ZERO;
    }

}
