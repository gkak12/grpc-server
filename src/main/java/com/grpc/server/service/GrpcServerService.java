package com.grpc.server.service;

import com.google.protobuf.Empty;
import com.grpc.server.DownloadFileChunk;
import com.grpc.server.GrpcServerRequest;
import com.grpc.server.GrpcServerResponse;
import com.grpc.server.UploadFileChunk;
import io.grpc.stub.StreamObserver;

public interface GrpcServerService {

    GrpcServerResponse findNameFromGrpcServer(GrpcServerRequest request);
    GrpcServerResponse findNamesFromGrpcServer(GrpcServerRequest request);
    GrpcServerResponse findObjectsFromGrpcServer(Empty empty);
    void uploadFileChunk(UploadFileChunk uploadFileChunk, StreamObserver<GrpcServerResponse> responseObserver);
    void completeFileChunk(StreamObserver<GrpcServerResponse> responseObserver);
    void downloadFileFromGrpcServer(GrpcServerRequest request, StreamObserver<DownloadFileChunk> responseObserver);
}
