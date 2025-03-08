package com.grpc.server.mapper;

import com.grpc.server.GrpcObject;
import com.grpc.server.domain.GrpcServerObject;
import org.mapstruct.*;

@Mapper(
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        collectionMappingStrategy = CollectionMappingStrategy.TARGET_IMMUTABLE,
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        componentModel = "spring"
)
public interface GrpcMapper {
    GrpcObject toGrpcObject(GrpcServerObject grpcServerObject);
}
