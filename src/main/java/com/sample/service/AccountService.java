package com.sample.service;

import com.sample.enums.AccountType;
import com.sample.model.Account;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public interface AccountService {

    Account createNewAccount(BigDecimal balance, Date creationDate, AccountType accountType, Long userId);

    List<Account> listAllAccount();

}
