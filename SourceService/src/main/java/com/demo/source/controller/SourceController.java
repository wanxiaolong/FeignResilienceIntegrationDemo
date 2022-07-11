package com.demo.source.controller;

import com.demo.source.client.TargetServiceClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SourceController {

    private static final Logger LOGGER = LoggerFactory.getLogger(SourceController.class);

    @Autowired
    private TargetServiceClient targetServiceClient;

    @GetMapping("/hello/{name}")
    public String hello(@PathVariable("name") String name) {
        //调用TargetService
        String result = targetServiceClient.hello(name);
        LOGGER.info("SourceService got response from TargetService: " + result);
        return result;
    }
}
