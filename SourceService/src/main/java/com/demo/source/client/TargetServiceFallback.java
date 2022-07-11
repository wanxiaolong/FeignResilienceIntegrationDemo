package com.demo.source.client;

import org.springframework.stereotype.Component;

@Component
public class TargetServiceFallback implements TargetServiceClient {
    @Override
    public String hello(String name) {
        return "Fallback method: Hello " + name + "!";
    }
}
