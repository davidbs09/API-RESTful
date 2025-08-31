package com.david.service;

import com.david.domain.model.Transaction;

import java.math.BigDecimal;
import java.util.List;

public interface TransactionService {
    Transaction deposit(Long accountId, BigDecimal amount, String description);
    Transaction withdraw(Long accountId, BigDecimal amount, String description);
    Transaction transfer(Long fromAccountId, Long toAccountId, BigDecimal amount, String description);
    List<Transaction> getStatement(Long accountId);
}
