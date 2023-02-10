package com.griffith.rj_spring_boot;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Slf4j
@SpringBootApplication
@ServletComponentScan //这个注释 是为了能够扫描到过滤器（检查是否登陆）组件而引入的
@EnableTransactionManagement//开启事务
@EnableCaching//开始缓存注解
public class RjSpringBootApplication {
    public static void main(String[] args) {
        SpringApplication.run(RjSpringBootApplication.class, args);
        log.info("proj start success!");
    }

}
