package assignment1.service;

import assignment1.constants.TaxConstants;
import assignment1.entity.Item;
import assignment1.entity.ItemResult;
import assignment1.entity.ItemType;
import assignment1.strategy.ImportedTaxStrategy;
import assignment1.strategy.ManufacturedTaxStrategy;
import assignment1.strategy.RawTaxStrategy;
import assignment1.strategy.TaxStrategy;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Map;

public class TaxCalculatorService {

    private static final Map<ItemType, TaxStrategy> STRATEGIES = Map.of(
            ItemType.RAW, new RawTaxStrategy(),
            ItemType.MANUFACTURED, new ManufacturedTaxStrategy(),
            ItemType.IMPORTED, new ImportedTaxStrategy()
    );

    public BigDecimal calculateTax(Item item) {

        TaxStrategy taxStrategy = STRATEGIES.get(item.getType());

        if (taxStrategy == null) {
            throw new IllegalArgumentException(
                    "Unsupported item type."
            );
        }

        return taxStrategy.calculateTax(item);
    }

    public ItemResult calculate(Item item) {

        BigDecimal tax = calculateTax(item);

        BigDecimal finalPrice = item.getTotalPrice().add(tax)
                .setScale(TaxConstants.SCALE, RoundingMode.HALF_UP);

        return new ItemResult(item, tax, finalPrice);
    }
}