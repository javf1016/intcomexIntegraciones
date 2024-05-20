package com.intcomexintegraciones;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.server.ConfigurableWebServerFactory;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
public class IntcomexIntegracionesApplication {

    public static void main(String[] args) {
        SpringApplication.run(IntcomexIntegracionesApplication.class, args);
    }

    @Bean
    public WebServerFactoryCustomizer<ConfigurableWebServerFactory> webServerFactoryCustomizer(
            @Value("${PORT:8080}") String port) {
        return factory -> factory.setPort(Integer.parseInt(port));
    }
}
