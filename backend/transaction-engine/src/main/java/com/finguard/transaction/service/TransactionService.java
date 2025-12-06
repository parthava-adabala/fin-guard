package com.finguard.transaction.service;

import com.finguard.transaction.dto.TransactionRequest;
import com.finguard.transaction.event.TransactionEvent;
import com.finguard.transaction.model.Transaction;
import com.finguard.transaction.model.TransactionStatus;
import com.finguard.transaction.repository.TransactionRepository;
import com.finguard.proto.fraud.FraudCheckRequest;
import com.finguard.proto.fraud.FraudCheckResponse;
import com.finguard.proto.fraud.FraudServiceGrpc;
import net.devh.boot.grpc.client.inject.GrpcClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TransactionService {

    private static final Logger log = LoggerFactory.getLogger(TransactionService.class);
    private static final String TOPIC_NAME = "transaction-events";

    private final TransactionRepository repository;
    private final KafkaTemplate<String, Object> kafkaTemplate;
    private final FraudServiceGrpc.FraudServiceBlockingStub fraudStub;

    // Constructor Injection for EVERYTHING (Best Practice)
    public TransactionService(
            TransactionRepository repository,
            KafkaTemplate<String, Object> kafkaTemplate,
            @GrpcClient("fraud-service") FraudServiceGrpc.FraudServiceBlockingStub fraudStub) {
        this.repository = repository;
        this.kafkaTemplate = kafkaTemplate;
        this.fraudStub = fraudStub;
    }

    @Transactional
    public Transaction initiateTransaction(TransactionRequest request) {
        log.info("Processing transaction for account: {}", request.getAccountId());

        // 1. Call Fraud Sentinel (gRPC)
        FraudCheckResponse fraudResponse = fraudStub.analyzeTransaction(
                FraudCheckRequest.newBuilder()
                        .setAccountId(request.getAccountId())
                        .setAmount(request.getAmount().doubleValue())
                        .setCurrency(request.getCurrency())
                        .setTransactionId(request.getReferenceId() != null ? request.getReferenceId() : "UNKNOWN")
                        .build()
        );

        // 2. Determine Status
        TransactionStatus status = fraudResponse.getIsRejected() ? TransactionStatus.REJECTED : TransactionStatus.APPROVED;

        // 3. Save to DB
        Transaction transaction = new Transaction();
        transaction.setAccountId(request.getAccountId());
        transaction.setAmount(request.getAmount());
        transaction.setCurrency(request.getCurrency());
        transaction.setReferenceId(request.getReferenceId());
        transaction.setStatus(status);

        Transaction savedTransaction = repository.save(transaction);

        // 4. Publish to Kafka (Event-Driven) - ONLY IF APPROVED
        if (status == TransactionStatus.APPROVED) {
            TransactionEvent event = new TransactionEvent(
                    savedTransaction.getId().toString(),
                    savedTransaction.getAccountId(),
                    savedTransaction.getAmount(),
                    savedTransaction.getStatus().toString()
            );

            log.info("Publishing event to Kafka topic: {}", TOPIC_NAME);
            kafkaTemplate.send(TOPIC_NAME, savedTransaction.getId().toString(), event);
        }

        return savedTransaction;
    }
}