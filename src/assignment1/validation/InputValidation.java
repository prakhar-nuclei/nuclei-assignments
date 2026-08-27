package assignment1.validation;

import assignment1.entity.Item;

import java.math.BigDecimal;

public class InputValidation {

    public void validate(Item item) {

        if (item.getName() == null || item.getName().isBlank()) {
            throw new IllegalArgumentException(                           // name check not null
                                                                          //  not blank
                    "Item name cannot be blank."
            );
        }

        if (item.getPrice() == null
                || item.getPrice().compareTo(BigDecimal.ZERO) <= 0) {
                                                                            // rice check
            throw new IllegalArgumentException(
                    "Item price must be greater than zero."
            );
        }

        if (item.getQuantity() <= 0) {
            throw new IllegalArgumentException(
                    "Item quantity must be greater than zero."         // quantity check
            );
        }

        String type = item.getType();

        if (type == null ||
                (!type.equalsIgnoreCase("raw")
                        && !type.equalsIgnoreCase("manufactured")     // type check
                        && !type.equalsIgnoreCase("imported"))) {

            throw new IllegalArgumentException(
                    "Invalid item type. Allowed values are: raw, manufactured, imported."
            );
        }
    }
}