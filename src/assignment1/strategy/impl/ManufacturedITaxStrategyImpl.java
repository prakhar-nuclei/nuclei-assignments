package assignment1.strategy.impl;

import assignment1.constants.TaxConstants;
import assignment1.entity.Item;
import assignment1.strategy.ITaxStrategy;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class ManufacturedITaxStrategyImpl implements ITaxStrategy {

    @Override
    public BigDecimal calculateTax(Item item) {

        BigDecimal totalPrice = item.getTotalPrice();    // Quantity

        BigDecimal baseTax = totalPrice
                .multiply(TaxConstants.BASE_TAX_RATE);

        BigDecimal additionalTax = totalPrice
                .add(baseTax)
                .multiply(TaxConstants.MANUFACTURED_TAX_RATE); // constants

        return baseTax.add(additionalTax)
                .setScale(TaxConstants.SCALE, RoundingMode.HALF_UP);
    }
}