package assignment1.strategy;

import assignment1.constants.TaxConstants;
import assignment1.entity.Item;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class RawTaxStrategy implements TaxStrategy {

    @Override
    public BigDecimal calculateTax(Item item) {

        return item.getTotalPrice()                        // Quantity aur constant
                .multiply(TaxConstants.BASE_TAX_RATE)
                .setScale(TaxConstants.SCALE, RoundingMode.HALF_UP);
    }
}