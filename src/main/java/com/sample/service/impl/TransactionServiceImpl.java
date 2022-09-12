package com.sample.service.impl;

import com.sample.enums.AccountType;
import com.sample.expection.AccountOwnershipException;
import com.sample.expection.BadRequestException;
import com.sample.expection.BalanceNotSuffficientException;
import com.sample.model.Account;
import com.sample.model.Transaction;
import com.sample.repository.AccountRepository;
import com.sample.service.TransactionService;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.UUID;
@Component
public class TransactionServiceImpl implements TransactionService {

    AccountRepository accountRepository;

    public TransactionServiceImpl(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    public Transaction makeTransfer(Account sender, Account receiver, BigDecimal amount, Date creationDate, String message) throws BadRequestException, AccountOwnershipException, BalanceNotSuffficientException {

        validateAccount(sender,receiver);
        checkAccountOwnership(sender,receiver);
        executeBalanceAndUpdateIfRequired(amount,sender,receiver);
        /*
        after all validations completed, and money transfered, we need to create Transaction object and save/return it
         */
        //please create needed classes / methods for this steps, save the transactions
        return null;
    }

    private void executeBalanceAndUpdateIfRequired(BigDecimal amount, Account sender, Account receiver) throws BalanceNotSuffficientException {
        if (checkSenderBalance(sender,amount)){
            //make transaction
            sender.setBalance(sender.getBalance().subtract(amount));
            receiver.setBalance(receiver.getBalance().add(amount));
        }else {
            //not enough balance
            throw new BalanceNotSuffficientException("Balance is not enough for this transfer");
        }
    }

    private boolean checkSenderBalance(Account sender, BigDecimal amount) {
        //verify that sender has enough balance
        return sender.getBalance().subtract(amount).compareTo(BigDecimal.ZERO) >= 0;

    }

    private void checkAccountOwnership(Account sender, Account receiver) throws AccountOwnershipException {

        /*
        write a if statement that checks if one of the account is saving,
        and user if of sender or receiver is not the same, throw AccountOwnershipException
         */

        if ((sender.getAccountType().equals(AccountType.SAVING)||receiver.getAccountType().equals(AccountType.SAVING))&& !sender.getUserId().equals(receiver.getUserId()))
        {
            throw new AccountOwnershipException("One of the accounts is Savings" + "Transactions between savings and checking account are allowed between same user accounts only." + "User Id's dont match"
            );
        }
    }

    private void validateAccount(Account sender, Account receiver) throws BadRequestException {
        /*
        * -if any of the account is null
        * -if account ids are the same(same account)
        * -if the account exist in the database
        */

        if (sender==null || receiver==null){
            throw new BadRequestException("Sender or Receiver can not be null");
        }

        if (sender.getId().equals(receiver.getId())){
            throw new BadRequestException("Sender account must be different then Receiver");
        }

        findAccountById(sender.getId());
        findAccountById(receiver.getId());

    }

    private Account findAccountById(UUID id) {
        return accountRepository.findById(id);
    }

    @Override
    public List<Transaction> findAllTransaction() {
        return null;
    }
}
