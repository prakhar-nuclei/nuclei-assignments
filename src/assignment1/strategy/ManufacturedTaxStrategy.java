package assignment1.strategy;

import assignment1.entity.Item;

import java.math.BigDecimal;

public class ManufacturedTaxStrategy implements TaxStrategy {

    @Override
    public BigDecimal calculateTax(Item item) {

        BigDecimal baseTax = item.getPrice()
                .multiply(new BigDecimal("0.125"));

        BigDecimal additionalTax = item.getPrice()
                .add(baseTax)
                .multiply(new BigDecimal("0.02"));

        return baseTax.add(additionalTax);
    }
}