package com.nuclei.assignment3.producer;

import com.nuclei.assignment3.entity.Item;
import com.nuclei.assignment3.exception.ItemProcessingException;
import com.nuclei.assignment3.repo.ItemRepository;
import org.springframework.stereotype.Component;

import java.sql.SQLException;
import java.util.concurrent.BlockingQueue;

@Component
public class ItemProducer {

    private final ItemRepository itemRepository;
    private final BlockingQueue<Item> itemQueue;

    public ItemProducer(
            final ItemRepository itemRepository,
            final BlockingQueue<Item> itemQueue) {
        this.itemRepository = itemRepository;
        this.itemQueue = itemQueue;
    }

    public void produce() {

        try {
            itemRepository.readItems(item -> {
                try {
                    itemQueue.put(item);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();

                    throw new ItemProcessingException(
                            "Item producer thread was interrupted",
                            e
                    );
                }
            });

        } catch (SQLException e) {
            throw new ItemProcessingException(
                    "Failed to read items from database",
                    e
            );
        }
    }
}
