package com.nuclei.assignment3.printer;

import com.nuclei.assignment3.entity.ItemResult;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ResultPrinter {

    public void print(List<ItemResult> results) {
        for (ItemResult result : results) {
            System.out.println("Item: " + result.getItem().getName());
            System.out.println("Tax: " + result.getTax());
            System.out.println("Final Price: " + result.getFinalPrice());
            System.out.println();
        }
    }
}
