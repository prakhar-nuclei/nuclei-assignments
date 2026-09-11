package com.nuclei.assignment3.executor;

import com.nuclei.assignment3.exception.ItemProcessingException;
import com.nuclei.assignment3.producer.ItemProducer;
import org.springframework.stereotype.Component;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

@Component
public class ItemProcessingExecutor {

    private final ItemProducer itemProducer;

    public ItemProcessingExecutor( final ItemProducer itemProducer) {
        this.itemProducer = itemProducer;
    }

    public void execute() {

        ExecutorService executorService =
                Executors.newSingleThreadExecutor();

        try {
            Future<?> producerTask =
                    executorService.submit(itemProducer::produce);

            producerTask.get();

        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();

            throw new ItemProcessingException(
                    "Item producer execution was interrupted",
                    e
            );

        } catch (ExecutionException e) {

            throw new ItemProcessingException(
                    "Item producer execution failed",
                    e.getCause()
            );

        } finally {
            executorService.shutdown();
        }
    }
}
