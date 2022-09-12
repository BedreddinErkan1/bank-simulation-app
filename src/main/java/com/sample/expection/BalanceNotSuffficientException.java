package com.sample.expection;

public class BalanceNotSuffficientException extends Throwable {
    public BalanceNotSuffficientException(String message) {
        super(message);
    }
}
