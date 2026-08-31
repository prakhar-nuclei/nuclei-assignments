package assignment1.strategy;

import assignment1.constants.TaxConstants;
import assignment1.entity.Item;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class ManufacturedTaxStrategy implements TaxStrategy {

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