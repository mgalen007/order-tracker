package com.galen.ordertracker.common.exception;

import java.util.UUID;

public class ResourceNotFoundException extends RuntimeException {

    public ResourceNotFoundException(String message) {
        super(message);
    }

    public static ResourceNotFoundException of(String resource, UUID id) {
        return new ResourceNotFoundException(resource + " with ID " + id + " not found");
    }

    public static ResourceNotFoundException of(String resource, String field, String value) {
        return new ResourceNotFoundException(resource + " with " + field + " " + value + " not found");
    }
}
