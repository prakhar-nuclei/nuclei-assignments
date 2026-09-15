package com.nuclei.assignment3.executor;

import com.nuclei.assignment3.consumer.TaxConsumer;
import com.nuclei.assignment3.entity.ItemResult;
import com.nuclei.assignment3.exception.ItemProcessingException;
import com.nuclei.assignment3.producer.ItemProducer;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

@Component
public class ItemProcessingExecutor {

    private final ItemProducer itemProducer;
    private final TaxConsumer taxConsumer;

    public ItemProcessingExecutor(
            final ItemProducer itemProducer,
            final TaxConsumer taxConsumer) {
        this.itemProducer = itemProducer;
        this.taxConsumer = taxConsumer;
    }

    public List<ItemResult> execute() {
        ExecutorService executorService =
                Executors.newFixedThreadPool(2);

        List<ItemResult> results = new ArrayList<>();

        CompletionService<Boolean> completionService =    //list hai sabke future hai
                                                          // track kon kab complete hua hai
                new ExecutorCompletionService<>(executorService);

        Future<Boolean> producerTask =             //handle hai status check ke liye
                completionService.submit(()->{ itemProducer.produce();
                                return true;
        });

        Future<Boolean> consumerTask =
                completionService.submit(() -> {
                    taxConsumer.consume(results);
                    return true;
    });

        try {
            Future<Boolean> completedTask = completionService.take();
            completedTask.get();

            completedTask = completionService.take();
            completedTask.get();

            return results;

        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();

            producerTask.cancel(true);
            consumerTask.cancel(true);

            throw new ItemProcessingException(
                    "Item processing execution was interrupted",
                    e
            );

        } catch (ExecutionException e) {
            producerTask.cancel(true);
            consumerTask.cancel(true);

            throw new ItemProcessingException(
                    "Item processing execution failed",
                    e.getCause()
            );

        } finally {
            executorService.shutdown();
        }
    }
}