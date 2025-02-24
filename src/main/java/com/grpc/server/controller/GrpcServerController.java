package com.grpc.server.controller;

import com.grpc.server.GrpcServerRequest;
import com.grpc.server.GrpcServerResponse;
import com.grpc.server.GrpcServerServiceGrpc;
import com.grpc.server.service.GrpcServerService;
import io.grpc.stub.StreamObserver;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.devh.boot.grpc.server.service.GrpcService;

@Slf4j
@GrpcService
@RequiredArgsConstructor
public class GrpcServerController extends GrpcServerServiceGrpc.GrpcServerServiceImplBase {

    private final GrpcServerService grpcServerService;

    @Override
    public void findGrpcServerDataList(GrpcServerRequest request, StreamObserver<GrpcServerResponse> responseObserver) {
        log.info("findGrpcServerDataList");

        responseObserver.onNext(grpcServerService.findGrpcServerDataList(request));
        responseObserver.onCompleted();
    }
}
