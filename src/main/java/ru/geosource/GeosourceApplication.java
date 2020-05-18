package ru.geosource;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@EnableCaching
@SpringBootApplication
public class GeosourceApplication {

    public static void main(String[] args) {
        SpringApplication.run(GeosourceApplication.class, args);
    }
}
