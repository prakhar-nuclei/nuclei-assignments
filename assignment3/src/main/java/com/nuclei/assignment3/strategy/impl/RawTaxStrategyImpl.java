package com.nuclei.assignment3.strategy.impl;

import com.nuclei.assignment3.constants.TaxConstants;
import com.nuclei.assignment3.entity.Item;
import com.nuclei.assignment3.strategy.ITaxStrategy;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class RawTaxStrategyImpl implements ITaxStrategy {

    @Override
    public BigDecimal calculateTax(Item item) {

        return item.getTotalPrice()
                .multiply(TaxConstants.BASE_TAX_RATE)
                .setScale(TaxConstants.SCALE, RoundingMode.HALF_UP);
    }
}
