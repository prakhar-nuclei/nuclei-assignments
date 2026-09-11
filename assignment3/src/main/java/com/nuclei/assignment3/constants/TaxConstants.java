package com.nuclei.assignment3.constants;

import java.math.BigDecimal;

public final class TaxConstants {

    public static final BigDecimal BASE_TAX_RATE  =
            new BigDecimal("0.125");

    public static final BigDecimal MANUFACTURED_TAX_RATE =
            new BigDecimal("0.02");


    public static final BigDecimal IMPORT_DUTY_RATE  = new BigDecimal("0.10");

    public static final BigDecimal IMPORTED_SURCHARGE_LIMIT_1 =
            new BigDecimal("100");

    public static final BigDecimal IMPORTED_SURCHARGE_LIMIT_2 =
            new BigDecimal("200");

    public static final BigDecimal IMPORTED_SURCHARGE_1 =
            new BigDecimal("5");

    public static final BigDecimal IMPORTED_SURCHARGE_2 =
            new BigDecimal("10");

    public static final BigDecimal IMPORTED_SURCHARGE_RATE =
            new BigDecimal("0.05");

    public static final int SCALE = 2;

    private TaxConstants() {
    }
}
