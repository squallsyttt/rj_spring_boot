package com.griffith.rj_spring_boot;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@Slf4j
@SpringBootApplication
@ServletComponentScan //这个注释 是为了能够扫描到过滤器（检查是否登陆）组件而引入的
public class RjSpringBootApplication {
    public static void main(String[] args) {
        SpringApplication.run(RjSpringBootApplication.class, args);
        log.info("proj start success!");
    }

}
