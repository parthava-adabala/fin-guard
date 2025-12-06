package com.finguard.transaction.service;

import com.finguard.transaction.dto.TransactionRequest;
import com.finguard.transaction.model.Transaction;
import com.finguard.transaction.model.TransactionStatus;
import com.finguard.transaction.repository.TransactionRepository;
import com.finguard.proto.fraud.FraudCheckResponse;
import com.finguard.proto.fraud.FraudServiceGrpc;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.kafka.core.KafkaTemplate;

import java.math.BigDecimal;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class TransactionServiceTest {

    @Mock
    private TransactionRepository repository;

    @Mock
    private KafkaTemplate<String, Object> kafkaTemplate;

    @Mock
    private FraudServiceGrpc.FraudServiceBlockingStub fraudStub;

    @InjectMocks
    private TransactionService transactionService;

    @Test
    void shouldApproveValidTransaction() {
        // 1. Arrange (Mock Data)
        TransactionRequest request = new TransactionRequest();
        request.setAccountId("ACC-TEST");
        request.setAmount(new BigDecimal("500.00"));
        request.setCurrency("USD");
        request.setReferenceId("REF-123");

        // Mock gRPC Response (Approved)
        FraudCheckResponse grpcResponse = FraudCheckResponse.newBuilder()
                .setIsRejected(false)
                .build();
        when(fraudStub.analyzeTransaction(any())).thenReturn(grpcResponse);

        // Mock DB Save
        Transaction savedTxn = new Transaction();
        savedTxn.setId(UUID.randomUUID());
        savedTxn.setStatus(TransactionStatus.APPROVED);
        when(repository.save(any())).thenReturn(savedTxn);

        // 2. Act
        Transaction result = transactionService.initiateTransaction(request);

        // 3. Assert
        assertEquals(TransactionStatus.APPROVED, result.getStatus());

        // Verify Kafka was called (Event Driven check)
        verify(kafkaTemplate, times(1)).send(anyString(), anyString(), any());
    }

    @Test
    void shouldRejectFraudTransaction() {
        // 1. Arrange
        TransactionRequest request = new TransactionRequest();
        request.setAccountId("ACC-FRAUD");
        request.setAmount(new BigDecimal("15000.00"));
        request.setCurrency("USD");

        // Mock gRPC Response (Rejected)
        FraudCheckResponse grpcResponse = FraudCheckResponse.newBuilder()
                .setIsRejected(true)
                .setReason("Amount too high")
                .build();
        when(fraudStub.analyzeTransaction(any())).thenReturn(grpcResponse);

        Transaction savedTxn = new Transaction();
        savedTxn.setId(UUID.randomUUID());
        savedTxn.setStatus(TransactionStatus.REJECTED);
        when(repository.save(any())).thenReturn(savedTxn);

        // 2. Act
        Transaction result = transactionService.initiateTransaction(request);

        // 3. Assert
        assertEquals(TransactionStatus.REJECTED, result.getStatus());

        // Verify Kafka was NOT called (we only stream approved events)
        verify(kafkaTemplate, never()).send(anyString(), anyString(), any());
    }
}