# FinGuard: High-Frequency Transaction & Fraud Detection Platform

**FinGuard** is an enterprise-grade financial transaction processing system designed for **high throughput** and **low latency**. It simulates a core banking ledger capable of handling thousands of concurrent requests using **Java 21 Virtual Threads**, vetting transactions via **gRPC** for sub-millisecond fraud detection, and broadcasting state changes via **Apache Kafka** for real-time analytics.

### 🚀 Architecture Overview

The system follows a **Domain-Driven Design (DDD)** approach within a **Microservices Architecture**:

```mermaid
graph LR
    User[Client / Mobile App] -- HTTPS/REST --> Transaction[Transaction Engine]
    
    subgraph "Synchronous (Low Latency)"
    Transaction -- gRPC (Protobuf) --> Fraud[Fraud Sentinel]
    end
    
    subgraph "Asynchronous (Eventual Consistency)"
    Transaction -- Kafka Topic: transaction-events --> Analytics[Analytics Service]
    end
    
    Transaction -- JDBC --> DB[(PostgreSQL)]
    Transaction -- Cache --> Redis[(Redis)]
