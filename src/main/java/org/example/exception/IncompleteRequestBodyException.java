package org.example.exception;

public class IncompleteRequestBodyException extends RuntimeException{
    public IncompleteRequestBodyException(String message) {
        super(message);
    }
}
