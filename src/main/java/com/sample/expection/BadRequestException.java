package com.sample.expection;

public class BadRequestException extends Throwable {
    public BadRequestException(String message) {
        super(message);
    }
}
