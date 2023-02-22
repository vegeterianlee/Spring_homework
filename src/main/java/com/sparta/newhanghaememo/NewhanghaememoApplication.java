package com.sparta.newhanghaememo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class NewhanghaememoApplication {

    public static void main(String[] args) {
        SpringApplication.run(NewhanghaememoApplication.class, args);
    }

}
