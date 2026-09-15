package com.nuclei.assignment3.config;

import com.nuclei.assignment3.messaging.ItemMessage;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

@Configuration
public class ProcessingConfig {

    private static final int QUEUE_CAPACITY = 100;

    @Bean
    public BlockingQueue<ItemMessage> itemQueue() {
        return new ArrayBlockingQueue<>(QUEUE_CAPACITY);
    }
}
