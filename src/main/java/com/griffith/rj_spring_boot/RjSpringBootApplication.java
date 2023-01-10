package com.griffith.rj_spring_boot;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@Slf4j
@SpringBootApplication
public class RjSpringBootApplication {

    public static void main(String[] args) {
        SpringApplication.run(RjSpringBootApplication.class, args);
        log.info("proj start success!");
    }

}
