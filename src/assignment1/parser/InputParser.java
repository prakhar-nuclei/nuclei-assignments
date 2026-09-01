package assignment1.parser;

import assignment1.constants.InputConstants;
import assignment1.entity.Item;
import assignment1.enums.ItemTypeEnum;
import assignment1.exception.ParseException;

import java.math.BigDecimal;

public class InputParser {


    private boolean isOption(String value) {
        return value.equals(InputConstants.NAME_OPTION)
                || value.equals(InputConstants.PRICE_OPTION)
                || value.equals(InputConstants.QUANTITY_OPTION)
                || value.equals(InputConstants.TYPE_OPTION);
    }

    public Item parse(String[] args) {

        String name = null;
        String price = null;
        String quantity = null;
        String type = null;

        for (int i = 0; i < args.length; i += 2) {
            String option = args[i];

            if (i + 1 >= args.length || isOption(args[i + 1])) {
                throw new ParseException(                                //option check
                        "Missing value for option: " + option
                );
            }

            String value = args[i + 1];

            switch (option) {
                case InputConstants.NAME_OPTION:
                    if (name != null) {
                        throw new ParseException(
                                "Duplicate option: " + option
                        );
                    }
                    name = value;
                    break;

                case InputConstants.PRICE_OPTION:                                      // unknown option
                    if (price != null) {
                        throw new ParseException(
                                "Duplicate option: " + option
                        );
                    }
                    price = value;
                    break;

                case InputConstants.QUANTITY_OPTION:
                    if (quantity != null) {
                        throw new ParseException(
                                "Duplicate option: " + option
                        );
                    }
                    quantity = value;
                    break;

                case InputConstants.TYPE_OPTION:
                    if (type != null) {
                        throw new ParseException(
                                "Duplicate option: " + option
                        );
                    }
                    type = value;
                    break;

                default:
                    throw new ParseException(
                            "Unknown option: " + option
                    );
            }
        }

        if (name == null) {
            throw new ParseException(
                    "Missing required option: " + InputConstants.NAME_OPTION
            );
        }

        if (price == null) {
            throw new ParseException(
                    "Missing required option: " + InputConstants.PRICE_OPTION
            );
        }
                                                                         // duplicate option
        if (quantity == null) {
            throw new ParseException(
                    "Missing required option: " + InputConstants.QUANTITY_OPTION
            );
        }

        if (type == null) {
            throw new ParseException(
                    "Missing required option: " + InputConstants.TYPE_OPTION
            );
        }

        BigDecimal parsedPrice;
        int parsedQuantity;
        ItemTypeEnum parsedType;

        try {
            parsedPrice = new BigDecimal(price);
            parsedQuantity = Integer.parseInt(quantity);

            parsedType = ItemTypeEnum.valueOf(type.toUpperCase());

        } catch (NumberFormatException exception) {

            throw new ParseException(
                    "Price and quantity must be valid numbers."
            );

        } catch (IllegalArgumentException exception) {

            throw new ParseException(
                    "Invalid item type. Allowed values are: raw, manufactured, imported."
            );
        }

        return new Item(name, parsedPrice, parsedQuantity, parsedType);
    }
}