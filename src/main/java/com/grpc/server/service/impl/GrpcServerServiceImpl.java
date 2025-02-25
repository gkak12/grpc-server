package com.grpc.server.service.impl;

import com.grpc.server.GrpcServerRequest;
import com.grpc.server.GrpcServerResponse;
import com.grpc.server.service.GrpcServerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Slf4j
@Service
public class GrpcServerServiceImpl implements GrpcServerService {

    @Override
    public GrpcServerResponse findGrpcServerDataList(GrpcServerRequest request) {
        List<String> list = Arrays.asList("obj1", "obj2", "obj3");

        return GrpcServerResponse
                .newBuilder()
                .setStatusCode(200)
                .setMessage("OK")
                .addAllDataList(list)
                .build();
    }
}
