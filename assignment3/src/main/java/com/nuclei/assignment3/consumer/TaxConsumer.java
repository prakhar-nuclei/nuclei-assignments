package com.nuclei.assignment3.consumer;

import com.nuclei.assignment3.entity.Item;
import com.nuclei.assignment3.entity.ItemResult;
import com.nuclei.assignment3.exception.ItemProcessingException;
import com.nuclei.assignment3.messaging.ItemMessage;
import com.nuclei.assignment3.service.TaxCalculatorService;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;
import java.util.concurrent.BlockingQueue;

@Component
public class TaxConsumer {

    private final BlockingQueue<ItemMessage> itemQueue;
    private final TaxCalculatorService taxCalculatorService;

    public TaxConsumer(
           final BlockingQueue<ItemMessage> itemQueue,
           final TaxCalculatorService taxCalculatorService) {
        this.itemQueue = itemQueue;
        this.taxCalculatorService = taxCalculatorService;
    }

    public void consume(List<ItemResult> results) {
        try {
            while (true) {
                ItemMessage message = itemQueue.take();

                if (message.poisonPill()) {    //poison pill check aur hum return
                    return;
                }

                Item item = message.item();
                BigDecimal tax = taxCalculatorService.calculateTax(item);
                BigDecimal finalPrice = item.getTotalPrice().add(tax);

                results.add(new ItemResult(item, tax, finalPrice));
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();

            throw new ItemProcessingException(
                    "Item consumer thread was interrupted",
                    e
            );
        }
    }

}
