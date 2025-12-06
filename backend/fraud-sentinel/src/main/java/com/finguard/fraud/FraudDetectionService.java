package com.finguard.fraud;

import com.finguard.proto.fraud.FraudCheckRequest;
import com.finguard.proto.fraud.FraudCheckResponse;
import com.finguard.proto.fraud.FraudServiceGrpc;
import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@GrpcService
public class FraudDetectionService extends FraudServiceGrpc.FraudServiceImplBase {

    private static final Logger log = LoggerFactory.getLogger(FraudDetectionService.class);

    @Override
    public void analyzeTransaction(FraudCheckRequest request, StreamObserver<FraudCheckResponse> responseObserver) {
        log.info("Analyzing transaction: {} for amount: {}", request.getTransactionId(), request.getAmount());

        boolean isRejected = false;
        String reason = "Approved";

        // Simple Rule: Reject transactions over $10,000
        if (request.getAmount() > 10000) {
            isRejected = true;
            reason = "Amount exceeds limit";
        }

        // Send response back via gRPC (Protocol Buffers)
        FraudCheckResponse response = FraudCheckResponse.newBuilder()
                .setIsRejected(isRejected)
                .setReason(reason)
                .build();

        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }
}