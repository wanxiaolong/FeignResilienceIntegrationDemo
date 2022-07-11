package com.demo.source.config;

import com.demo.source.client.TargetServiceClient;
import com.demo.source.client.TargetServiceFallback;
import feign.Feign;
import io.github.resilience4j.circuitbreaker.CircuitBreaker;
import io.github.resilience4j.circuitbreaker.CircuitBreakerRegistry;
import io.github.resilience4j.feign.FeignDecorators;
import io.github.resilience4j.feign.Resilience4jFeign;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;

public class FeignClientConfig {

    @Autowired
    private CircuitBreakerRegistry registry;

    @Autowired
    private TargetServiceFallback fallback;

    @Bean
    @Scope("prototype")
    public Feign.Builder feignBuilder() {
        //获取一个名字为TargetService的断路器，该断路器在application.properties中配置
        CircuitBreaker circuitBreaker = registry.circuitBreaker(TargetServiceClient.SERVICE_NAME);
        FeignDecorators decorators = FeignDecorators.builder()
                //配置要使用的断路器
                .withCircuitBreaker(circuitBreaker)
                //配置要使用的Fallback
                .withFallback(fallback)
                .build();
        return Resilience4jFeign.builder(decorators);
    }
}
