package com.grpc.server.service;

import com.google.protobuf.Empty;
import com.grpc.server.FileChunk;
import com.grpc.server.GrpcServerRequest;
import com.grpc.server.GrpcServerResponse;
import io.grpc.stub.StreamObserver;

public interface GrpcServerService {

    GrpcServerResponse findNameFromGrpcServer(GrpcServerRequest request);
    GrpcServerResponse findNamesFromGrpcServer(GrpcServerRequest request);
    GrpcServerResponse findObjectsFromGrpcServer(Empty empty);
    void downloadFileFromGrpcServer(GrpcServerRequest request, StreamObserver<FileChunk> responseObserver);
}
