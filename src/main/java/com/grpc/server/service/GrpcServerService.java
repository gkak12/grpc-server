package com.grpc.server.service;

import com.google.protobuf.Empty;
import com.grpc.server.GrpcServerRequest;
import com.grpc.server.GrpcServerResponse;

public interface GrpcServerService {

    GrpcServerResponse findNameFromGrpcServer(GrpcServerRequest request);
    GrpcServerResponse findNamesFromGrpcServer(GrpcServerRequest request);
    GrpcServerResponse findObjectsFromGrpcServer(Empty empty);
}
