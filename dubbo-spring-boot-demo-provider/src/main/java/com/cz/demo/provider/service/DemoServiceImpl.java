package com.cz.demo.provider.service;

import org.apache.dubbo.config.annotation.DubboService;
import com.cz.demo.service.DemoService;

@DubboService
public class DemoServiceImpl implements DemoService {
    @Override
    public String sayHello(String name) {
        return "codez-dubbo-spring-boot-provider: Hello, " + name + " (from Spring Boot)";
    }
}
