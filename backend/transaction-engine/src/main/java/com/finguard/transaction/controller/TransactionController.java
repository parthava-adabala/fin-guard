package com.finguard.transaction.controller;

import com.finguard.transaction.dto.TransactionRequest;
import com.finguard.transaction.model.Transaction;
import com.finguard.transaction.service.TransactionService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/transactions")
public class TransactionController {

    private final TransactionService service;

    public TransactionController(TransactionService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<Transaction> createTransaction(@Valid @RequestBody TransactionRequest request) {
        Transaction transaction = service.initiateTransaction(request);
        return ResponseEntity.ok(transaction);
    }
}