package assignment1.entity;

import java.math.BigDecimal;

public class Item {

    private final String name;
    private final BigDecimal price;
    private final int quantity;
    private final String type;

    public Item(String name, BigDecimal price, int quantity, String type) {
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

    public String getType() {
        return type;
    }
}
