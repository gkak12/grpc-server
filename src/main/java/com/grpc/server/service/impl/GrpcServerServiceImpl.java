package com.grpc.server.service.impl;

import com.google.protobuf.Empty;
import com.grpc.server.GrpcServerRequest;
import com.grpc.server.GrpcServerResponse;
import com.grpc.server.domain.GrpcServerObject;
import com.grpc.server.mapper.GrpcMapper;
import com.grpc.server.service.GrpcServerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class GrpcServerServiceImpl implements GrpcServerService {

    private final GrpcMapper grpcMapper;

    @Override
    public GrpcServerResponse findNameFromGrpcServer(GrpcServerRequest request) {
        log.info("grpc-server | findGrpcServerName request: {}", request);

        return GrpcServerResponse
                .newBuilder()
                .setStatusCode(200)
                .setMessage("OK")
                .setName("obj1")
                .build();
    }

    @Override
    public GrpcServerResponse findNamesFromGrpcServer(GrpcServerRequest request) {
        log.info("grpc-server | findGrpcServerNames request: {}", request);

        List<String> names = Arrays.asList("obj1", "obj2", "obj3"); // 임시 하드코딩

        return GrpcServerResponse
                .newBuilder()
                .setStatusCode(200)
                .setMessage("OK")
                .addAllNames(names)
                .build();
    }

    @Override
    public GrpcServerResponse findObjectsFromGrpcServer(Empty empty) {
        log.info("grpc-server | findGrpcServerObjects request: empty");

        List<GrpcServerObject> objectList = Arrays.asList(  // 임시 하드코딩
            GrpcServerObject.builder().seq(1L).name("obj1").flag(true).build(),
            GrpcServerObject.builder().seq(2L).name("obj2").flag(false).build(),
            GrpcServerObject.builder().seq(3L).name("obj3").flag(true).build()
        );

        return GrpcServerResponse
                .newBuilder()
                .setStatusCode(200)
                .setMessage("OK")
                .addAllObjects(objectList.stream()
                    .map(grpcMapper::toGrpcObject)
                    .toList()
                )
                .build();
    }
}
