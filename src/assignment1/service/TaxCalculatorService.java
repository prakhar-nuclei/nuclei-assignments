package assignment1.service;

import assignment1.entity.Item;
import assignment1.entity.ItemResult;
import assignment1.strategy.ImportedTaxStrategy;
import assignment1.strategy.ManufacturedTaxStrategy;
import assignment1.strategy.RawTaxStrategy;
import assignment1.strategy.TaxStrategy;

import java.math.BigDecimal;

public class TaxCalculatorService {

    public BigDecimal calculateTax(Item item) {

        TaxStrategy taxStrategy;

        switch (item.getType().toLowerCase()) {
            case "raw":
                taxStrategy = new RawTaxStrategy();
                break;

            case "manufactured":
                taxStrategy = new ManufacturedTaxStrategy();
                break;

            case "imported":
                taxStrategy = new ImportedTaxStrategy();
                break;

            default:
                throw new IllegalArgumentException(
                        "Unsupported item type."
                );
        }

        return taxStrategy.calculateTax(item);
    }


    public ItemResult calculate(Item item) {

        BigDecimal tax = calculateTax(item);

        BigDecimal finalPrice = item.getPrice().add(tax);

        return new ItemResult(item, tax, finalPrice);
    }
}