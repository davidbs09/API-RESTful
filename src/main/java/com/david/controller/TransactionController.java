package com.david.controller;

import com.david.domain.model.Transaction;
import com.david.service.TransactionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/transactions")
public class TransactionController {

    private final TransactionService transactionService;

    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @PostMapping("/deposit")
    public ResponseEntity<Transaction> deposit(@RequestParam Long accountId,
                                               @RequestParam BigDecimal amount,
                                               @RequestParam(required = false) String description) {
        var tx = transactionService.deposit(accountId, amount, description);
        return ResponseEntity.ok(tx);
    }

    @PostMapping("/withdraw")
    public ResponseEntity<Transaction> withdraw(@RequestParam Long accountId,
                                                @RequestParam BigDecimal amount,
                                                @RequestParam(required = false) String description) {
        var tx = transactionService.withdraw(accountId, amount, description);
        return ResponseEntity.ok(tx);
    }

    @PostMapping("/transfer")
    public ResponseEntity<Transaction> transfer(@RequestParam Long fromAccountId,
                                                @RequestParam Long toAccountId,
                                                @RequestParam BigDecimal amount,
                                                @RequestParam(required = false) String description) {
        var tx = transactionService.transfer(fromAccountId, toAccountId, amount, description);
        return ResponseEntity.ok(tx);
    }

    @GetMapping("/statement/{accountId}")
    public ResponseEntity<List<Transaction>> getStatement(@PathVariable Long accountId) {
        var list = transactionService.getStatement(accountId);
        return ResponseEntity.ok(list);
    }
}
