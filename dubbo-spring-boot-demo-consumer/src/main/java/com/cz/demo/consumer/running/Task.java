package com.cz.demo.consumer.running;

import org.apache.dubbo.config.annotation.DubboReference;
import com.cz.demo.service.DemoService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class Task implements CommandLineRunner {
    // 通过@DubboReference 从 Dubbo 获取了一个 RPC 订阅
    // 这个 demoService 可以像本地调用一样直接调用
    @DubboReference
    private DemoService demoService;

    @Override
    public void run(String... args) throws Exception {
        System.out.println(demoService.sayHello("dubbo-consumer-codez: world"));
        // 在 run方法中创建了一个线程进行调用
        new Thread(() -> {
            while (true) {
                try {
                    Thread.sleep(1000);
                    System.out.println(new Date() + " Receive result ======> " + demoService.sayHello("world"));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    Thread.currentThread().interrupt();
                }
            }
        }).start();
    }
}