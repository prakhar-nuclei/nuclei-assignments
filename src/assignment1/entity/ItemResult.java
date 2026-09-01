package assignment1.entity;

import java.math.BigDecimal;

public class ItemResult {

    private final Item item;
    private final BigDecimal tax;
    private final BigDecimal finalPrice;

    public ItemResult(final Item item, final BigDecimal tax, final BigDecimal finalPrice) {
        this.item = item;
        this.tax = tax;
        this.finalPrice = finalPrice;
    }

    public Item getItem() {
        return item;
    }

    public BigDecimal getTax() {
        return tax;
    }

    public BigDecimal getFinalPrice() {
        return finalPrice;
    }
}