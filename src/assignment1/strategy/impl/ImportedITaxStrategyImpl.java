package assignment1.strategy.impl;

import assignment1.constants.TaxConstants;
import assignment1.entity.Item;
import assignment1.strategy.ITaxStrategy;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class ImportedITaxStrategyImpl implements ITaxStrategy {

    @Override
    public BigDecimal calculateTax(Item item) {

        BigDecimal importDuty = item.getTotalPrice()
                .multiply(TaxConstants.IMPORT_DUTY_RATE);

        BigDecimal costAfterDuty = item.getTotalPrice()
                .add(importDuty);

        BigDecimal surcharge;

        if (costAfterDuty.compareTo(TaxConstants.IMPORTED_SURCHARGE_LIMIT_1) <= 0) {
            surcharge = TaxConstants.IMPORTED_SURCHARGE_1;
        } else if (costAfterDuty.compareTo(TaxConstants.IMPORTED_SURCHARGE_LIMIT_2) <= 0) {
            surcharge = TaxConstants.IMPORTED_SURCHARGE_2;
        } else {
            surcharge = costAfterDuty
                    .multiply(TaxConstants.IMPORTED_SURCHARGE_RATE);
        }

        return importDuty.add(surcharge)
                .setScale(TaxConstants.SCALE, RoundingMode.HALF_UP);
    }
}