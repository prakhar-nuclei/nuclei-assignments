package assignment1.strategy.impl;

import assignment1.constants.TaxConstants;
import assignment1.entity.Item;
import assignment1.strategy.ITaxStrategy;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class RawITaxStrategyImpl implements ITaxStrategy {

    @Override
    public BigDecimal calculateTax(Item item) {

        return item.getTotalPrice()                        // Quantity aur constant
                .multiply(TaxConstants.BASE_TAX_RATE)
                .setScale(TaxConstants.SCALE, RoundingMode.HALF_UP);
    }
}