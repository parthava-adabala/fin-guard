package com.finguard.transaction.event;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class TransactionEvent {
    private String transactionId;
    private String accountId;
    private BigDecimal amount;
    private String status;
    private String timestamp;

    public TransactionEvent() {}

    public TransactionEvent(String transactionId, String accountId, BigDecimal amount, String status) {
        this.transactionId = transactionId;
        this.accountId = accountId;
        this.amount = amount;
        this.status = status;
        this.timestamp = LocalDateTime.now().toString();
    }

    public String getTransactionId() { return transactionId; }
    public void setTransactionId(String transactionId) { this.transactionId = transactionId; }

    public String getAccountId() { return accountId; }
    public void setAccountId(String accountId) { this.accountId = accountId; }

    public BigDecimal getAmount() { return amount; }
    public void setAmount(BigDecimal amount) { this.amount = amount; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public String getTimestamp() { return timestamp; }
    public void setTimestamp(String timestamp) { this.timestamp = timestamp; }
}