package com.nuclei.assignment3.exception;

public class ItemProcessingException extends RuntimeException {

    public ItemProcessingException(String message) {
        super(message);
    }

    public ItemProcessingException(String message, Throwable cause) {
        super(message, cause);
    }
}

