package org.lognet.springboot.grpc;

import io.grpc.Metadata;
import io.grpc.ServerCall;
import io.grpc.ServerInterceptor;
import io.grpc.Status;
import io.grpc.StatusRuntimeException;

public interface FailureHandlingServerInterceptor extends ServerInterceptor {
    default  void closeCall(Object o, GRpcErrorHandler errorHandler, ServerCall<?, ?> call, Metadata headers, final Status status, Exception exception){

        final Metadata responseHeaders = new Metadata();
        Status statusToSend = errorHandler.handle(o,status, exception, headers, responseHeaders);
        call.close(statusToSend, responseHeaders);

    }
}
