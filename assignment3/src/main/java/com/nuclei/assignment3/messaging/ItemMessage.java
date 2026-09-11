package com.nuclei.assignment3.messaging;

import com.nuclei.assignment3.entity.Item;

public record ItemMessage(Item item, boolean poisonPill) {

    public static ItemMessage item(Item item) {
        return new ItemMessage(item, false);
    }

    public static ItemMessage poisonPillMessage() {
        return new ItemMessage(null, true);
    }
}