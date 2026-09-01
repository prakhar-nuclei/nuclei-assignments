package assignment1.entity;

import assignment1.enums.ItemTypeEnum;

import java.math.BigDecimal;

public class Item {

    private final String name;
    private final BigDecimal price;
    private final int quantity;
    private final ItemTypeEnum type;

    public Item(String name, BigDecimal price, int quantity, ItemTypeEnum type) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public int getQuantity() {
        return quantity;
    }

    public BigDecimal getTotalPrice() {
        return price.multiply(BigDecimal.valueOf(quantity));
    }

    public ItemTypeEnum getType() {
        return type;
    }
}
