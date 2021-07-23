package com;

import org.springframework.stereotype.Service;

import com.grpctest.HelloServiceGrpc;
import com.grpctest.HelloServiceGrpc.HelloServiceStub;
import com.grpctest.HelloWorldRequest;
import com.grpctest.HelloWorldResponse;

import io.grpc.ManagedChannelBuilder;
import io.grpc.stub.StreamObserver;

@Service
public class GrpcClient {
	//스텁생성
	 private static final int PORT = 3030;
	    public static final String HOST = "localhost";
	    private final HelloServiceStub asyncStub = HelloServiceGrpc.newStub(
	            ManagedChannelBuilder.forAddress(HOST, PORT)
	            .usePlaintext()
	            .build()
	    );
	 
	    public String helloWorldPrint() {
	        final HelloWorldRequest helloWorldRequest = HelloWorldRequest.newBuilder()
	        		.setMsg("really really")
	                .build();
	 
	        asyncStub.helloWorldPrint(helloWorldRequest, new StreamObserver<HelloWorldResponse>() {
	            @Override
	            public void onNext(HelloWorldResponse value) {
	                System.out.println("GrpcClient#sampleCall - {}"+ value);
	            }
	 
	            @Override
	            public void onError(Throwable t) {
	            	System.out.println("GrpcClient#sampleCall - onError");
	            }
	 
	            @Override
	            public void onCompleted() {
	            	System.out.println("GrpcClient#sampleCall - onCompleted");
	            }
	        });
	        return "string";
	    }
	}




