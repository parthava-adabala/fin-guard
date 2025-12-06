package com.finguard.transaction.controller;

import com.finguard.transaction.dto.TransactionRequest;
import com.finguard.transaction.model.Transaction;
import com.finguard.transaction.service.TransactionService;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/transactions")
@CrossOrigin(origins = "http://localhost:4200")
public class TransactionController {

    private final TransactionService service;

    public TransactionController(TransactionService service) {
        this.service = service;
    }

    @PostMapping
    @RateLimiter(name = "transactionApi")
    public ResponseEntity<Transaction> createTransaction(@Valid @RequestBody TransactionRequest request) {
        Transaction transaction = service.initiateTransaction(request);
        return ResponseEntity.ok(transaction);
    }
}