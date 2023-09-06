package com.example.portfolio.controller.support;

import java.text.MessageFormat;

public class ResourceNotFoundException extends RuntimeException {

    public ResourceNotFoundException() {
    }

    public ResourceNotFoundException(String message) {
        super(message);
    }

    public ResourceNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public ResourceNotFoundException(Throwable cause) {
        super(cause);
    }

    public ResourceNotFoundException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    public ResourceNotFoundException(Class clazz, String identifier) {
        super(MessageFormat.format("Resource not found for given identifier. [Resource: {0}, Identifier: {1}]", clazz.getSimpleName(), identifier));
    }
}
