package com.grpc.server.service;

import com.google.protobuf.Empty;
import com.grpc.server.GrpcServerRequest;
import com.grpc.server.GrpcServerResponse;

public interface GrpcServerService {

    GrpcServerResponse findGrpcServerNames(GrpcServerRequest request);
    GrpcServerResponse findGrpcServerObjects(Empty empty);
}
