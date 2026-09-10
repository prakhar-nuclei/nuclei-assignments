package com.nuclei.assignment3.validation;

import com.nuclei.assignment3.entity.Item;
import com.nuclei.assignment3.exception.ValidationException;

import java.math.BigDecimal;

public class ItemValidation {

    public void validate(Item item) {

        if (item.getName() == null || item.getName().isBlank()) {
            throw new ValidationException(
                    "Item name cannot be blank."
            );
        }

        if (item.getPrice() == null
                || item.getPrice().compareTo(BigDecimal.ZERO) <= 0) {
            // rice check
            throw new ValidationException(
                    "Item price must be greater than zero."
            );
        }

        if (item.getQuantity() <= 0) {
            throw new ValidationException(
                    "Item quantity must be greater than zero."
            );
        }

    }
}
