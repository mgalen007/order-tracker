package com.galen.ordertracker.common.exception;

/** Thrown when an operation violates a business rule or a uniqueness constraint. */
public class ConflictException extends RuntimeException {

    public ConflictException(String message) {
        super(message);
    }
}
