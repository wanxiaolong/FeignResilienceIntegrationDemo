package com.demo.source.client;

import com.demo.source.config.FeignClientConfig;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(
        name = TargetServiceClient.SERVICE_NAME,
        //FeignClient要使用到的配置类
        configuration = FeignClientConfig.class,
        //指定被调用者的url
        url = "${target-service-url}"
)
public interface TargetServiceClient {
    String SERVICE_NAME = "TargetService";

    //指定TargetService上暴露的接口
    @GetMapping("/hello/{name}")
    String hello(@PathVariable("name") String name);
}
