package com.example.hjk;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.example.hjk.dao")
public class HjkApplication {

    public static void main(String[] args) {
        SpringApplication.run(HjkApplication.class, args);
    }

}

