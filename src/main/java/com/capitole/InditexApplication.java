package com.capitole;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories
public class InditexApplication {

    public static void main(String[] args) {
        SpringApplication.run(InditexApplication.class, args);
    }
}
