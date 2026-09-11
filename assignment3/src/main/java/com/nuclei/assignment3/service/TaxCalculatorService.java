package com.nuclei.assignment3.service;

import com.nuclei.assignment3.constants.TaxConstants;
import com.nuclei.assignment3.entity.Item;
import com.nuclei.assignment3.entity.ItemResult;
import com.nuclei.assignment3.enums.ItemTypeEnum;
import com.nuclei.assignment3.strategy.ITaxStrategy;
import com.nuclei.assignment3.strategy.impl.ImportedTaxStrategyImpl;
import com.nuclei.assignment3.strategy.impl.ManufacturedTaxStrategyImpl;
import com.nuclei.assignment3.strategy.impl.RawTaxStrategyImpl;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Map;

@Service
public class TaxCalculatorService {

    private static final Map<ItemTypeEnum, ITaxStrategy> STRATEGIES = Map.of(
            ItemTypeEnum.RAW, new RawTaxStrategyImpl(),
            ItemTypeEnum.MANUFACTURED, new ManufacturedTaxStrategyImpl(),
            ItemTypeEnum.IMPORTED, new ImportedTaxStrategyImpl()
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
