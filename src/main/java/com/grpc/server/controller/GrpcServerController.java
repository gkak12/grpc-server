package com.grpc.server.controller;

import com.google.protobuf.Empty;
import com.grpc.server.*;
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
    public StreamObserver<UploadFileChunk> uploadFileToGrpcServer(StreamObserver<GrpcServerResponse> responseObserver) {
        log.info("grpc-server | GrpcServerController uploadFileToGrpcServer is called.");

        return new StreamObserver<UploadFileChunk>() {
            @Override
            public void onNext(UploadFileChunk uploadFileChunk) {
                grpcServerService.uploadFileChunk(uploadFileChunk, responseObserver);
            }

            @Override
            public void onCompleted() {
                grpcServerService.completeFileChunk(responseObserver);
            }

            @Override
            public void onError(Throwable throwable) {
                log.info("grpc-server | gRPC stream error: " + throwable.getMessage());
            }
        };
    }

    @Override
    public void downloadFileFromGrpcServer(GrpcServerRequest request, StreamObserver<DownloadFileChunk> responseObserver) {
        log.info("grpc-server | GrpcServerController downloadFileFromGrpcServer is called.");

        grpcServerService.downloadFileFromGrpcServer(request, responseObserver);
    }
}
