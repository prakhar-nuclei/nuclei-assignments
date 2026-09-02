package assignment1.service;

import assignment1.constants.TaxConstants;
import assignment1.entity.Item;
import assignment1.entity.ItemResult;
import assignment1.enums.ItemTypeEnum;
import assignment1.strategy.impl.ImportedITaxStrategyImpl;
import assignment1.strategy.impl.ManufacturedITaxStrategyImpl;
import assignment1.strategy.impl.RawITaxStrategyImpl;
import assignment1.strategy.ITaxStrategy;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Map;

public class TaxCalculatorService {

    private static final Map<ItemTypeEnum, ITaxStrategy> STRATEGIES = Map.of(
            ItemTypeEnum.RAW, new RawITaxStrategyImpl(),
            ItemTypeEnum.MANUFACTURED, new ManufacturedITaxStrategyImpl(),
            ItemTypeEnum.IMPORTED, new ImportedITaxStrategyImpl()
    );

    public BigDecimal calculateTax(Item item) {

        ITaxStrategy ITaxStrategy = STRATEGIES.get(item.getType());

        if (ITaxStrategy == null) {
            throw new IllegalArgumentException(
                    "Unsupported item type."
            );
        }

        return ITaxStrategy.calculateTax(item);
    }

    public ItemResult calculate(Item item) {

        BigDecimal tax = calculateTax(item);

        BigDecimal finalPrice = item.getTotalPrice().add(tax)
                .setScale(TaxConstants.SCALE, RoundingMode.HALF_UP);

        return new ItemResult(item, tax, finalPrice);
    }
}