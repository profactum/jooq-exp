package com.example.jooqexp.jooqexp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

@SpringBootApplication
@ConfigurationPropertiesScan
public class JooqExpApplication {

    public static void main(String[] args) {
        SpringApplication.run(JooqExpApplication.class, args);
    }

}
