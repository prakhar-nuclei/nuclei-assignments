package com.nuclei.assignment3.strategy;

import com.nuclei.assignment3.entity.Item;

import java.math.BigDecimal;

public interface ITaxStrategy {

    BigDecimal calculateTax(Item item);
}
