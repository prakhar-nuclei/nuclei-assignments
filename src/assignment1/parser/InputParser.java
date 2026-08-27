package assignment1.parser;

import assignment1.entity.Item;

import java.math.BigDecimal;

public class InputParser {


    private boolean isOption(String value) {
        return value.equals("-name")
                || value.equals("-price")
                || value.equals("-quantity")
                || value.equals("-type");
    }

    public Item parse(String[] args) {

        String name = null;
        String price = null;
        String quantity = null;
        String type = null;

        for (int i = 0; i < args.length; i += 2) {
            String option = args[i];

            if (i + 1 >= args.length || isOption(args[i + 1])) {
                throw new IllegalArgumentException(                         //missing value check
                        "Missing value for option: " + option
                );
            }

            String value = args[i + 1];

            switch (option) {
                case "-name":
                    if (name != null) {
                        throw new IllegalArgumentException(
                                "Duplicate option: " + option
                        );
                    }
                    name = value;
                    break;

                case "-price":                                      // unknown option
                    if (price != null) {
                        throw new IllegalArgumentException(
                                "Duplicate option: " + option
                        );
                    }
                    price = value;
                    break;

                case "-quantity":
                    if (quantity != null) {
                        throw new IllegalArgumentException(
                                "Duplicate option: " + option
                        );
                    }
                    quantity = value;
                    break;

                case "-type":
                    if (type != null) {
                        throw new IllegalArgumentException(
                                "Duplicate option: " + option
                        );
                    }
                    type = value;
                    break;

                default:
                    throw new IllegalArgumentException(
                            "Unknown option: " + option
                    );
            }
        }

        if (name == null) {
            throw new IllegalArgumentException(
                    "Missing required option: -name"
            );
        }

        if (price == null) {
            throw new IllegalArgumentException(
                    "Missing required option: -price"
            );
        }
                                                                         // duplicate option
        if (quantity == null) {
            throw new IllegalArgumentException(
                    "Missing required option: -quantity"
            );
        }

        if (type == null) {
            throw new IllegalArgumentException(
                    "Missing required option: -type"
            );
        }

        BigDecimal parsedPrice;
        int parsedQuantity;

        try {
            parsedPrice = new BigDecimal(price);
            parsedQuantity = Integer.parseInt(quantity);
        } catch (NumberFormatException exception) {                // string to correct dt
            throw new IllegalArgumentException(
                    "Price and quantity must be valid numbers."
            );
        }

        return new Item(name, parsedPrice, parsedQuantity, type);
    }
}