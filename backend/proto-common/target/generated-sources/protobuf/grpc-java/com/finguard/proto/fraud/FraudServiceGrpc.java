package com.finguard.proto.fraud;

import static io.grpc.MethodDescriptor.generateFullMethodName;

/**
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.58.0)",
    comments = "Source: fraud_check.proto")
@io.grpc.stub.annotations.GrpcGenerated
public final class FraudServiceGrpc {

  private FraudServiceGrpc() {}

  public static final java.lang.String SERVICE_NAME = "com.finguard.proto.fraud.FraudService";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<com.finguard.proto.fraud.FraudCheckRequest,
      com.finguard.proto.fraud.FraudCheckResponse> getAnalyzeTransactionMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "AnalyzeTransaction",
      requestType = com.finguard.proto.fraud.FraudCheckRequest.class,
      responseType = com.finguard.proto.fraud.FraudCheckResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.finguard.proto.fraud.FraudCheckRequest,
      com.finguard.proto.fraud.FraudCheckResponse> getAnalyzeTransactionMethod() {
    io.grpc.MethodDescriptor<com.finguard.proto.fraud.FraudCheckRequest, com.finguard.proto.fraud.FraudCheckResponse> getAnalyzeTransactionMethod;
    if ((getAnalyzeTransactionMethod = FraudServiceGrpc.getAnalyzeTransactionMethod) == null) {
      synchronized (FraudServiceGrpc.class) {
        if ((getAnalyzeTransactionMethod = FraudServiceGrpc.getAnalyzeTransactionMethod) == null) {
          FraudServiceGrpc.getAnalyzeTransactionMethod = getAnalyzeTransactionMethod =
              io.grpc.MethodDescriptor.<com.finguard.proto.fraud.FraudCheckRequest, com.finguard.proto.fraud.FraudCheckResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "AnalyzeTransaction"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.finguard.proto.fraud.FraudCheckRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.finguard.proto.fraud.FraudCheckResponse.getDefaultInstance()))
              .setSchemaDescriptor(new FraudServiceMethodDescriptorSupplier("AnalyzeTransaction"))
              .build();
        }
      }
    }
    return getAnalyzeTransactionMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static FraudServiceStub newStub(io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<FraudServiceStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<FraudServiceStub>() {
        @java.lang.Override
        public FraudServiceStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new FraudServiceStub(channel, callOptions);
        }
      };
    return FraudServiceStub.newStub(factory, channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static FraudServiceBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<FraudServiceBlockingStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<FraudServiceBlockingStub>() {
        @java.lang.Override
        public FraudServiceBlockingStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new FraudServiceBlockingStub(channel, callOptions);
        }
      };
    return FraudServiceBlockingStub.newStub(factory, channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static FraudServiceFutureStub newFutureStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<FraudServiceFutureStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<FraudServiceFutureStub>() {
        @java.lang.Override
        public FraudServiceFutureStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new FraudServiceFutureStub(channel, callOptions);
        }
      };
    return FraudServiceFutureStub.newStub(factory, channel);
  }

  /**
   */
  public interface AsyncService {

    /**
     */
    default void analyzeTransaction(com.finguard.proto.fraud.FraudCheckRequest request,
        io.grpc.stub.StreamObserver<com.finguard.proto.fraud.FraudCheckResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getAnalyzeTransactionMethod(), responseObserver);
    }
  }

  /**
   * Base class for the server implementation of the service FraudService.
   */
  public static abstract class FraudServiceImplBase
      implements io.grpc.BindableService, AsyncService {

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return FraudServiceGrpc.bindService(this);
    }
  }

  /**
   * A stub to allow clients to do asynchronous rpc calls to service FraudService.
   */
  public static final class FraudServiceStub
      extends io.grpc.stub.AbstractAsyncStub<FraudServiceStub> {
    private FraudServiceStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected FraudServiceStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new FraudServiceStub(channel, callOptions);
    }

    /**
     */
    public void analyzeTransaction(com.finguard.proto.fraud.FraudCheckRequest request,
        io.grpc.stub.StreamObserver<com.finguard.proto.fraud.FraudCheckResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getAnalyzeTransactionMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   * A stub to allow clients to do synchronous rpc calls to service FraudService.
   */
  public static final class FraudServiceBlockingStub
      extends io.grpc.stub.AbstractBlockingStub<FraudServiceBlockingStub> {
    private FraudServiceBlockingStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected FraudServiceBlockingStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new FraudServiceBlockingStub(channel, callOptions);
    }

    /**
     */
    public com.finguard.proto.fraud.FraudCheckResponse analyzeTransaction(com.finguard.proto.fraud.FraudCheckRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getAnalyzeTransactionMethod(), getCallOptions(), request);
    }
  }

  /**
   * A stub to allow clients to do ListenableFuture-style rpc calls to service FraudService.
   */
  public static final class FraudServiceFutureStub
      extends io.grpc.stub.AbstractFutureStub<FraudServiceFutureStub> {
    private FraudServiceFutureStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected FraudServiceFutureStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new FraudServiceFutureStub(channel, callOptions);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.finguard.proto.fraud.FraudCheckResponse> analyzeTransaction(
        com.finguard.proto.fraud.FraudCheckRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getAnalyzeTransactionMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_ANALYZE_TRANSACTION = 0;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final AsyncService serviceImpl;
    private final int methodId;

    MethodHandlers(AsyncService serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_ANALYZE_TRANSACTION:
          serviceImpl.analyzeTransaction((com.finguard.proto.fraud.FraudCheckRequest) request,
              (io.grpc.stub.StreamObserver<com.finguard.proto.fraud.FraudCheckResponse>) responseObserver);
          break;
        default:
          throw new AssertionError();
      }
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public io.grpc.stub.StreamObserver<Req> invoke(
        io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        default:
          throw new AssertionError();
      }
    }
  }

  public static final io.grpc.ServerServiceDefinition bindService(AsyncService service) {
    return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
        .addMethod(
          getAnalyzeTransactionMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.finguard.proto.fraud.FraudCheckRequest,
              com.finguard.proto.fraud.FraudCheckResponse>(
                service, METHODID_ANALYZE_TRANSACTION)))
        .build();
  }

  private static abstract class FraudServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    FraudServiceBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return com.finguard.proto.fraud.FraudCheckProto.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("FraudService");
    }
  }

  private static final class FraudServiceFileDescriptorSupplier
      extends FraudServiceBaseDescriptorSupplier {
    FraudServiceFileDescriptorSupplier() {}
  }

  private static final class FraudServiceMethodDescriptorSupplier
      extends FraudServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final java.lang.String methodName;

    FraudServiceMethodDescriptorSupplier(java.lang.String methodName) {
      this.methodName = methodName;
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.MethodDescriptor getMethodDescriptor() {
      return getServiceDescriptor().findMethodByName(methodName);
    }
  }

  private static volatile io.grpc.ServiceDescriptor serviceDescriptor;

  public static io.grpc.ServiceDescriptor getServiceDescriptor() {
    io.grpc.ServiceDescriptor result = serviceDescriptor;
    if (result == null) {
      synchronized (FraudServiceGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new FraudServiceFileDescriptorSupplier())
              .addMethod(getAnalyzeTransactionMethod())
              .build();
        }
      }
    }
    return result;
  }
}
