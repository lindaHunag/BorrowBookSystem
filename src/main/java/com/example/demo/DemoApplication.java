package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@SpringBootApplication
public class DemoApplication {
    @RequestMapping("/home")
    String home() {
        return "<h1>Spring boot Success!!!</h1><h2>Spring boot</h2>";
    }

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

}
