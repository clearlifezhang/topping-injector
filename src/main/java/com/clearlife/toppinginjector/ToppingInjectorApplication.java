package com.clearlife.toppinginjector;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class ToppingInjectorApplication {

    public static void main(String[] args) {
        SpringApplication.run(ToppingInjectorApplication.class, args);
    }

}
