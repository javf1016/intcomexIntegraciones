package com.intcomexintegraciones;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
public class IntcomexIntegracionesApplication {

    public static void main(String[] args) {
        SpringApplication.run(IntcomexIntegracionesApplication.class, args);
    }

}
