package com.example.techlife;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class TechLifeApplication {

    public static void main(String[] args) {
        SpringApplication.run(TechLifeApplication.class, args);
    }

}
