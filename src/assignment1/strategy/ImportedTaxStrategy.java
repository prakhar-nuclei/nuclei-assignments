package assignment1.strategy;

import assignment1.entity.Item;

import java.math.BigDecimal;

public class ImportedTaxStrategy implements TaxStrategy {

    @Override
    public BigDecimal calculateTax(Item item) {

        BigDecimal importDuty = item.getPrice()
                .multiply(new BigDecimal("0.10"));

        BigDecimal costAfterDuty = item.getPrice()
                .add(importDuty);

        BigDecimal surcharge;

        if (costAfterDuty.compareTo(new BigDecimal("100")) <= 0) {
            surcharge = new BigDecimal("5");
        } else if (costAfterDuty.compareTo(new BigDecimal("200")) <= 0) {
            surcharge = new BigDecimal("10");
        } else {
            surcharge = costAfterDuty
                    .multiply(new BigDecimal("0.05"));
        }

        return importDuty.add(surcharge);
    }
}