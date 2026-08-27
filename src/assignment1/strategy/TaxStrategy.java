package assignment1.strategy;

import assignment1.entity.Item;

import java.math.BigDecimal;

public interface TaxStrategy {

    BigDecimal calculateTax(Item item);
}
