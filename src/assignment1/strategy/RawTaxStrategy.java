package assignment1.strategy;

import assignment1.entity.Item;

import java.math.BigDecimal;

public class RawTaxStrategy implements TaxStrategy {

    @Override
    public BigDecimal calculateTax(Item item) {

        return item.getPrice()
                .multiply(new BigDecimal("0.125"));
    }
}