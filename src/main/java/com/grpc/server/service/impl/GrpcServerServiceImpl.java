package com.grpc.server.service.impl;

import com.google.protobuf.ByteString;
import com.google.protobuf.Empty;
import com.grpc.server.DownloadFileChunk;
import com.grpc.server.GrpcServerRequest;
import com.grpc.server.GrpcServerResponse;
import com.grpc.server.UploadFileChunk;
import com.grpc.server.domain.GrpcServerObject;
import com.grpc.server.mapper.GrpcMapper;
import com.grpc.server.service.GrpcServerService;
import io.grpc.Status;
import io.grpc.stub.StreamObserver;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
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

    @Override
    public StreamObserver<UploadFileChunk> uploadFileToGrpcServer(StreamObserver<GrpcServerResponse> responseObserver) {
        return new StreamObserver<>() {
            private OutputStream out;
            private String fileName;

            @Override
            public void onNext(UploadFileChunk uploadFileChunk) {
                try {
                    if (out == null) {
                        fileName = uploadFileChunk.getFileName();
                        Path path = Paths.get("D:\\tmp", fileName); // 임시 하드코딩
                        Files.createDirectories(path.getParent());
                        out = Files.newOutputStream(path);
                    }

                    out.write(uploadFileChunk.getData().toByteArray());
                } catch (IOException e) {
                    responseObserver.onError(
                        Status.INTERNAL.withDescription(e.getMessage()).asRuntimeException()
                    );
                }
            }

            @Override
            public void onError(Throwable throwable) {
                log.info("grpc-server | gRPC stream error: " + throwable.getMessage());
            }

            @Override
            public void onCompleted() {
                try {
                    if (out != null){
                        out.close();
                    }

                    responseObserver.onNext(GrpcServerResponse.newBuilder()
                        .setMessage("Upload successful")
                        .build()
                    );

                    responseObserver.onCompleted();
                } catch (IOException e) {
                    responseObserver.onError(
                        Status.INTERNAL.withDescription(e.getMessage()).asRuntimeException()
                    );
                }
            }
        };
    }

    @Override
    public void downloadFileFromGrpcServer(GrpcServerRequest request, StreamObserver<DownloadFileChunk> responseObserver) {
        try (FileInputStream fis = new FileInputStream("D:\\tmp\\tmp.txt")) { // 임시 하드코딩
            byte[] buffer = new byte[4096];
            int bytesRead;

            while ((bytesRead = fis.read(buffer)) != -1) {
                DownloadFileChunk chunk = DownloadFileChunk.newBuilder()
                    .setData(ByteString.copyFrom(buffer, 0, bytesRead))
                    .build();
                responseObserver.onNext(chunk);
            }
            responseObserver.onCompleted();
        } catch (IOException e) {
            responseObserver.onError(e);
        }
    }
}
