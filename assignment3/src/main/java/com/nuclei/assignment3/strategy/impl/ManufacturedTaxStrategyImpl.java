package com.nuclei.assignment3.strategy.impl;

import com.nuclei.assignment3.constants.TaxConstants;
import com.nuclei.assignment3.entity.Item;
import com.nuclei.assignment3.strategy.ITaxStrategy;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class ManufacturedTaxStrategyImpl implements ITaxStrategy {

    @Override
    public BigDecimal calculateTax(Item item) {

        BigDecimal totalPrice = item.getTotalPrice();

        BigDecimal baseTax = totalPrice
                .multiply(TaxConstants.BASE_TAX_RATE);

        BigDecimal additionalTax = totalPrice
                .add(baseTax)
                .multiply(TaxConstants.MANUFACTURED_TAX_RATE);

        return baseTax.add(additionalTax)
                .setScale(TaxConstants.SCALE, RoundingMode.HALF_UP);
    }
}
