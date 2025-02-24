package com.grpc.server.service;

import com.grpc.server.GrpcServerRequest;
import com.grpc.server.GrpcServerResponse;

public interface GrpcServerService {

    GrpcServerResponse findGrpcServerDataList(GrpcServerRequest request);
}
