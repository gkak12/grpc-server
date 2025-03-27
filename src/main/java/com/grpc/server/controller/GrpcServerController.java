package com.grpc.server.controller;

import com.google.protobuf.Empty;
import com.grpc.server.FileChunk;
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
    public void findNameFromGrpcServer(GrpcServerRequest request, StreamObserver<GrpcServerResponse> responseObserver) {
        log.info("grpc-server | GrpcServerController findGrpcServerName is called.");

        responseObserver.onNext(grpcServerService.findNameFromGrpcServer(request));
        responseObserver.onCompleted();
    }

    @Override
    public void findNamesFromGrpcServer(GrpcServerRequest request, StreamObserver<GrpcServerResponse> responseObserver) {
        log.info("grpc-server | GrpcServerController findGrpcServerNames is called.");

        responseObserver.onNext(grpcServerService.findNamesFromGrpcServer(request));
        responseObserver.onCompleted();
    }

    @Override
    public void findObjectsFromGrpcServer(Empty empty, StreamObserver<GrpcServerResponse> responseObserver) {
        log.info("grpc-server | GrpcServerController findGrpcServerObjects is called.");

        responseObserver.onNext(grpcServerService.findObjectsFromGrpcServer(empty));
        responseObserver.onCompleted();
    }

    @Override
    public void downloadFileFromGrpcServer(GrpcServerRequest request, StreamObserver<FileChunk> responseObserver) {
        log.info("grpc-server | GrpcServerController downloadFileFromGrpcServer is called.");

        grpcServerService.downloadFileFromGrpcServer(request, responseObserver);
    }
}
