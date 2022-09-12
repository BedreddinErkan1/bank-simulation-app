package com.sample.service;

import com.sample.expection.AccountOwnershipException;
import com.sample.expection.BadRequestException;
import com.sample.expection.BalanceNotSuffficientException;
import com.sample.model.Account;
import com.sample.model.Transaction;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public interface TransactionService {

    Transaction makeTransfer(Account sender, Account receiver, BigDecimal amount, Date creationDate, String message) throws BadRequestException, AccountOwnershipException, BalanceNotSuffficientException;

    List<Transaction> findAllTransaction();
}
