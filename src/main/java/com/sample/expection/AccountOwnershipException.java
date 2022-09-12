package com.sample.expection;

public class AccountOwnershipException extends Throwable {
    public AccountOwnershipException(String message) {
        super(message);
    }
}
