package com.smart.edi.akuh.util;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 *
 * @author Kelsas
 */
public class RoundUtil {

    public static double round(double value, int places) {
        if (places < 0) {
            throw new IllegalArgumentException();
        }

        BigDecimal bd = new BigDecimal(Double.toString(value));
        bd = bd.setScale(places, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }
}
