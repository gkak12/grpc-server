package com.grpc.server.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GrpcServerObject {

    private Long seq;
    private String name;
    private boolean flag;
}
