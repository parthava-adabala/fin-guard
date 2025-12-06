package com.finguard.analytics;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class AnalyticsConsumer {

    private static final Logger log = LoggerFactory.getLogger(AnalyticsConsumer.class);

    @KafkaListener(topics = "transaction-events", groupId = "analytics-group")
    public void consumeTransactionEvent(Object event) {
        log.info("Analyitcs Service received Kafka Event: {}", event);
        log.info(">> Updating Real-time Dashboard...");
    }
}