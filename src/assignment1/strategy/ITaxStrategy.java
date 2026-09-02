package assignment1.strategy;

import assignment1.entity.Item;

import java.math.BigDecimal;

public interface ITaxStrategy {

    BigDecimal calculateTax(Item item);
}
