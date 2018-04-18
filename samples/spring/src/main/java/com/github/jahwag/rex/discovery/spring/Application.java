package com.github.jahwag.rex.discovery.spring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("com.github.jahwag.rex")
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}
