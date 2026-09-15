package com.nuclei.assignment3;

import com.nuclei.assignment3.executor.ItemProcessingExecutor;
import com.nuclei.assignment3.printer.ResultPrinter;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Assignment3Application {

    public static void main(String[] args) {
        SpringApplication.run(Assignment3Application.class, args);
    }

    @Bean
    CommandLineRunner run (
            ItemProcessingExecutor executor ,
             ResultPrinter printer
    ){

        return args -> printer.print(executor.execute());
    }

}
